package com.fixnow.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class UploadService {
    private final Cloudinary cloudinary;

    public UploadService(
        @Value("${CLOUDINARY_CLOUD_NAME}") String name,
        @Value("${CLOUDINARY_API_KEY}") String key,
        @Value("${CLOUDINARY_API_SECRET}") String secret
    ) {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", name,
            "api_key", key,
            "api_secret", secret
        ));
    }

    public String uploadImage(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), 
            ObjectUtils.asMap("resource_type", "auto", "folder", "fixnow_solicitudes"));
        return uploadResult.get("secure_url").toString();
    }
}
