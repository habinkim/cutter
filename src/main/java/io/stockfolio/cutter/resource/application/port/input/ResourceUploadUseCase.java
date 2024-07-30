package io.stockfolio.cutter.resource.application.port.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FunctionalInterface
public interface ResourceUploadUseCase {

    void upload(@NotEmpty @NotNull List<MultipartFile> files);

}
