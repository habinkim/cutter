package io.stockfolio.cutter.common.config;

public class Uris {

    private Uris() {
    }

    public static final String API_V1_ROOT = "/v1";

    public static final String RESOURCE_V1_ROOT = API_V1_ROOT + "/resources";
    public static final String RESOURCE_UPLOAD_V1 = RESOURCE_V1_ROOT + "/upload";

    public static final String TASK_V1_ROOT = API_V1_ROOT + "/tasks";
    public static final String TASK_TRIM_V1 = TASK_V1_ROOT + "/trim";
    public static final String TASK_CONCAT_V1 = TASK_V1_ROOT + "/concat";



}
