package org.example.projectmanagerapp.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExceptionTest {

    @Test
    @DisplayName("Test ErrorDetails")
    void testErrorDetails() {
        Date now = new Date();
        ErrorDetails details = new ErrorDetails(now, "Error message", "Error details");

        assertEquals(now, details.getTimestamp());
        assertEquals("Error message", details.getMessage());
        assertEquals("Error details", details.getDetails());
    }

    @Test
    @DisplayName("Test UserNotFoundException")
    void testUserNotFoundException() {
        UserNotFoundException ex = new UserNotFoundException("User not found");
        assertEquals("User not found", ex.getMessage());
    }

    @Test
    @DisplayName("Test GlobalExceptionHandler - UserNotFound")
    void testHandleUserNotFound() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        WebRequest request = mock(WebRequest.class);
        when(request.getDescription(false)).thenReturn("uri=/api/users");

        UserNotFoundException ex = new UserNotFoundException("Not found");
        ResponseEntity<?> response = handler.resourceNotFoundException(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("Test GlobalExceptionHandler - Global")
    void testHandleGlobal() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        WebRequest request = mock(WebRequest.class);
        when(request.getDescription(false)).thenReturn("uri=/api/test");

        Exception ex = new Exception("General error");
        ResponseEntity<?> response = handler.globalExceptionHandler(ex, request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}