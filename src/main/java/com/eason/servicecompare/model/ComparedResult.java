package com.eason.servicecompare.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComparedResult {

    private String originResult;

    private String comparedResult;
}
