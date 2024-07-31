package io.stockfolio.cutter.resource.adapter.output.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemResourceRepository extends JpaRepository<ItemResourceJpaEntity, Long> {
    Optional<ItemResourceJpaEntity> findByUlid(final String ulid);
}
