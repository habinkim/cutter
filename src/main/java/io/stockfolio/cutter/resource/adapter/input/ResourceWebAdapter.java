package io.stockfolio.cutter.resource.adapter.input;

import io.stockfolio.cutter.common.config.Uris;
import io.stockfolio.cutter.common.response.MessageCode;
import io.stockfolio.cutter.common.response.Response;
import io.stockfolio.cutter.common.response.ResponseMapper;
import io.stockfolio.cutter.common.stereotype.WebAdapter;
import io.stockfolio.cutter.resource.application.port.input.ResourceUploadUseCase;
import io.stockfolio.cutter.resource.domain.value.SavedResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value = Uris.RESOURCE_UPLOAD_V1)
    public ResponseEntity<Response<UploadResponse>> upload(@RequestParam(name = "files") List<MultipartFile> files) {
        List<SavedResource> savedResources = resourceUploadUseCase.upload(files);
        UploadResponse response = new UploadResponse(savedResources);

        return responseMapper.ok(MessageCode.SUCCESS, response);
    }

}
