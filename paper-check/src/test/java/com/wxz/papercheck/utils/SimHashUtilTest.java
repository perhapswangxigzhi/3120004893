package com.wxz.papercheck.utils;

import org.junit.Test;




public class SimHashUtilTest {
    @Test
    public void getHashTest(){
        String[] strings = {""};
        for (String string : strings) {
            String stringHash = SimHashUtil.getHash(string);
            System.out.println(stringHash.length());
            System.out.println(stringHash);
        }
    }

    @Test
    public void getSimHashTest(){
        String str0 = FileUtil.txtTransferToStr("D:/test/new.txt");
        String str1 = FileUtil.txtTransferToStr("D:/test/orig_0.8_add.txt");
        System.out.println(SimHashUtil.getSimHash(str0));
        System.out.println(SimHashUtil.getSimHash(str1));
    }

}
