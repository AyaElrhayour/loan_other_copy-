package com.youcode.demo.service;

import com.youcode.demo.entity.Status;
import com.youcode.demo.repository.StatusInterface;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;


class StatusServiceTest {

    @Mock
    private StatusInterface statusInterface;

    @InjectMocks
    private StatusService statusService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenValidStatusThenDoesNotThrowException() {

        Status newStatus = new Status();
        newStatus.setStatus("New Status");
        when(statusInterface.getByStatus(newStatus.getStatus())).thenReturn(Optional.empty());
        assertDoesNotThrow(() -> statusService.addStatus(newStatus));
        verify(statusInterface, times(1)).addStatus(newStatus);
    }

    @Test
    void givenStatusAlreadyExistsThenThrowsException() {
        Status existingStatus = new Status();
        existingStatus.setStatus("Existing Status");
        when(statusInterface.getByStatus(existingStatus.getStatus())).thenReturn(Optional.of(existingStatus));
        Exception exception = assertThrows(Exception.class, () -> {
            statusService.addStatus(existingStatus);
        });
        assertEquals("Status already exists.", exception.getMessage());
        verify(statusInterface, never()).addStatus(any(Status.class));
    }

    @Test
    void givenNullStatusThenThrowsException() {
        Status nullStatus = new Status();
        nullStatus.setStatus(null);
        Exception exception = assertThrows(Exception.class, () -> {
            statusService.addStatus(nullStatus);
        });
        assertEquals("Invalid status information.", exception.getMessage());
        verify(statusInterface, never()).addStatus(any(Status.class));
    }

    @Test
    void givenEmptyStatusThenThrowsException() {
        Status emptyStatus = new Status();
        emptyStatus.setStatus("");
        Exception exception = assertThrows(Exception.class, () -> {
            statusService.addStatus(emptyStatus);
        });
        assertEquals("Invalid status information.", exception.getMessage());
        verify(statusInterface, never()).addStatus(any(Status.class));
    }


}