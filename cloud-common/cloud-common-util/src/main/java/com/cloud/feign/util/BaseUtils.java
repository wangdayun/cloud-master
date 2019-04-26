package com.cloud.feign.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * 常量工具类
 *
 * @author : dayun_wang
 */
public class BaseUtils {

    /**
     * 获取uuid
     *
     * @return
     */
    public static String getRandomString(int length) {
        char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'q',
                'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(chars.length);
            sb.append(chars[number]);
        }
        return sb.toString();
    }

    /**
     * 记录debug信息
     *
     * @param debug
     */
    public static void addDebugLog(String debug) {
        String fileName = "debug.log";
        String path = "/SpringCloud-log/debug-error/";
        File dirs = new File(path);
        if (!dirs.exists()) {
            dirs.mkdirs();
        }
        String filePath = path + fileName;
        BaseUtils.writeStringToFile(DateUtils.getTime() + " " + debug + "\r\n", new File(filePath), true);
    }

    /**
     * 记录debug信息(自定义)
     *
     * @param file
     * @param debug
     */
    public static void addDebugLog(String file, String debug) {
        String fileName = "debug.log";
        File dirs = null;
        if (StringUtils.isNotBlank(file)) {
            dirs = new File(file);
            if (!dirs.exists()) {
                dirs.mkdirs();
            }
            String filePath = file + fileName;
            BaseUtils.writeStringToFile(DateUtils.getTime() + " " + debug + "\r\n", new File(filePath), true);
        }
    }

    /**
     * 写入字符串到文件
     *
     * @param message
     * @param file
     * @param flag
     */
    public static void writeStringToFile(String message, File file, boolean flag) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file, flag);
            outputStream.write(message.getBytes("UTF-8"));
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
