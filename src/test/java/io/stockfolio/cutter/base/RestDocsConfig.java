package io.stockfolio.cutter.base;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.snippet.Attributes.Attribute;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

@TestConfiguration
public class RestDocsConfig {

    public static final String CONSTRAINT = "constraint";

    public static Attribute field(
            final String key,
            final String value) {
        return new Attribute(key, value);
    }

    public static Attribute constraint(
            final String value) {
        return field(CONSTRAINT, value);
    }

    @Bean
    public RestDocumentationResultHandler write() {
        return document(
                "{method-name}",
                DocumentUtil.getDocumentRequest(),
                DocumentUtil.getDocumentResponse()
        );
    }

}
