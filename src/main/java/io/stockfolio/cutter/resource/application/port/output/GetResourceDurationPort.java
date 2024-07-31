package io.stockfolio.cutter.resource.application.port.output;

import jakarta.validation.constraints.NotBlank;

public interface GetResourceDurationPort {
    Integer getResourceDuration(@NotBlank final String savedPath);
}
