package com.anzh.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

@Slf4j
public class Base64Util {
    
    private Base64Util (){}
    
    private static final int CACHE_SIZE = 1024;

    public static byte[] decode(String base64)  {
        return Base64.decodeBase64(base64.getBytes());
    }

    public static String encode(byte[] bytes) {
        return Arrays.toString(Base64.encodeBase64(bytes));
    }

    public static String encodeFile(String filePath) {
        byte[] bytes = fileToByte(filePath);
        return encode(bytes);
    }

    public static void decodeToFile(String filePath, String base64) {
        byte[] bytes = decode(base64);
        byteArrayToFile(bytes, filePath);
    }

    public static byte[] fileToByte(String filePath) {
        byte[] data = new byte[0];
        File file = new File(filePath);
        if (file.exists()) {
            try (FileInputStream in = new FileInputStream(file)) {
                ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
                byte[] cache = new byte[CACHE_SIZE];

                int nRead;
                while ((nRead = in.read(cache)) != -1) {
                    out.write(cache, 0, nRead);
                    out.flush();
                }

                out.close();
                
                data = out.toByteArray();
            }catch (Exception e) {
                log.info("读取文件异常，异常信息为：", e);
            }
        }

        return data;
    }

    public static void byteArrayToFile(byte[] bytes, String filePath) {
        try (InputStream in = new ByteArrayInputStream(bytes)) {
            File destFile = new File(filePath);
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }

            if (!destFile.createNewFile()) {
                return;
            }
            
            try (OutputStream out = new FileOutputStream(destFile)) {
                byte[] cache = new byte[CACHE_SIZE];

                int nRead;
                while ((nRead = in.read(cache)) != -1) {
                    out.write(cache, 0, nRead);
                    out.flush();
                }
            }
        }catch (Exception e) {
            log.info("byteArrayToFile exception is: ", e);   
        }
    }
}
