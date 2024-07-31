package io.stockfolio.cutter.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "local-storage")
@Component
@Setter
@Getter
public class LocalStorageProperties {

    /**
     * Folder location for storing files
     */
    private String location;


}
