package io.stockfolio.cutter.resource.adapter.output.persistence;

import io.stockfolio.cutter.common.stereotype.PersistenceAdapter;
import io.stockfolio.cutter.resource.application.port.output.SaveResourcePort;
import io.stockfolio.cutter.resource.domain.behavior.SaveResource;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ResourcePersistenceAdapter implements SaveResourcePort {

    private final ItemResourceRepository itemResourceRepository;

    @Override
    public void save(final SaveResource behavior) {
        ItemResourceJpaEntity entity = ItemResourceJpaEntity.builder()
                .savedPath(behavior.savedPath())
                .extension(behavior.extension())
                .size(behavior.size())
                .originalFileName(behavior.originalFileName())
                .build();

        itemResourceRepository.save(entity);
    }
}
