package com.eason.servicecompare.service;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class FetchResponseService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SAXReader saxReader;

    public Document fetchResponseByUrl(String url){
        try {
            return saxReader.read(new URL(url));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
