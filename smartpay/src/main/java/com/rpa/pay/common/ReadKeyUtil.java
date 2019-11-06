package com.rpa.pay.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;


/**
 * @author: dangyi
 * @date: Created in 18:09 2019/11/6
 * @version: 1.0.0
 * @description:
 */
public class ReadKeyUtil {

    /**
     * 获取公钥
     */
    public static PublicKey readPublicKey(String cerFileName) {

        Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

        CertificateFactory cf;
        FileInputStream in;
        Certificate c;
        PublicKey pk = null;
        try {
            cf = CertificateFactory.getInstance("X.509");
            in = new FileInputStream(cerFileName);
            c = cf.generateCertificate(in);
            pk = c.getPublicKey();

        } catch (CertificateException e) {
            logger.error("获取公钥错误！！！");
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            logger.error("获取公钥错误！！！");
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return pk;

    }


    /**
     * 获取私钥
     */
    public PrivateKey readPrivateKey(String privateKeyFileName, String privatepass) {

        Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

        KeyStore ks;
        PrivateKey prk = null;
        try {
            ks = KeyStore.getInstance("pkcs12");
            FileInputStream fis = new FileInputStream(privateKeyFileName);
            ks.load(fis, null);
            String pwd = privatepass;
            String alias = ks.aliases().nextElement().toString();
            prk = (PrivateKey) ks.getKey(alias, pwd.toCharArray());
        } catch (Exception e) {
            logger.error("获取私钥错误！！！");
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return prk;
    }
}
