package com.zfs.web.controller;

import com.zfs.web.service.LoginService;
import com.zfs.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * @author: dangyi
 * @date: Created in 16:30 2019/10/12
 * @version: 1.0.0
 * @description:
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("entry")
    public String login(HttpSession session,
                        HttpServletResponse response,
                        Map<String, Object> result,
                        @RequestParam(value = "username", required = false) String username,
                        @RequestParam(value = "password", required = false) String password,
                        @RequestParam(value = "checkcode",required = false) String checkcode
                        ) {
      String res = this.loginService.login(session, response, result, username, password, checkcode);
      return res;
    }


    /**
     * 获取验证码
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping("/login/get/checkcode")
    public void getCheckcode(HttpServletResponse response, HttpSession session) throws IOException {
        //服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        //在内存中创建一个长80，宽30的图片，默认黑色背景
        //参数一：长
        //参数二：宽
        //参数三：颜色
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //获取画笔
        Graphics g = image.getGraphics();
        //设置画笔颜色为灰色
        g.setColor(Color.GRAY);
        //填充图片
        g.fillRect(0,0, width,height);

        //产生4个随机验证码，12Ey
        String checkCode = getCheckcodeString();
        checkCode="becf";
        //将验证码放入HttpSession中
        session.setAttribute("CHECKCODE_SERVER",checkCode);

        //设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        //设置字体的小大
        g.setFont(new Font("黑体",Font.BOLD,24));
        //向图片上写入验证码
        g.drawString(checkCode,15,25);

        //将内存中的图片输出到浏览器
        //参数一：图片对象
        //参数二：图片的格式，如PNG,JPG,GIF
        //参数三：图片输出到哪里去
        ImageIO.write(image,"PNG",response.getOutputStream());
    }

    /**
     * 产生4位随机字符串
     */
    private String getCheckcodeString() {
        String base = "0123456789ABCDEFGabcdefg";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<=4;i++){
            //产生0到size-1的随机值
            int index = r.nextInt(size);
            //在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            //将c放入到StringBuffer中去
            sb.append(c);
        }
        return sb.toString();
    }


    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param httpSession
     * @return
     */
    @ResponseBody
    @PostMapping("updatePassword")
    public ResultVO updatePassword (HttpSession httpSession,
                                    @RequestParam("oldPassword") String oldPassword,
                                    @RequestParam("newPassword") String newPassword){
        return this.loginService.updatePassword(httpSession, oldPassword, newPassword);
    }


    @GetMapping("logout")
    public String logout (HttpSession httpSession){
        return this.loginService.logout(httpSession);
    }



}
