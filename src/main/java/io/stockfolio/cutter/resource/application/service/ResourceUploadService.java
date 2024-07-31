package io.stockfolio.cutter.resource.application.service;

import io.stockfolio.cutter.common.config.ServicePolicy;
import io.stockfolio.cutter.common.stereotype.UseCase;
import io.stockfolio.cutter.resource.application.port.input.ResourceUploadUseCase;
import io.stockfolio.cutter.resource.application.port.output.GetResourceDurationPort;
import io.stockfolio.cutter.resource.application.port.output.SaveResourcePort;
import io.stockfolio.cutter.resource.application.port.output.UploadFilePort;
import io.stockfolio.cutter.resource.domain.behavior.SaveResource;
import io.stockfolio.cutter.resource.domain.value.SavedResource;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.apache.commons.io.FilenameUtils.getExtension;

/**
 * 파일 업로드 비즈니스 로직
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class ResourceUploadService implements ResourceUploadUseCase {

    private final UploadFilePort uploadFilePort;
    private final SaveResourcePort saveResourcePort;
    private final GetResourceDurationPort getResourceDurationPort;

    private final ServicePolicy servicePolicy;

    @Transactional
    @Override
    public List<SavedResource> upload(@NotNull @NotEmpty final List<MultipartFile> files) {
        servicePolicy.isVideoFiles(files);

        log.info("file name : {}", files.getFirst().getName());

        return files.stream().map(file -> {
            String savedPath = uploadFilePort.uploadFile(file);
            Integer duration = getResourceDurationPort.getResourceDuration(savedPath);
            SaveResource behavior = toBehavior(file, savedPath, duration);
            return saveResourcePort.save(behavior);
        }).toList();
    }

    private static SaveResource toBehavior(final MultipartFile file, final String savedPath, final Integer duration) {
        return new SaveResource(getExtension(file.getOriginalFilename()), file.getSize(), file.getOriginalFilename(), savedPath, duration);
    }

}
