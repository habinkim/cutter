package io.stockfolio.cutter.resource.application.service;

import io.stockfolio.cutter.common.config.ServicePolicy;
import io.stockfolio.cutter.common.stereotype.UseCase;
import io.stockfolio.cutter.resource.application.port.input.ResourceUploadUseCase;
import io.stockfolio.cutter.resource.application.port.output.SaveResourcePort;
import io.stockfolio.cutter.resource.application.port.output.UploadFilePort;
import io.stockfolio.cutter.resource.domain.behavior.SaveResource;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 파일 업로드 비즈니스 로직
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class ResourceUploadService implements ResourceUploadUseCase {

    private final UploadFilePort uploadFilePort;
    private final SaveResourcePort saveResourcePort;

    private final ServicePolicy servicePolicy;

    @Transactional
    @Override
    public void upload(@NotNull @NotEmpty final List<MultipartFile> files) {
//        servicePolicy.isVideoFiles(files);

        log.info("file name : {}", files.getFirst().getName());

        files.forEach(uploadFilePort::uploadFile);
        toBehavior(files).forEach(saveResourcePort::save);

    }

    public static List<SaveResource> toBehavior(@NotNull @NotEmpty final List<MultipartFile> files) {
        return files.stream()
                .map(file -> new SaveResource(FilenameUtils.getExtension(file.getOriginalFilename()), file.getSize(),
                        file.getContentType(), file.getOriginalFilename()))
                .toList();
    }

}
