package com.eason.servicecompare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompareByUrlRequest {

    @URL
    private String originUrl;

    @URL
    private String comparedUrl;

}
