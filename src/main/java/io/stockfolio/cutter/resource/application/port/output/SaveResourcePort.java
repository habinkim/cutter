package io.stockfolio.cutter.resource.application.port.output;

import io.stockfolio.cutter.resource.domain.behavior.SaveResource;
import io.stockfolio.cutter.resource.domain.value.SavedResource;

public interface SaveResourcePort {

    SavedResource save(final SaveResource behavior);

}
