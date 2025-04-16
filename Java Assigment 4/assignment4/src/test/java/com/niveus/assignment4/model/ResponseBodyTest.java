package com.niveus.assignment4.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponseBodyTest {

    @Test
     void testGetSetStatusCode(){
        ResponseBody responseBody= new ResponseBody();
        responseBody.setStatusCode("200");
        assertEquals("200",responseBody.getStatusCode());
    }

    @Test
     void testGetSetStatusMessage(){
        ResponseBody responseBody= new ResponseBody();
        responseBody.setStatusMessage("OK");
        assertEquals("OK",responseBody.getStatusMessage());
    }

    @Test
     void testGetSetErrorDescription(){
        ResponseBody responseBody= new ResponseBody();
        responseBody.setErrorDescription("Data Not Found");
        assertEquals("Data Not Found",responseBody.getErrorDescription());
    }
}