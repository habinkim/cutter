package io.stockfolio.cutter.resource.domain.value;

import io.stockfolio.cutter.common.response.BasePayload;

/**
 * 저장된 리소스 정보
 *
 * @param ulid             ULID
 * @param savedPath        저장된 경로
 * @param extension        확장자
 * @param size             파일 크기
 * @param originalFileName 원본 파일 이름
 * @param duration         동영상 길이
 */
public record SavedResource(String ulid, String savedPath, String extension, Long size,
                            String originalFileName, Integer duration) implements BasePayload {
}
