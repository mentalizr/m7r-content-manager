package org.mentalizr.contentManager;

import org.junit.jupiter.api.Test;

import java.io.File;

public class TestTest {

    @Test
    public void test() {
        File file = new File("test.txt");
        System.out.println(file.getAbsoluteFile());
    }

}
