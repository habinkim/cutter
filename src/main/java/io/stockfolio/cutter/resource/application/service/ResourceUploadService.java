package io.stockfolio.cutter.resource.application.service;

import io.stockfolio.cutter.common.config.ServicePolicy;
import io.stockfolio.cutter.common.stereotype.UseCase;
import io.stockfolio.cutter.resource.application.port.input.ResourceUploadUseCase;
import io.stockfolio.cutter.resource.application.port.output.SaveResourcePort;
import io.stockfolio.cutter.resource.application.port.output.UploadFilePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ResourceUploadService implements ResourceUploadUseCase {

    private final UploadFilePort uploadFilePort;
    private final SaveResourcePort saveResourcePort;

    private final ServicePolicy servicePolicy;

    @Transactional
    @Override
    public void upload(List<MultipartFile> files) {
        servicePolicy.isVideoFiles(files);

        files.forEach(uploadFilePort::uploadFile);

    }

}
