package com.example.imagecloudservice.v1.storage;

import com.example.imagecloudservice.models.CloudinaryModel;
import com.example.imagecloudservice.models.StorageFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class StorageService {

    private final StorageRepository storageRepository;
    private final CloudinaryService cloudinaryService;

    public StorageService(final StorageRepository storageRepository, final CloudinaryService cloudinaryService) {
        this.storageRepository = storageRepository;
        this.cloudinaryService = cloudinaryService;
    }

    public List<StorageFile> getImages() {
        return this.storageRepository.findAll();
    }

    public StorageFile uploadImage(final MultipartFile file, final String folderName) {
        CloudinaryModel cloudinaryImage = cloudinaryService.uploadFile(file, folderName);
        StorageFile storageFile = mapStorageFileModel(cloudinaryImage);
        storageRepository.save(storageFile);
        return storageFile;
    }

    public void deleteImage(final String mongoId) {
        StorageFile storageFile = storageRepository.findById(mongoId).orElseThrow(() -> new RuntimeException("Resource not found"));
        cloudinaryService.deleteFile(storageFile.getCloudinaryPublicId());
        storageRepository.deleteById(mongoId);
    }

    private StorageFile mapStorageFileModel(final CloudinaryModel cloudinaryImage) {
        return StorageFile.builder()
                .cloudinaryPublicId(cloudinaryImage.getPublicId())
                .cloudinaryFolder(cloudinaryImage.getFolder())
                .cloudinarySecureUrl(cloudinaryImage.getSecureUrl())
                .cloudUrl(cloudinaryImage.getSecureUrl())
                .fileName(cloudinaryImage.getOriginalFilename())
                .uploadTime(LocalDateTime.now())
                .build();
    }

}
