package com.eason.servicecompare.service;

import com.eason.servicecompare.model.CompareRequest;
import com.eason.servicecompare.model.ComparedResult;
import com.eason.servicecompare.model.RequestModel;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompareResponseService {

    @Autowired
    private FetchResponseService fetchResponseService;

    public ComparedResult compare(CompareRequest compareByUrlRequest) {
//        Document originDoc = fetchResponseService.fetchResponseByUrl(compareByUrlRequest.getOriginRequest().getUrl());
//        Document comparedDoc = fetchResponseService.fetchResponseByUrl(compareByUrlRequest.getComparedRequest().getUrl());
//
//
//        Element originRoot = originDoc.getRootElement();
//        Element comparedRoot = comparedDoc.getRootElement();
//
//        filterSameNodes(originRoot, comparedRoot);
//
//        return new ComparedResult(originDoc.asXML(), comparedDoc.asXML());

        String originResponse = fetchResponseService.fetchResponseByRequest(compareByUrlRequest.getOriginRequest());
        String comparedResponse = fetchResponseService.fetchResponseByRequest(compareByUrlRequest.getComparedRequest());
        return new ComparedResult(originResponse, comparedResponse);
    }

    public String test(RequestModel requestModel) {
        return fetchResponseService.fetchResponseByRequest(requestModel);
    }


    public void filterSameNodes(Element originElement, Element comparedElement) {
        if (comparedElement == null) {
            return;
        }
        List<Attribute> listAttr = originElement.attributes();
        //compare attrs
        boolean isSame = true;
        for (Attribute attr : listAttr) {
            String originAttrName = attr.getName();
            String originAttrValue = attr.getValue();
            System.out.println("属性名称：" + originAttrName + "属性值：" + originAttrValue);
            Attribute comparedAttr = comparedElement.attribute(originAttrName);
            if (!compareValue(comparedAttr.getValue(), originAttrValue)) {
                isSame = false;
            }
        }

        //compare values
        if (originElement.isTextOnly()) {
            if (isSame && compareValue(originElement.getStringValue(), comparedElement.getStringValue())) {
                originElement.getParent().remove(originElement);
                comparedElement.getParent().remove(comparedElement);
            }
            return;
        } else {
            //compare child elements

            List<Element> listElement = originElement.elements();
            for (Element e : listElement) {
                List<Element> comparedElements = comparedElement.elements(e.getName());
                for (Element ce : comparedElements) {
                    this.filterSameNodes(e, ce);
                }
            }
        }
    }


    private boolean compareValue(String val1, String val2) {
        //TODO 若为url 需要比对参数是否一致
        return val1.equals(val2);
    }

}
