package io.stockfolio.cutter.resource.application.port.output;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFilePort {
    void uploadFile(@NotNull MultipartFile file);
}
