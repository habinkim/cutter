package io.stockfolio.cutter.resource.application.service;

import io.stockfolio.cutter.common.stereotype.UseCase;
import io.stockfolio.cutter.resource.application.port.input.ResourceUploadUseCase;
import io.stockfolio.cutter.resource.application.port.output.UploadFilePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ResourceUploadService implements ResourceUploadUseCase {

    private final UploadFilePort uploadFilePort;

    @Override
    public void upload(List<MultipartFile> files) {

    }

}
