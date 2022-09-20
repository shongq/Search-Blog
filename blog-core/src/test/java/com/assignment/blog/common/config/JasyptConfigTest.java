package com.assignment.blog.common.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

class JasyptConfigTest {

    @Test
    void contextLoads() {
    }

    @Test
    void jasyptEncodingTest() {
        String dbUserName = "sa";
        String restApiKey = "KakaoAK a3ef522babf5b55df8fabb856a515e3b";
        String clientId = "tFrXWhKyGF4pKS3PXIU1";
        String clientSecert = "_ppCkoCIju";

        System.out.println("restApiKey : " + jasyptEncoding(restApiKey));
        System.out.println("dbUserName : " + jasyptEncoding(dbUserName));
        System.out.println("clientId : " + jasyptEncoding(clientId));
        System.out.println("clientSecert : " + jasyptEncoding(clientSecert));
    }
    public String jasyptEncoding(String value) {

        String key = "blog_jasypt_key";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);
        return pbeEnc.encrypt(value);
    }
}