package com.wxz.papercheck.utils;

import com.hankcs.hanlp.HanLP;
import com.wxz.papercheck.exception.MyException;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class SimHashUtil {
    static final int TXT_MIN_LENGTH = 200;

    /**
     * 使用MD5算法进行hash加密
     */
    public static String getHash(String str){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new MyException(str + "：MD5加密失败");
        }
        return new BigInteger(1, messageDigest.digest(str.getBytes(StandardCharsets.UTF_8))).toString(2);

    }

    /**
     * 传入String,计算出它的simHash值，并以字符串形式输出
     */
    public static String getSimHash(String str){
        final int strLength = str.length();
        if(strLength < TXT_MIN_LENGTH){
            throw new MyException("文本过短！");
        }
        // 用数组表示特征向量,取128位,从 0 1 2 位开始表示从高位到低位
        int[] v = new int[128];
        // 1、分词（使用了外部依赖hankcs包提供的接口）
        List<String> keyWordList = HanLP.extractKeyword(str, strLength);
        // hash
        int size = keyWordList.size();
        for(int i = 0; i < size; i++){
            String keyword = keyWordList.get(i);
            // 2、获取hash值
            StringBuilder keywordHash = new StringBuilder(getHash(keyword));
            final int keyWordHashLength = keywordHash.length();
            if (keyWordHashLength < 128) {
                // hash值可能少于128位，在低位以0补齐
                int dif = 128 - keyWordHashLength;
                for (int j = 0; j < dif; j++) {
                    keywordHash.append("0");
                }
            }
            // 3、加权、合并
            for (int j = 0; j < v.length; j++) {
                // 对keywordHash的每一位与'1'进行比较
                if (keywordHash.charAt(j) == '1') {
                    //权重分10级，由词频从高到低，取权重10~0
                    v[j] += (10 - (i / (size / 10)));
                } else {
                    v[j] -= (10 - (i / (size / 10)));
                }
            }
        }
        // 4、降维、储存返回的simHash值
        StringBuilder simHash = new StringBuilder();
        for (int i : v) {
            // 从高位遍历到低位
            if (i <= 0) {
                simHash.append("0");
            } else {
                simHash.append("1");
            }
        }
        return simHash.toString();
    }

}
