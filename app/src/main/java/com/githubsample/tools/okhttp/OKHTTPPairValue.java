package com.githubsample.tools.okhttp;

public class OKHTTPPairValue {

    private String key, value;

    public OKHTTPPairValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
