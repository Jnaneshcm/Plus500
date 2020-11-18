package com.page500.core.util;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageBean {

    @JsonProperty("elements")
    private HashMap<String,ElementLocatorBean> elements;

    public HashMap<String, ElementLocatorBean> getElements() {
        return elements;
    }


}