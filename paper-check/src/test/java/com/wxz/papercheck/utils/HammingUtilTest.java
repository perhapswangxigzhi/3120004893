package com.wxz.papercheck.utils;

import org.junit.Test;



public class HammingUtilTest {

    @Test
    public void getHammingDistanceTest() {
        String str0 = FileUtil.txtTransferToStr("D:/test/orig.txt");
        String str1 = FileUtil.txtTransferToStr("D:/test/orig_0.8_add.txt");
        int distance = HammingUtil.getHammingDistance(SimHashUtil.getSimHash(str0), SimHashUtil.getSimHash(str1));
        System.out.println("海明距离：" + distance);
        System.out.println("相似度: " + (100 - distance * 100 / 128) + "%");
    }

    @Test
    public void getHammingDistanceFailTest() {
        // 测试str0.length()!=str1.length()的情况
        String str0 = "10101010";
        String str1 = "1010101";
        System.out.println(HammingUtil.getHammingDistance(str0, str1));
    }

    @Test
    public void getSimilarityTest() {
        String str0 = FileUtil.txtTransferToStr("D:/test/orig.txt");
        String str1 = FileUtil.txtTransferToStr("D:/test/orig_0.8_add.txt");
        int distance = HammingUtil.getHammingDistance(SimHashUtil.getSimHash(str0), SimHashUtil.getSimHash(str1));
        double similarity = HammingUtil.getSimilarity(SimHashUtil.getSimHash(str0), SimHashUtil.getSimHash(str1));
        System.out.println("str0和str1的汉明距离: " + distance);
        System.out.println("str0和str1的相似度:" + similarity);
    }
}
