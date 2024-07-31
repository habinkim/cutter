package io.stockfolio.cutter.resource.adapter.input;

import io.stockfolio.cutter.common.response.BasePayload;
import io.stockfolio.cutter.resource.domain.value.SavedResource;

import java.util.List;

public record UploadResponse(List<SavedResource> savedResources) implements BasePayload {
}
