package com.unionpay.cloudatlas.avalon.lambda.file;

import java.io.*;

/**
 * 文件服务类
 */
public class FileService {

    /**
     * 从 url 获取本地文件内容，调用函数式接口处理
     * @param url
     * @param fileConsumer
     * @throws IOException
     */
    public void fileHandle(String url, FileConsumer fileConsumer) throws IOException {
        // 创建文件读取流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(url)));
        // 定义行变量
        String line;
        StringBuilder stringBuilder = new StringBuilder();

        // 循环读取文件内容
        while((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line + "\n");
        }
        // 调用函数式接口方法，将文件内容传递给 lambda 表达式，实现业务逻辑
        fileConsumer.fileHandle(stringBuilder.toString());
    }
}
