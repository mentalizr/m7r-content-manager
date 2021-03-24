package org.mentalizr.contentManager.fileHierarchy.basics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NamingTest {

    @Test
    void isValidProgramName() {
        assertTrue(Naming.isValidProgramName("program"));
        assertTrue(Naming.isValidProgramName("program1"));
        assertTrue(Naming.isValidProgramName("program01"));
        assertTrue(Naming.isValidProgramName("program-01"));
        assertTrue(Naming.isValidProgramName("program_01"));
        assertTrue(Naming.isValidProgramName("program_01_and"));

        assertFalse(Naming.isValidProgramName("1"));
        assertFalse(Naming.isValidProgramName("1program"));
        assertFalse(Naming.isValidProgramName(".program"));
        assertFalse(Naming.isValidProgramName("_program"));
        assertFalse(Naming.isValidProgramName("something/like/that"));
        assertFalse(Naming.isValidProgramName("../something/like/that"));
        assertFalse(Naming.isValidProgramName("../something"));
    }

}