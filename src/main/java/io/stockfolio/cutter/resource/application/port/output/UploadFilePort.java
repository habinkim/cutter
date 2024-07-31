package io.stockfolio.cutter.resource.application.port.output;

import jakarta.validation.constraints.NotNull;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface UploadFilePort {
    String uploadFile(@NotNull MultipartFile file);

    default String generateFileName(@NotNull final MultipartFile file) {
        String generatedFileName = UUID.randomUUID().toString();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        assert extension != null;

        return generatedFileName + "." + extension;
    }
}
