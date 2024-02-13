package com.example.imagecloudservice.v1.storage;


import com.example.imagecloudservice.models.StorageFile;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/storage")
public class StorageController {

    private final StorageService storageService;

    public StorageController(final StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    List<StorageFile> getImages() {
        List<StorageFile> images = storageService.getImages();
        return ResponseEntity.status(HttpStatus.OK).body(images).getBody();
    }


    @PostMapping("/upload")
    StorageFile uploadImage(
            @RequestParam("file") final MultipartFile file,
            @RequestParam("folder") @NotNull final String folderName) {
        StorageFile storageFile = storageService.uploadImage(file, folderName);
        return ResponseEntity.status(HttpStatus.OK).body(storageFile).getBody();

    }

    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, String>> deleteImage(@PathVariable("id") final String id) {
        storageService.deleteImage(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "Resource successfully deleted"
        ));

    }


}
