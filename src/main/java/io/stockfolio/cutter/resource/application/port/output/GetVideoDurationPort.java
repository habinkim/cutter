package io.stockfolio.cutter.resource.application.port.output;

import jakarta.validation.constraints.NotBlank;

public interface GetVideoDurationPort {
    Integer getVideoDuration(@NotBlank final String savedPath);
}
