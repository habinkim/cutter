package io.stockfolio.cutter.resource.adapter.output.storage;

import io.stockfolio.cutter.common.config.LocalStorageProperties;
import io.stockfolio.cutter.common.stereotype.StorageAdapter;
import io.stockfolio.cutter.resource.application.port.output.UploadFilePort;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@StorageAdapter
public class ResourceLocalStorageAdapter implements UploadFilePort {

    private final Path rootLocation;

    public ResourceLocalStorageAdapter(LocalStorageProperties properties) {
        this.rootLocation = Path.of(properties.getLocation());
    }

    @Override
    public void uploadFile(@NotNull MultipartFile file) {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) throw new StorageException("Failed to store empty file " + filename);

            if (filename.contains(".."))
                throw new StorageException("Cannot store file with relative path outside current directory " + filename);

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }

    }
}
