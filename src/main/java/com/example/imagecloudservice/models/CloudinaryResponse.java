package com.example.imagecloudservice.models;

import java.util.List;
import java.util.Map;

public class CloudinaryResponse {

    public CloudinaryModel map(final Map<String, Object> cloudinaryResponse, final String originalFileName) {
        return CloudinaryModel
                .builder()
                .signature((String) cloudinaryResponse.get("signature"))
                .format((String) cloudinaryResponse.get("format"))
                .resourceType((String) cloudinaryResponse.get("resource_type"))
                .secureUrl((String) cloudinaryResponse.get("secure_url"))
                .createdAt((String) cloudinaryResponse.get("created_at"))
                .assetId((String) cloudinaryResponse.get("asset_id"))
                .versionId((String) cloudinaryResponse.get("version_id"))
                .type((String) cloudinaryResponse.get("type"))
                .version((Integer) cloudinaryResponse.get("version"))
                .url((String) cloudinaryResponse.get("url"))
                .publicId((String) cloudinaryResponse.get("public_id"))
                .tags((List<String>) cloudinaryResponse.get("tags"))
                .folder((String) cloudinaryResponse.get("folder"))
                .originalFilename(originalFileName)
                .apiKey(null)
                .bytes((Integer) cloudinaryResponse.get("bytes"))
                .width((Integer) cloudinaryResponse.get("width"))
                .etag((String) cloudinaryResponse.get("etag"))
                .placeholder((Boolean) cloudinaryResponse.get("placeholder"))
                .signature((String) cloudinaryResponse.get("signature"))
                .height((Integer) cloudinaryResponse.get("height"))
                .build();

    }
}
