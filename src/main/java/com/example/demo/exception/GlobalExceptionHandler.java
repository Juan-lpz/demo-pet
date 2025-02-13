package com.example.demo.exception;

import com.example.demo.model.NotificationResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    // Manejo de excepciones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<NotificationResponse>> handleGeneralException(Exception e) {
        logger.error("Error inesperado atrapado por el handler: ", e);

        List<NotificationResponse> notificationList = new ArrayList<>();
        NotificationResponse notification = new NotificationResponse();

        notification.setCode("E500");
        notification.setMessage("Ocurrió una excepción inesperada atrapada por el Handler: " + e.getMessage());
        notification.setTimestamp(OffsetDateTime.now().toString());

        notificationList.add(notification);

        return new ResponseEntity<>(notificationList, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
