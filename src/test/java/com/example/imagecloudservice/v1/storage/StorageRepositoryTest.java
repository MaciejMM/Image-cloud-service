package com.example.imagecloudservice.v1.storage;

import com.example.imagecloudservice.models.StorageFile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
class StorageRepositoryTest {

    StorageFile storageFile1 = new StorageFile("mongoid12345",
            "https://testurl.com/2",
            "abcd1234efgh",
            "hemet-folder",
            "https://testurl.com/1",
            "filename1.webp",
            LocalDateTime.of(2024, 2, 10, 15, 30));
    StorageFile storageFile2 = new StorageFile("mongoid1234523",
            "https://testurl.com/2",
            "fsdfsf21312",
            "hemet-folder",
            "https://testurl.com/2",
            "filename2.webp",
            LocalDateTime.of(2024, 2, 10, 15, 30));


    @Autowired
    StorageRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void shouldReturnImages() {

        //when
        underTest.save(storageFile1);
        underTest.save(storageFile2);

        //then
        assertTrue(underTest.findById("mongoid12345").isPresent());
        assertTrue(underTest.findById("mongoid1234523").isPresent());
        assertEquals(2, underTest.findAll().size());
        assertNotNull(underTest.findAll());

    }

    @Test
    void shouldSaveImageInDatabase() {
        //given
        int numberOfDbRecords = 1;

        //when
        StorageFile savedFile = underTest.save(storageFile1);

        //then
        assertNotNull(savedFile);
        assertEquals(numberOfDbRecords, underTest.findAll().size());
        assertEquals("mongoid12345", savedFile.getMongoId());
        assertEquals("https://testurl.com/2", savedFile.getCloudUrl());
        assertEquals("abcd1234efgh", savedFile.getCloudinaryPublicId());
        assertEquals("hemet-folder", savedFile.getCloudinaryFolder());
        assertEquals("https://testurl.com/1", savedFile.getCloudinarySecureUrl());
        assertEquals("filename1.webp", savedFile.getFileName());

    }

    @Test
    void shouldDeleteImageDatabase() {

        //given
        StorageFile savedFile = underTest.save(storageFile1);

        //when
        Optional<StorageFile> retrievedFileOptional = underTest.findById(savedFile.getMongoId());

        //then
        assertTrue(retrievedFileOptional.isPresent());
        assertEquals(1,underTest.findAll().size());

        //delete file
        underTest.deleteById(savedFile.getMongoId());
        assertEquals(0,underTest.findAll().size());

    }
}