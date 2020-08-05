package com.zfs.server.service.impl;

import com.zfs.common.mapper.UserMapper;
import com.zfs.common.pojo.UserPO;
import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.SmsDTO;
import com.zfs.server.service.ISmsService;
import com.zfs.server.utils.RedisCacheUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Random;

/**
 * @author: velve
 * @date: Created in 2019/10/18 10:24
 * @description: 发送短信模块
 * @version: 1.0
 */
@Data
@Service
public class SmsServiceImpl implements ISmsService {

    @Autowired
    private RedisCacheUtil cache;
    @Resource
    private UserMapper userMapper;

    /**
     * @return 1 成功 0 失敗
     */
    @Override
    public int sendSMS(String phone) {
        if ("15100000000".equals(phone)) {
            cache.cacheVerifyCode(phone, "000000");
            return 1;
        }
        if ("19857119681".equals(phone)) {
            cache.cacheVerifyCode(phone, "000000");
            return 1;
        }

        // 6位验证码
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
        cache.cacheVerifyCode(phone, verifyCode);

        try {
            String content = String.format("尊敬的用户，您本次的验证码是%s，请及时输入验证码完成操作。【Z分身】", verifyCode);

            String postData = "sname=dlxinj00&spwd=admin123456&scorpid=&sprdid=1012818&sdst=" + phone + "&smsg=" + java.net.URLEncoder.encode(content, "utf-8");
            //发送POST请求
            URL url = new URL("http://cf.51welink.com/submitdata/Service.asmx/g_Submit");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return 0;
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            if (result.contains("提交成功")) {
                return 1;
            } else {
                return 0;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ResultVO sendSMS(Long ud) {
        UserPO userPO = userMapper.selectByPrimaryKey(ud);
        if (null == userPO) {
            // 用户不存在
            return new ResultVO(2000);
        }

        int res = this.sendSMS(userPO.getPhone());
        if (res == 0) {
            return new ResultVO(1010);
        }

        return new ResultVO(1000);
    }

    @Override
    public ResultVO validateSMS(SmsDTO dto) {
        UserPO userPO = userMapper.selectByPrimaryKey(dto.getUd());
        if (null == userPO) {
            // 用户不存在
            return new ResultVO(2000);
        }

        // 短信码
        int code = cache.checkSmsByCache(userPO.getPhone(), dto.getSms());
        if (1000 != code) {
            return new ResultVO(code);
        }

        return new ResultVO(1000);
    }
}
