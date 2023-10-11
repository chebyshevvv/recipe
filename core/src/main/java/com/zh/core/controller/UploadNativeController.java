package com.zh.core.controller;

import com.zh.common.http.HttpResult;
import com.zh.core.NativeImageStore;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("upload/native")
public class UploadNativeController {
    private final NativeImageStore store;

    public UploadNativeController(NativeImageStore store) {
        this.store = store;
    }

    @PostMapping
    public HttpResult upload(MultipartFile file) throws IOException {
        Map<String,String> map = new HashMap<>(1);
        map.put("fileName",this.store.store(file));
        return HttpResult.ok(map);
    }

    @GetMapping("file/{fileName}")
    public void get(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        this.store.read(fileName,response);
    }
}
