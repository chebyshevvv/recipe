package com.zh.core;

import cn.hutool.core.lang.Validator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class NativeImageStore {

    public String store(MultipartFile file) throws IOException {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String fileName = now + file.getOriginalFilename();
        Path filePath = Paths.get(System.getProperty("user.dir"), fileName);

        FileChannel channel = FileChannel.open(filePath, StandardOpenOption.WRITE,StandardOpenOption.CREATE_NEW,StandardOpenOption.APPEND);
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 64);
        byte[] fileBytes = file.getBytes();
        for (int i = 0; i < fileBytes.length; i++) {

            buffer.put(fileBytes[i]);
            if (buffer.position() == buffer.limit() - 1){

                buffer.flip();
                channel.write(buffer);
            }else if (i == fileBytes.length -1){

                buffer.flip();
                channel.write(buffer);
            }
        }
        channel.close();
        return fileName;
    }
    public void read(String fileName, HttpServletResponse response) throws IOException {
        Path filePath = Paths.get(System.getProperty("user.dir"), fileName);
        Validator.validateTrue(filePath.toFile().exists(),"文件不存在");

        FileChannel channel = FileChannel.open(filePath, StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 64);
        int length = channel.read(buffer);
        while (length != -1){
            response.getOutputStream().write(buffer.array());
            buffer.clear();
            length = channel.read(buffer);
        }
        response.getOutputStream().flush();
        channel.close();
    }

}
