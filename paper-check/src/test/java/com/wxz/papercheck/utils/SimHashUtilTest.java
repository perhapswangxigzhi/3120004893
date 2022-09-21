package com.wxz.papercheck.utils;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ：ZeqiangLi
 * @date ： 2021/9/19 11:35
 */
@SpringBootTest
public class SimHashUtilTest {
    @Test
    public void getHashTest(){
        String[] strings = {"余华", "是", "一位", "真正", "的", "作家"};
        for (String string : strings) {
            String stringHash = SimHashUtil.getHash(string);
            System.out.println(stringHash.length());
            System.out.println(stringHash);
        }
    }

    @Test
    public void getSimHashTest(){
        String str0 = FileUtil.txtTransferToStr("D:/test/orig.txt");
        String str1 = FileUtil.txtTransferToStr("D:/test/orig_0.8_add.txt");
        System.out.println(SimHashUtil.getSimHash(str0));
        System.out.println(SimHashUtil.getSimHash(str1));
    }

}
