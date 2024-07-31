package io.stockfolio.cutter.common.config;

import io.stockfolio.cutter.common.exception.CommonApplicationException;
import io.stockfolio.cutter.common.response.MessageCode;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ServicePolicy {

    public void isVideoFiles(@NotNull final List<MultipartFile> files) {
        for (MultipartFile file : files) isVideoFile(file);
    }

    public Boolean isVideoFile(@NotNull final MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        assert extension != null;

        if (extension.equalsIgnoreCase("MP4")
                || extension.equalsIgnoreCase("MOV")
                || extension.equalsIgnoreCase("AVI"))
            return true;
        else
            throw new CommonApplicationException(MessageCode.FILE_IS_NOT_VIDEO);
    }

}
