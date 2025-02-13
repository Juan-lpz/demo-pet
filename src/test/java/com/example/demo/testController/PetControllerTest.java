package com.example.demo.testController;

import com.example.demo.controller.PetController;
import com.example.demo.model.CreatePetRequest;
import com.example.demo.model.CreatePetResponse;
import com.example.demo.model.ListPetResponse;
import com.example.demo.service.PetService;
import com.example.demo.exception.PetException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PetControllerTest {

    @InjectMocks
    PetController petController;

    @Mock
    PetService petService;

    @Before
    public void setUp() {

    }

    @Test
    public void testGetPetById_Success() throws PetException {
        Long petId = 1L;
        ListPetResponse expectedResponse = new ListPetResponse();
        when(petService.getPetById(petId)).thenReturn(expectedResponse);

        ResponseEntity<ListPetResponse> response = petController.getPetById(petId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testGetPetById_Error() throws PetException {
        Long petId = 1L;
        PetException petException = new PetException( HttpStatus.INTERNAL_SERVER_ERROR,"Pet not found","E500");
        when(petService.getPetById(petId)).thenThrow(petException);

        ResponseEntity<ListPetResponse> response = petController.getPetById(petId);
        assertNull(response.getBody().getListPetDataResponse());
    }

    @Test
    public void testInsertPet_Success() throws PetException {
        CreatePetRequest createPetRequest = new CreatePetRequest();
        CreatePetResponse expectedResponse = new CreatePetResponse();
        when(petService.createPet(createPetRequest)).thenReturn(expectedResponse);

        ResponseEntity<CreatePetResponse> response = petController.insertPet(createPetRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testInsertPet_Error() throws PetException {
        CreatePetRequest createPetRequest = new CreatePetRequest();
        PetException petException = new PetException( HttpStatus.INTERNAL_SERVER_ERROR,"Pet not create","E500");
        when(petService.createPet(createPetRequest)).thenThrow(petException);

        ResponseEntity<CreatePetResponse> response = petController.insertPet(createPetRequest);
        assertNull(response.getBody().getCreatePetDataResponse());

    }
}
