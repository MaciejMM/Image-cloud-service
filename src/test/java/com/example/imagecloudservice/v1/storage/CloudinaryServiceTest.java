package com.example.imagecloudservice.v1.storage;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.example.imagecloudservice.models.CloudinaryModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CloudinaryServiceTest {

    @Mock
    private Cloudinary cloudinary;
    @Mock
    private Uploader uploader;

    @InjectMocks
    private CloudinaryService cloudinaryService;


    @Test
    void shouldUploadFile() throws IOException {

        //given
        String folderName = "testFolder";
        String originalFilename = "test.jpg";
        byte[] fileBytes = "test file content".getBytes();
        Map<String, Object> cloudinaryResponse = new HashMap<>();
        cloudinaryResponse.put("secure_url", "http://example.com/image.jpg");
        cloudinaryResponse.put("signature", "test signarture");
        cloudinaryResponse.put("url", "https://testurl.com");
        MockMultipartFile multipartFile = new MockMultipartFile(
                "file",
                originalFilename,
                "image/jpeg",
                fileBytes
        );

        //when
        when(uploader.upload(eq(fileBytes), anyMap())).thenReturn(cloudinaryResponse);
        when(cloudinary.uploader()).thenReturn(uploader);

        CloudinaryModel result = cloudinaryService.uploadFile(multipartFile, folderName);

        verify(cloudinary.uploader()).upload(eq(fileBytes), anyMap());

        //then
        assertNotNull(result);
        assertEquals(cloudinaryResponse.get("secure_url"), result.getSecureUrl());
        assertEquals(cloudinaryResponse.get("signature"), result.getSignature());
        assertEquals(cloudinaryResponse.get("url"), result.getUrl());
        assertEquals(originalFilename, result.getOriginalFilename());
    }

    @Test
    void shouldThrowException(){

        //given
        String folderName = "testFolder";
        MockMultipartFile multipartFile = null;

        //when then
        assertThrows(RuntimeException.class, () -> {
            cloudinaryService.uploadFile(multipartFile, folderName);
        });
    }

    @Test
    void shouldDeleteFile() throws IOException {

        //when
        HashMap<String, String> map = new HashMap<>();
        map.put("result", "ok");
        when(cloudinary.uploader()).thenReturn(uploader);
        when(uploader.destroy("testPublicId", null)).thenReturn(map);
        HashMap<String, String> testResult = cloudinaryService.deleteFile("testPublicId");

        // Then
        assertNotNull(testResult);
        assertEquals("ok",testResult.get("result"));
    }

    @Test
    void shouldHandleIOExceptionWhenDeleteFails() throws IOException {
        //given

        //when
        when(cloudinary.uploader()).thenReturn(uploader);
        when(uploader.destroy("testPublicId", null)).thenThrow(new IOException("Mocked IOException"));

        //when then
        assertThrows(RuntimeException.class, () -> {
            cloudinaryService.deleteFile("testPublicId");
        }, "RuntimeException should be thrown");
    }


}