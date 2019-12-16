package com.unionpay.cloudatlas.avalon.lambda.cart;

import com.unionpay.cloudatlas.avalon.lambda.file.FileService;
import org.junit.Test;

import java.io.IOException;

public class FileServiceTest {
    @Test
    public void testFileHandle() throws IOException {
        FileService fileService = new FileService();
        // 通过 lambda 表达式，打印文件内容
        fileService.fileHandle(Thread.currentThread().getContextClassLoader().getResource("pom.xml").getPath(), fileContent -> {
            // 注释
            System.out.println(fileContent);
        });
    }
}
