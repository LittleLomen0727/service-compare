package com.eason.servicecompare.controller;


import com.eason.servicecompare.model.CompareByUrlRequest;
import com.eason.servicecompare.model.ComparedResult;
import com.eason.servicecompare.service.CompareResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("compare")
public class CompareController {

    @Autowired
    private CompareResponseService compareResponseService;

    @RequestMapping(value = "/url", method = RequestMethod.POST)
    public ComparedResult compareServiceByUrl(@Valid @RequestBody CompareByUrlRequest compareByUrlRequest){
        return compareResponseService.compare(compareByUrlRequest);
    }
}
