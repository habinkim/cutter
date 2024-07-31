package io.stockfolio.cutter.resource.adapter.input;

import io.stockfolio.cutter.common.config.Uris;
import io.stockfolio.cutter.common.response.ResponseMapper;
import io.stockfolio.cutter.common.stereotype.WebAdapter;
import io.stockfolio.cutter.resource.application.port.input.ResourceUploadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Input Web Adapter for Resource
 */
@WebAdapter
@RequiredArgsConstructor
public class ResourceWebAdapter {

    private final ResourceUploadUseCase resourceUploadUseCase;

    private final ResponseMapper responseMapper;

    @PostMapping(value = Uris.UPLOAD_V1)
    public void upload(List<MultipartFile> files) {
        resourceUploadUseCase.upload(files);

        responseMapper.ok();
    }

}
