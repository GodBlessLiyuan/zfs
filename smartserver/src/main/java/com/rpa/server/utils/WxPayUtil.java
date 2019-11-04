package com.rpa.server.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/11/4 10:09
 * @description: 微信支付
 * @version: 1.0
 */
public class WxPayUtil {

    /**
     * 解析XML
     *
     * @param xml
     * @return
     */
    public static Map<String, String> parseXML(String xml) {
        if (null == xml || "".equals(xml)) {
            return null;
        }

        Map<String, String> res = new HashMap<>(16);

        xml = xml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
        try {
            Document doc = new SAXReader().read(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
            Element root = doc.getRootElement();
            List<Element> elements = root.elements();
            for (Element e : elements) {
                res.put(e.getName(), e.getTextTrim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * 微信支付确认成功
     *
     * @return
     */
    public static String successWxPay() {
        return WxPayUtil.genWxPayRes("SUCCESS", "OK");
    }

    /**
     * 微信支付确认结果失败
     *
     * @return
     */
    public static String failWxPay() {
        return WxPayUtil.genWxPayRes("FAIL", "NO");
    }


    /**
     * 生成微信支付返回码
     *
     * @param code
     * @param msg
     * @return
     */
    public static String genWxPayRes(String code, String msg) {
        return "<xml>\n" +
                "  <return_code><![CDATA[" + code + "]]></return_code>\n" +
                "  <return_msg><![CDATA[" + msg + "]]></return_msg>\n" +
                "</xml>";
    }
}
