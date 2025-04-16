package com.niveus.assignment4.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataNotFoundExceptionTest {

    @Test
     void testDataNotFoundException(){
        String errorMessage = "Data Not Found";
        DataNotFoundException exception= new DataNotFoundException(errorMessage);
        assertEquals(errorMessage,exception.getMessage());
    }
}