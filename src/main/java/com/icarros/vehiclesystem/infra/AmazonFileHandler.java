package com.icarros.vehiclesystem.infra;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.icarros.vehiclesystem.infra.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AmazonFileHandler implements FileHandler {
    private final AmazonS3 amazonS3;
    @Value(value = "${aws.s3.endpoint}")
    private String endpoint;

    @Autowired
    public AmazonFileHandler(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public boolean save(String json) {
        try {
            String objectKey = generateObjectKey();
            amazonS3.putObject(this.endpoint, objectKey, json);
            return true;
        } catch (AmazonS3Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String generateObjectKey() {
        String uniqueKey = UUID.randomUUID().toString();
        return uniqueKey + ".json";
    }
}