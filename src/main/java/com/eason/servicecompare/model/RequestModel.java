package com.eason.servicecompare.model;

import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.http.HttpMethod;

@Data
public class RequestModel {
    @URL
    private String url;

    private HttpMethod method;

    private String customCookie;
}
