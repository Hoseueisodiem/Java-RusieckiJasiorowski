package org.example.projectmanagerapp.priority;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriorityLevelTest {

    @Test
    @DisplayName("HighPriority should return HIGH")
    void testHighPriority() {
        PriorityLevel priority = new HighPriority();
        assertEquals("HIGH", priority.getPriority());
    }

    @Test
    @DisplayName("MediumPriority should return MEDIUM")
    void testMediumPriority() {
        PriorityLevel priority = new MediumPriority();
        assertEquals("MEDIUM", priority.getPriority());
    }

    @Test
    @DisplayName("LowPriority should return LOW")
    void testLowPriority() {
        PriorityLevel priority = new LowPriority();
        assertEquals("LOW", priority.getPriority());
    }
}