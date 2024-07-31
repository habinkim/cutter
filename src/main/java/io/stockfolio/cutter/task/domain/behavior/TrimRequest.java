package io.stockfolio.cutter.task.domain.behavior;

import io.stockfolio.cutter.common.response.BasePayload;

public record TrimRequest(String ulid, Long trimStart, Long trimEnd) implements BasePayload {
}
