package com.well.image.consumer.controller;

import com.luhuiguo.fastdfs.domain.StorePath;
import com.luhuiguo.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@RestController
@RequestMapping(value = "image")
public class ImageController {

    @Autowired
    private FastFileStorageClient storageClient;


    @RequestMapping(value = "fileUpload", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void fileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
                try {
                    // 获取图片的文件名
                    String fileName = file.getOriginalFilename();
                    InputStream fis = file.getInputStream();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream(fis.available());
                    byte[] bytes = new byte[fis.available()];
                    int temp;
                    while ((temp = fis.read(bytes)) != -1) {
                        baos.write(bytes, 0, temp);
                    }
                    fis.close();
                    baos.close();
                    byte[] buffer = baos.toByteArray();
                    StorePath storePath = storageClient.uploadFile(buffer, fileName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            if (file.getContentType().contains("image")) {
            } else {

            }
        }
    }
}
