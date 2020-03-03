package com.well.image.consumer.controller;

import com.luhuiguo.fastdfs.domain.StorePath;
import com.luhuiguo.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@RestController
@RequestMapping(value = "")
public class ImageController {

    @Autowired
    private FastFileStorageClient storageClient;

    @Value("${image.host}")
    private String host;


    @PostMapping(value = "upload", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BaseResponse fileUpload(@RequestParam("file") MultipartFile file) {
        StorePath storePath;
        BaseResponse response = new BaseResponse.Builder<StorePath>().success().builder();
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
                storePath = storageClient.uploadFile(buffer, "png");
                response.setData(storePath);
                return response;
            } catch (Exception e) {
                e.printStackTrace();
            }
/*            if (file.getContentType().contains("image")) {
            } else {

            }*/
        }
        return response;
    }

    @PostMapping(value = "delete")
    public BaseResponse deleteFile(@RequestBody Image image) {
        // 图片地址的相对路径
        BaseResponse response = new BaseResponse.Builder<StorePath>().success().builder();
        try {
            storageClient.deleteFile(image.getGroup(), image.getPath());
            return response;
        } catch (Exception e) {
            response = new BaseResponse.Builder<StorePath>().fail().builder();
            response.setMessage(e.getMessage());
            return response;
        }
    }
}

    /*

    /var/fdfs/storage0/data/00/00/rBD881tAe8yAStvjAAn3FKEmfJo444.png

    */

