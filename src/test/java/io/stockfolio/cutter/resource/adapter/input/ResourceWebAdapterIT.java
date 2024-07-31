package io.stockfolio.cutter.resource.adapter.input;

import io.stockfolio.cutter.base.ControllerBaseTest;
import io.stockfolio.cutter.common.config.Uris;
import io.stockfolio.cutter.common.response.MessageCode;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;

import static io.stockfolio.cutter.base.RestDocsConfig.constraint;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class ResourceWebAdapterIT extends ControllerBaseTest {

    private static final String FILE_PATH = "file/file_example_MP4_1920_18MG.mp4";
    public static final String ORIGINAL_FILE_NAME = "file_example_MP4_1920_18MG.mp4";

    @Transactional
    @Test
    @Order(1)
    @DisplayName("파일 업로드, 성공")
    void uploadSuccess() throws Exception {

        FieldDescriptor[] responseDescriptors = ArrayUtils.addAll(baseResponseFields,
                fieldWithPath("data.saved_resources[]").description("업로드 파일 리스트").attributes(constraint("NOT NULL")),
                fieldWithPath("data.saved_resources[].ulid").description("업로드 파일 ULID").attributes(constraint("NOT NULL")),
                fieldWithPath("data.saved_resources[].saved_path").description("업로드 파일 저장 경로").attributes(constraint("NOT NULL")),
                fieldWithPath("data.saved_resources[].extension").description("업로드 파일 확장자").attributes(constraint("NOT NULL")),
                fieldWithPath("data.saved_resources[].size").description("업로드 파일 크기 (bytes)").attributes(constraint("NOT NULL")),
                fieldWithPath("data.saved_resources[].original_file_name").description("업로드 원본 파일명").attributes(constraint("NOT NULL"))
        );

        mockMvc.perform(
                        multipart(Uris.UPLOAD_V1)
                                .file(getFile())
                                .accept(APPLICATION_JSON)
                                .contentType("multipart/form-data")
                )
                .andExpectAll(baseAssertion(MessageCode.SUCCESS))
                .andExpectAll(
                        jsonPath("$.data.saved_resources", notNullValue()),
                        jsonPath("$.data.saved_resources", hasSize(1))
                )
                .andExpect(jsonPath("$.data.saved_resources[0].ulid", notNullValue()))
                .andExpect(jsonPath("$.data.saved_resources[0].saved_path", notNullValue()))
                .andExpect(jsonPath("$.data.saved_resources[0].extension", notNullValue()))
                .andExpect(jsonPath("$.data.saved_resources[0].size", notNullValue()))
                .andExpectAll(
                        jsonPath("$.data.saved_resources[0].original_file_name", notNullValue()),
                        jsonPath("$.data.saved_resources[0].original_file_name", is(ORIGINAL_FILE_NAME))
                )
                .andDo(restDocs.document(
                        responseFields(responseDescriptors)
                ));

    }

    private static MockMultipartFile getFile() throws IOException {
        ClassPathResource resource = new ClassPathResource(FILE_PATH);
        String contentType = Files.probeContentType(resource.getFile().toPath());
        return new MockMultipartFile("files", ORIGINAL_FILE_NAME, contentType, resource.getContentAsByteArray());
    }

}
