package com.page500.core.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ElementLocatorBean {

    @JsonProperty("Locator type")
    private String locatorType;

    @JsonProperty("value")
    private String value;

    @JsonProperty("Locator type")
    public String getLocatorType() {
        return locatorType;
    }

    @JsonProperty("Locator type")
    public void setLocatorType(String locatorType) {
        this.locatorType = locatorType;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    

    
   
}

