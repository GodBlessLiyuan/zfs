package com.rpa.pay.utils;

import com.rpa.pay.constant.WxPayConstant;
import com.rpa.pay.pojo.WxFeedbackPO;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
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
     * 解析Request
     *
     * @param req
     * @return
     */
    public static Map<String, String> parseReq(HttpServletRequest req) {
        Map<String, String> info = null;
        try {
            InputStream is = req.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            StringBuffer sb = new StringBuffer();
            String s;
            while ((s = reader.readLine()) != null) {
                sb.append(s);
            }
            reader.close();
            is.close();

            info = WxPayUtil.parseXML(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return info;
    }

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
        return WxPayUtil.genWxPayRes(WxPayConstant.SUCCESS, WxPayConstant.OK);
    }

    /**
     * 微信支付确认结果失败
     *
     * @return
     */
    public static String failWxPay() {
        return WxPayUtil.genWxPayRes(WxPayConstant.FAIL, WxPayConstant.NO);
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

    /**
     * 微信反馈结果封装
     *
     * @return
     */
    public static WxFeedbackPO convertMap2PO(Map<String, String> map) {
        WxFeedbackPO po = new WxFeedbackPO();

        String returnCode = map.get(WxPayConstant.RETURN_CODE);
        po.setReturnCode(returnCode);
        po.setReturnMsg(map.get(WxPayConstant.RETURN_MSG));
        if (WxPayConstant.SUCCESS.equals(returnCode)) {
            po.setAppid(map.get(WxPayConstant.APPID));
            po.setMchId(map.get(WxPayConstant.MCH_ID));
            po.setDeviceInfo(map.get(WxPayConstant.DEVICE_INFO));
            po.setNonceStr(map.get(WxPayConstant.NONCE_STR));
            po.setSigin(map.get(WxPayConstant.SIGN));
            po.setResultCode(map.get(WxPayConstant.RESULT_CODE));
            po.setErrCode(map.get(WxPayConstant.ERR_CODE));
            po.setErrCodeDes(map.get(WxPayConstant.ERR_CODE_DES));
            po.setOpenid(map.get(WxPayConstant.OPENID));
            po.setIsSubscribe(map.get(WxPayConstant.IS_SUBSCRIBE));
            po.setTradeType(map.get(WxPayConstant.TRADE_TYPE));
            po.setBankType(map.get(WxPayConstant.BANK_TYPE));
            if (null != map.get(WxPayConstant.TOTAL_FEE)) {
                po.setTotalFee(Integer.parseInt(map.get(WxPayConstant.TOTAL_FEE)));
            }
            po.setFeeType(map.get(WxPayConstant.FEE_TYPE));
            if (null != map.get(WxPayConstant.CASH_FEE)) {
                po.setCashFee(Integer.parseInt(map.get(WxPayConstant.CASH_FEE)));
            }
            po.setCashFeeType(map.get(WxPayConstant.CASH_FEE_TYPE));
            if (null != map.get(WxPayConstant.COUPON_FEE)) {
                po.setCouponFee(Integer.parseInt(map.get(WxPayConstant.COUPON_FEE)));
            }
            if (null != map.get(WxPayConstant.COUPON_COUNT)) {
                po.setCouponCount(Integer.parseInt(map.get(WxPayConstant.COUPON_COUNT)));
            }
            po.setTransactionId(map.get(WxPayConstant.TRANSACTION_ID));
            po.setOutTradeNo(map.get(WxPayConstant.OUT_TRADE_NO));
            po.setAttach(map.get(WxPayConstant.ATTCH));
            po.setTimeEnd(map.get(WxPayConstant.TIME_END));
        }

        return po;
    }
}
