package com.dianaszanto.jobsearchapi.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class ApiKeyUtil {

    public static UUID generateApiKey() {
        return UUID.randomUUID();
    }
}
