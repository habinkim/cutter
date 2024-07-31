package io.stockfolio.cutter.resource.domain.behavior;

/**
 * SaveResource Behavior
 *
 * @param savedPath        실제 저장 경로
 * @param extension        확장자
 * @param size             파일 크기(byte)
 * @param originalFileName 원본 파일명
 */
public record SaveResource(String savedPath, String extension, Long size, String originalFileName) {

}
