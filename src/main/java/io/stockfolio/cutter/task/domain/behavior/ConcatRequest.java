package io.stockfolio.cutter.task.domain.behavior;

import java.util.List;

public record ConcatRequest(List<String> ulids) {
}
