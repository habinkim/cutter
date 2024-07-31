package io.stockfolio.cutter.resource.application.port.output;

import io.stockfolio.cutter.resource.domain.value.SavedResource;

import java.util.Optional;

public interface GetResourcePort {
    Optional<SavedResource> getResource(final String ulid);
}
