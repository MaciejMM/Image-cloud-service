package com.example.imagecloudservice.v1.storage;

import com.example.imagecloudservice.models.StorageFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends MongoRepository<StorageFile, String> {

}
