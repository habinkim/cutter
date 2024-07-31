package io.stockfolio.cutter.resource.adapter.output.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemResourceRepository extends JpaRepository<ItemResourceJpaEntity, Long> {
}
