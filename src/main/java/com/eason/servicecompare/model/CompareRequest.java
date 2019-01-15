package com.eason.servicecompare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompareRequest {

    private RequestModel originRequest;

    private RequestModel comparedRequest;

}
