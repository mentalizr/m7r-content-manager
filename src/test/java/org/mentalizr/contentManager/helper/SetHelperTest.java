package org.mentalizr.contentManager.helper;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SetHelperTest {

    @Test
    void subtract() {
        Set<String> setA = Set.of("A", "B", "C");
        Set<String> setB = Set.of("C");
        Set<String> set = SetHelper.subtract(setA, setB);

        assertEquals(2, set.size());
        assertTrue(set.contains("A"));
        assertTrue(set.contains("B"));
    }

    @Test
    void subtract_emptySubtrahend() {
        Set<String> setA = Set.of("A", "B", "C");
        Set<String> setB = Set.of();
        Set<String> set = SetHelper.subtract(setA, setB);

        assertEquals(3, set.size());
        assertTrue(set.contains("A"));
        assertTrue(set.contains("B"));
        assertTrue(set.contains("C"));
    }

    @Test
    void subtract_irrelevantSubtrahend() {
        Set<String> setA = Set.of("A", "B", "C");
        Set<String> setB = Set.of("D");
        Set<String> set = SetHelper.subtract(setA, setB);

        assertEquals(3, set.size());
        assertTrue(set.contains("A"));
        assertTrue(set.contains("B"));
        assertTrue(set.contains("C"));
    }

    @Test
    void subtract_emptyMinuend() {
        Set<String> setA = Set.of();
        Set<String> setB = Set.of("C");
        Set<String> set = SetHelper.subtract(setA, setB);

        assertEquals(0, set.size());
    }

}