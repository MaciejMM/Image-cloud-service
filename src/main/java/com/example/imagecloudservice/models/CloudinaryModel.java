package com.example.imagecloudservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CloudinaryModel {
    private String signature;
    private String format;
    private String resourceType;
    private String secureUrl;
    private String createdAt;
    private String assetId;
    private String versionId;
    private String type;
    private Integer version;
    private String url;
    private String publicId;
    private List<String> tags;
    private String folder;
    private String originalFilename;
    private String apiKey;
    private Integer bytes;
    private Integer width;
    private String etag;
    private Boolean placeholder;
    private Integer height;
}
