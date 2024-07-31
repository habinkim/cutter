package io.stockfolio.cutter.resource.adapter.output.persistence;

import io.stockfolio.cutter.common.stereotype.PersistenceAdapter;
import io.stockfolio.cutter.resource.application.port.output.SaveResourcePort;
import io.stockfolio.cutter.resource.domain.behavior.SaveResource;
import io.stockfolio.cutter.resource.domain.value.SavedResource;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ResourcePersistenceAdapter implements SaveResourcePort {

    private final ItemResourceRepository itemResourceRepository;

    @Override
    public SavedResource save(final SaveResource behavior) {
        ItemResourceJpaEntity entity = ItemResourceJpaEntity.builder()
                .savedPath(behavior.savedPath())
                .extension(behavior.extension())
                .size(behavior.size())
                .originalFileName(behavior.originalFileName())
                .duration(behavior.duration())
                .build();

        ItemResourceJpaEntity savedEntity = itemResourceRepository.save(entity);

        return new SavedResource(savedEntity.getUlid(), savedEntity.getSavedPath(), savedEntity.getExtension(),
                savedEntity.getSize(), savedEntity.getOriginalFileName(), savedEntity.getDuration());
    }
}
