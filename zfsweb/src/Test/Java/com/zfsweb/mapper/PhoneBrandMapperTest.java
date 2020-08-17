package com.zfsweb.mapper;

import com.alibaba.fastjson.JSON;
import com.zfs.common.mapper.PhoneBrandMapper;
import com.zfs.common.pojo.PhoneBrandPO;
import com.zfs.web.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-08-17 10:49
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {WebApplication.class})
public class PhoneBrandMapperTest {
    @Autowired
    private PhoneBrandMapper phoneBrandMapper;
    @Test
    public void json2POS() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("D:\\ProMe\\test8\\zfs\\smartzfs\\smarthelper\\zfsweb\\src\\Test\\java\\com\\zfsweb\\mapper\\phoneBrandjson.txt")));//构造一个BufferedReader类来读取文件
        String s = null;
        StringBuilder result = new StringBuilder();
        while((s = br.readLine())!=null){
            result.append(s);
        }

        List<PhoneBrandPO> brandPOS = JSON.parseArray(result.toString(), PhoneBrandPO.class);
        for(PhoneBrandPO phoneBrandPO:brandPOS){
            phoneBrandMapper.insert(phoneBrandPO);
        }
    }
    @Test
    @Deprecated
    public void readFile(){
        try{
            File f = new File(this.getClass().getResource("/").getPath());
            System.out.println(f);
            BufferedReader br = new BufferedReader(new FileReader(new File("D:\\ProMe\\test8\\zfs\\smartzfs\\smarthelper\\zfsweb\\src\\Test\\java\\com\\zfsweb\\mapper\\z分身中机型伪装的机型.txt")));//构造一个BufferedReader类来读取文件
            String s = null;
            List<PhoneBrandPO> brandPOS=new ArrayList<>();
            int flag=1;
            PhoneBrandPO brandPO=new PhoneBrandPO();
            while((s = br.readLine())!=null&&(s = br.readLine()).length()>1){//使用readLine方法，一次读一行
                String[] tmp = s.split(":");
                if(flag==1){
                    brandPO.setModel(tmp[1]);
                    flag=2;
                }else if(flag==2){
                    brandPO.setManufacturer(tmp[1]);
                    flag=3;
                }else if(flag==3){
                    brandPO.setName(tmp[1]);
                    flag=4;
                }else if(flag==4){
                    brandPO.setBrand(tmp[1]);
                    flag=1;
                }

            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
