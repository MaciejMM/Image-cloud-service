package com.example.imagecloudservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("project-images")
public class StorageFile {

    @MongoId
    private String mongoId;
    private String cloudUrl;
    private String cloudinaryPublicId;
    private String cloudinaryFolder;
    private String cloudinarySecureUrl;
    private String fileName;
    private LocalDateTime uploadTime;

}
