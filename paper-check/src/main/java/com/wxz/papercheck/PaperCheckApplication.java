package com.wxz.papercheck;

import com.wxz.papercheck.utils.HammingUtil;
import com.wxz.papercheck.utils.SimHashUtil;
import com.wxz.papercheck.utils.FileUtil;


public class PaperCheckApplication {

    public static void main(String[] args) {

        // 读取论文txt文件，转化为final常量字符串
        final String fileStr0 = FileUtil.txtTransferToStr(args[0]);
        final String fileStr1 = FileUtil.txtTransferToStr(args[1]);
        // 读取论文查重结果文件路径
        final String saveResultFileName = args[2];
        // 由字符串得出对应的 simHash值
        final String simHash0 = SimHashUtil.getSimHash(fileStr0);
        final String simHash1 = SimHashUtil.getSimHash(fileStr1);
        // 由 simHash值求出相似度
        double similarity = HammingUtil.getSimilarity(simHash0, simHash1);
        // 把相似度写入最后的结果文件中
        FileUtil.writeTxt(similarity, saveResultFileName);
        // 退出程序
        System.exit(0);
    }

}
