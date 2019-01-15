package com.eason.servicecompare.service;

import com.eason.servicecompare.model.RequestModel;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

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

    public String fetchResponseByRequest(RequestModel requestModel) {
        HttpHeaders httpHeaders = new HttpHeaders();
        String customCookie = requestModel.getCustomCookie();
        if (customCookie != null && !customCookie.equals("")) {
            httpHeaders.add("Cookie", customCookie);
        }
        HttpEntity<String> stringHttpEntity = new HttpEntity<>("", httpHeaders);
        return restTemplate.exchange(requestModel.getUrl(), requestModel.getMethod(), stringHttpEntity, String.class, new HashMap<>()).getBody();
    }

}
