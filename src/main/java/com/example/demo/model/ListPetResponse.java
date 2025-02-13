package com.example.demo.model;

import java.util.List;

public class ListPetResponse {

    private ListPetDataResponse listPetDataResponse;

    private NotificationResponse notificationResponse;

    public ListPetDataResponse getListPetDataResponse() {
        return listPetDataResponse;
    }

    public void setListPetDataResponse(ListPetDataResponse listPetDataResponse) {
        this.listPetDataResponse = listPetDataResponse;
    }

    public NotificationResponse getNotificationResponse() {
        return notificationResponse;
    }

    public void setNotificationResponse(NotificationResponse notificationResponse) {
        this.notificationResponse = notificationResponse;
    }

    @Override
    public String toString() {
        return "ListPetResponse{" +
                "listPetDataResponse=" + listPetDataResponse +
                ", notificationResponse=" + notificationResponse +
                '}';
    }
}
