package com.wxz.papercheck.utils;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;


@SpringBootTest
public class FileUtilTest {
    @Test
    public void readTxtTest() {
        // 路径存在，正常读取
        String str = FileUtil.txtTransferToStr("D:/test/orig.txt");
        String[] strings = str.split(" ");
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    public void writeTxtTest() {
        // 路径存在，正常写入
        double[] elem = {0.11, 0.22, 0.33, 0.44, 0.55};
        for (int i = 0; i < elem.length; i++) {
            FileUtil.writeTxt(elem[i], "D:/test/ans.txt");
        }
    }

    @Test
    public void readTxtFailTest() {
        // 路径不存在，读取失败
        String str = FileUtil.txtTransferToStr("D:/test/orig_0.8_add.txt");
    }

    @Test
    public void writeTxtFailTest() {
        // 路径错误，写入失败
        double[] elem = {0.11, 0.22, 0.33, 0.44, 0.55};
        for (int i = 0; i < elem.length; i++) {
            FileUtil.writeTxt(elem[i], "D:/test/ans.txt");
        }
    }
}
