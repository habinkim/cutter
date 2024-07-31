package io.stockfolio.cutter.resource.application.port.input;

import io.stockfolio.cutter.resource.domain.value.SavedResource;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FunctionalInterface
public interface ResourceUploadUseCase {

    List<SavedResource> upload(@NotEmpty @NotNull List<MultipartFile> files);

}
