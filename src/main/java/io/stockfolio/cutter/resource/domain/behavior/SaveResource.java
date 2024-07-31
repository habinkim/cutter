package io.stockfolio.cutter.resource.domain.behavior;

/**
 * SaveResource Behavior
 *
 * @param extension        확장자
 * @param size             파일 크기(byte)
 * @param contentType      컨텐츠 타입
 * @param originalFileName 원본 파일명
 */
public record SaveResource(String extension, Long size, String contentType, String originalFileName) {

}
