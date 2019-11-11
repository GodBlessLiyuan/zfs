package com.rpa.pay.utils;

import com.rpa.pay.config.WxPayConfig;
import com.rpa.pay.constant.WxPayConstant;
import com.rpa.pay.pojo.OrderPO;
import com.rpa.pay.pojo.WxFeedbackPO;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.DigestUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: xiahui
 * @date: Created in 2019/11/4 10:09
 * @description: 微信支付
 * @version: 1.0
 */
public class WxPayUtil {

    /**
     * 创建微信下单请求参数
     *
     * @param orderPO
     * @param wxPayConfig
     * @param req
     * @return
     */
    public static String createReqParam(OrderPO orderPO, WxPayConfig wxPayConfig, HttpServletRequest req) {
        SortedMap<String, Object> wxPayParam = new TreeMap<>();
        wxPayParam.put(WxPayConstant.APPID, wxPayConfig.getAppid());
        wxPayParam.put(WxPayConstant.BODY, "砖助智能助手-商品充值");
        wxPayParam.put(WxPayConstant.MCH_ID, wxPayConfig.getMch_id());
        wxPayParam.put(WxPayConstant.NONCE_STR, UUID.randomUUID().toString().replace("-", ""));
        wxPayParam.put(WxPayConstant.NOTIFY_URL, wxPayConfig.getNotify_url());
        wxPayParam.put(WxPayConstant.OUT_TRADE_NO, orderPO.getOrderNumber());
        wxPayParam.put(WxPayConstant.SPBILL_CREATE_IP, RequestUtil.getIpAddr(req));
        wxPayParam.put(WxPayConstant.TOTAL_FEE, orderPO.getPay());
        wxPayParam.put(WxPayConstant.TRADE_TYPE, "APP");
        wxPayParam.put(WxPayConstant.SIGN, WxPayUtil.sign(wxPayParam, wxPayConfig.getKey()));

        return WxPayUtil.convertMap2Xml(wxPayParam);
    }

    /**
     * 微信请求参数签名
     *
     * @param wxPayParam
     * @param key        商户密钥
     * @return
     */
    private static String sign(SortedMap<String, Object> wxPayParam, String key) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : wxPayParam.entrySet()) {
            String k = entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"key".equals(k) && !"sign".equals(k)) {
                sb.append(k).append("=").append(v).append("&");
            }
        }
        sb.append("key=").append(key);

        return DigestUtils.md5DigestAsHex(sb.toString().getBytes()).toUpperCase();
    }

    /**
     * 转换 Map 为 XML
     *
     * @param map
     * @return
     */
    private static String convertMap2Xml(SortedMap<String, Object> map) {
        StringBuffer sb = new StringBuffer();

        sb.append("<xml>\n");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append("<").append(entry.getKey()).append(">");
            sb.append("<![CDATA[").append(entry.getValue()).append("]]>");
            sb.append("</").append(entry.getKey()).append(">\n");
        }
        sb.append("</xml>");

        return sb.toString();
    }

    /**
     * 发送微信支付下单请求
     *
     * @param reqUrl 请求地址
     * @param output 请求参数
     * @return
     */
    public static String httpsRequest(String reqUrl, String output) {
        try {
            URL url = new URL(reqUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            if (null != output) {
                OutputStream os = connection.getOutputStream();
                os.write(output.getBytes(StandardCharsets.UTF_8));
                os.close();
            }

            // 返回结果
            String res = WxPayUtil.readInputStream(connection.getInputStream());
            connection.disconnect();

            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 解析Request
     *
     * @param req
     * @return
     */
    public static Map<String, String> parseReq(HttpServletRequest req) {
        Map<String, String> info = null;
        try {
            info = WxPayUtil.parseXML(WxPayUtil.readInputStream(req.getInputStream()));
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
            po.setSign(map.get(WxPayConstant.SIGN));
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

    /**
     * 读取 InputStream 数据
     */
    public static String readInputStream(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        StringBuffer sb = new StringBuffer();
        String s;
        while ((s = reader.readLine()) != null) {
            sb.append(s);
        }
        reader.close();
        is.close();

        return sb.toString();
    }

    /**
     * 生成订单编号
     *
     * @return
     */
    public static String genOrderNumber() {
        StringBuffer sb = new StringBuffer();
        sb.append("vip");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
        sb.append(sdf.format(new Date()));
        sb.append(UUID.randomUUID().toString().replace("-", "").substring(0, 8));
        return sb.toString();
    }
}
