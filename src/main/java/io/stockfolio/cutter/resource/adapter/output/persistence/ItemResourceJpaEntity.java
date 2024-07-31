package io.stockfolio.cutter.resource.adapter.output.persistence;

import com.github.f4b6a3.ulid.UlidCreator;
import io.stockfolio.cutter.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "item_resource")
public class ItemResourceJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    @Comment("Sequence")
    private Long id;

    @Builder.Default
    @NotBlank
    @Column(name = "ulid", nullable = false, updatable = false, unique = true)
    @Comment("ULID")
    private String ulid = UlidCreator.getMonotonicUlid().toString();

    @NotBlank
    @Column(name = "saved_path", nullable = false, updatable = false)
    @Comment("실제 저장 경로")
    private String savedPath;

    @NotBlank
    @Column(nullable = false, updatable = false)
    @Comment("확장자")
    private String extension;

    @NotNull
    @Column(nullable = false, updatable = false)
    @Comment("파일 크기(byte)")
    private Long size;

    @NotNull
    @Column(nullable = false, updatable = false)
    @Comment("동영상의 길이")
    private Integer duration;

    @NotBlank
    @Column(nullable = false, updatable = false)
    @Comment("원본 파일명")
    private String originalFileName;

}
