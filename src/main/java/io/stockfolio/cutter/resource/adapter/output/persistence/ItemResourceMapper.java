package io.stockfolio.cutter.resource.adapter.output.persistence;

import io.stockfolio.cutter.common.config.BaseMapperConfig;
import io.stockfolio.cutter.resource.domain.value.SavedResource;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public abstract class ItemResourceMapper {

    public abstract SavedResource savedResource(final ItemResourceJpaEntity entity);

}
