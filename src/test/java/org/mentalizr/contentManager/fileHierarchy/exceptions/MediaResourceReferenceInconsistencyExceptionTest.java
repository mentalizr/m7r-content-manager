package org.mentalizr.contentManager.fileHierarchy.exceptions;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MediaResourceReferenceInconsistencyExceptionTest {

    @Test
    public void getMessage() {
        MediaResourceReferenceInconsistencyException exception = new MediaResourceReferenceInconsistencyException("fileName");
        assertEquals("Referenced media file is not available: [fileName].", exception.getMessage());
    }

//    @Test
//    public void getMessage2() {
//        MediaResourceReferenceInconsistencyException cause = new MediaResourceReferenceInconsistencyException("fileName");
//        Exception exception = new Exception(cause);
//
//        System.out.println(exception.getMessage());
//
//        assertEquals("Referenced media file is not available: [fileName].", exception.getMessage());
//    }

}