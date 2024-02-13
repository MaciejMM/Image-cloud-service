package com.example.imagecloudservice.v1.storage;

import com.cloudinary.Cloudinary;
import com.example.imagecloudservice.models.CloudinaryModel;
import com.example.imagecloudservice.models.CloudinaryResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryService(final Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    public CloudinaryModel uploadFile(final MultipartFile file,
                                      final String folderName) {
        CloudinaryResponse cloudinaryResponse = new CloudinaryResponse();

        HashMap<String, String> options = mapOptions(folderName);
        Map<String, Object> upload;
        try {
            upload = cloudinary.uploader().upload(file.getBytes(), options);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return cloudinaryResponse.map(upload, file.getOriginalFilename());
    }

    public HashMap<String, String> deleteFile(final String cloudinaryPublicId) {
        HashMap<String, String> destroy;
        try {
            destroy = (HashMap<String, String>) cloudinary.uploader().destroy(cloudinaryPublicId, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return destroy;
    }

    private HashMap<String, String> mapOptions(final String option) {
        HashMap<String, String> options = new HashMap<>();
        options.put("folder", option);
        return options;
    }
}
