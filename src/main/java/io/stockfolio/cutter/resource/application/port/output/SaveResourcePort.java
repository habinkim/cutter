package io.stockfolio.cutter.resource.application.port.output;

import io.stockfolio.cutter.resource.domain.behavior.SaveResource;

public interface SaveResourcePort {

    void save(final SaveResource behavior);

}
