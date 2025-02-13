package com.example.demo.mapper;

import com.example.demo.exception.PetException;
import com.example.demo.model.*;
import com.example.demo.repository.PetDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.crypto.Data;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PetMapper {

    private static final Logger logger = LogManager.getLogger(PetMapper.class);


    public static ApiCreatePetRequest requestCreatePetApiMap(CreatePetRequest createPetRequest) {
        ApiCreatePetRequest apiCreatePetRequest = new ApiCreatePetRequest();

        apiCreatePetRequest.setId(createPetRequest.getId());
        apiCreatePetRequest.setName(createPetRequest.getName());
        apiCreatePetRequest.setPhotoUrls(createPetRequest.getPhotoUrls());
        apiCreatePetRequest.setStatus(createPetRequest.getStatus());

        if (createPetRequest.getCategory() != null) {
            Category category = createPetRequest.getCategory();
            apiCreatePetRequest.setCategory(new Category(category.getId(), category.getName()));
        }
        if (createPetRequest.getTags() != null) {
            List<Tag> tags = createPetRequest.getTags().stream()
                    .map(tag -> new Tag(tag.getId(), tag.getName()))
                    .collect(Collectors.toList());
            apiCreatePetRequest.setTags(tags);
        }

        return apiCreatePetRequest;
    }


    public static CreatePetResponse createPetResponseMap(ApiCreatePetResponse apiCreatePetResponse) {

        CreatePetResponse createPetResponse = new CreatePetResponse();
        CreatePetDataResponse createPetDataResponse = new CreatePetDataResponse();

        createPetDataResponse.setId(apiCreatePetResponse.getId());
        createPetDataResponse.setName(apiCreatePetResponse.getName());
        createPetDataResponse.setStatus(apiCreatePetResponse.getStatus());
        createPetDataResponse.setPhotoUrls(apiCreatePetResponse.getPhotoUrls());
        createPetDataResponse.setTags(apiCreatePetResponse.getTags());
        createPetDataResponse.setTransactionId(UUID.randomUUID().toString());
        createPetDataResponse.setDateCreated(OffsetDateTime.now().toString());

        if (apiCreatePetResponse.getCategory() != null) {
            createPetDataResponse.setCategory(new Category(
                    apiCreatePetResponse.getCategory().getId(),
                    apiCreatePetResponse.getCategory().getName()
            ));
        }

        createPetResponse.setCreatePetDataResponse(createPetDataResponse);
        NotificationResponse notification = new NotificationResponse();
        notification.setCode("001");
        notification.setMessage("Operacion realizada correctamente");
        notification.setTimestamp(OffsetDateTime.now().toString());
        createPetResponse.setNotificationResponse(notification);
        return createPetResponse;
    }


    public static ListPetResponse getListPetResponseMap(GetPetApiResponse getPetApiResponse) {

        ListPetResponse listPetResponse = new ListPetResponse();

        ListPetDataResponse listPetDataResponse = new ListPetDataResponse();

        listPetDataResponse.setId(getPetApiResponse.getId());
        listPetDataResponse.setCategory(getPetApiResponse.getCategory());
        listPetDataResponse.setName(getPetApiResponse.getName());
        listPetDataResponse.setPhotoUrls(getPetApiResponse.getPhotoUrls());
        listPetDataResponse.setTags(getPetApiResponse.getTags());
        listPetDataResponse.setStatus(getPetApiResponse.getStatus());

        listPetResponse.setListPetDataResponse(listPetDataResponse);

        NotificationResponse notification = new NotificationResponse();
        notification.setCode("001");
        notification.setMessage("Operacion realizada correctamente");
        notification.setTimestamp(OffsetDateTime.now().toString());

        listPetResponse.setNotificationResponse(notification);

        return listPetResponse;
    }



    public static ListPetResponse listPetResponseErrorMap(PetException e) {
        logger.info(">> Mapeando error propagado al controlador");

        ListPetResponse listPetResponse = new ListPetResponse();
        listPetResponse.setListPetDataResponse(null);
        NotificationResponse notification = new NotificationResponse();
        notification.setCode(e.getCode());
        notification.setMessage(e.getMensaje());
        notification.setTimestamp(OffsetDateTime.now().toString());
        listPetResponse.setNotificationResponse(notification);
        return listPetResponse;
    }


    public static CreatePetResponse createPetResponseErrorMap(PetException e) {
        logger.info(">> Mapeando error");
        CreatePetResponse createPetResponse = new CreatePetResponse();
        createPetResponse.setCreatePetDataResponse(null);

        NotificationResponse notification = new NotificationResponse();
        notification.setCode(e.getCode());
        notification.setMessage(e.getMensaje());
        notification.setTimestamp(OffsetDateTime.now().toString());
        createPetResponse.setNotificationResponse(notification);
        return createPetResponse;
    }
}
