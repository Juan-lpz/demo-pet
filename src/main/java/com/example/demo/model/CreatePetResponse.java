package com.example.demo.model;

public class CreatePetResponse {

    private CreatePetDataResponse createPetDataResponse;

    private NotificationResponse notificationResponse;

    public CreatePetDataResponse getCreatePetDataResponse() {
        return createPetDataResponse;
    }

    public void setCreatePetDataResponse(CreatePetDataResponse createPetDataResponse) {
        this.createPetDataResponse = createPetDataResponse;
    }

    public NotificationResponse getNotificationResponse() {
        return notificationResponse;
    }

    public void setNotificationResponse(NotificationResponse notificationResponse) {
        this.notificationResponse = notificationResponse;
    }

    @Override
    public String toString() {
        return "CreatePetResponse{" +
                "createPetDataResponse=" + createPetDataResponse +
                ", notificationResponse=" + notificationResponse +
                '}';
    }
}
