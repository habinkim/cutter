package io.stockfolio.cutter.resource.adapter.output.persistence;

import io.stockfolio.cutter.common.stereotype.PersistenceAdapter;
import io.stockfolio.cutter.resource.application.port.output.GetResourcePort;
import io.stockfolio.cutter.resource.application.port.output.SaveResourcePort;
import io.stockfolio.cutter.resource.domain.behavior.SaveResource;
import io.stockfolio.cutter.resource.domain.value.SavedResource;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class ResourcePersistenceAdapter implements SaveResourcePort, GetResourcePort {

    private final ItemResourceRepository itemResourceRepository;
    private final ItemResourceMapper itemResourceMapper;

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
        return itemResourceMapper.savedResource(savedEntity);
    }

    @Override
    public Optional<SavedResource> getResource(String ulid) {
        return itemResourceRepository.findByUlid(ulid).map(itemResourceMapper::savedResource);
    }
}
