package com.example.demo.testRepository;

import com.example.demo.exception.PetException;
import com.example.demo.model.ApiCreatePetRequest;
import com.example.demo.model.ApiCreatePetResponse;
import com.example.demo.model.GetPetApiResponse;
import com.example.demo.repository.PetDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PetDaoTest {

    @InjectMocks
    PetDao petDao;

    @Mock
    RestTemplate restTemplate;

    @Before
    public void setUp() {
    }

    @Test
    public void getPetById_Success() throws PetException {
        Long petId = 1L;
        GetPetApiResponse mockResponse = new GetPetApiResponse();
        mockResponse.setId(petId);
        mockResponse.setName("Fido");

        ResponseEntity<GetPetApiResponse> responseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(GetPetApiResponse.class)))
                .thenReturn(responseEntity);

        GetPetApiResponse actualResponse = petDao.getPetById(petId);

        assertNotNull(actualResponse);
        assertEquals(petId, actualResponse.getId());
        assertEquals("Fido", actualResponse.getName());
    }

    @Test(expected = PetException.class)
    public void getPetById_Error() throws PetException {
        Long petId = 1L;


        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(GetPetApiResponse.class)))
                .thenThrow(new RuntimeException("Service unavailable"));
        petDao.getPetById(petId);
    }

    @Test
    public void createPet_Success() throws PetException {
        ApiCreatePetRequest createPetRequest = new ApiCreatePetRequest();
        ApiCreatePetResponse mockResponse = new ApiCreatePetResponse();
        mockResponse.setId(1L);
        mockResponse.setStatus("available");

        ResponseEntity<ApiCreatePetResponse> responseEntity = new ResponseEntity<>(mockResponse, HttpStatus.CREATED);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(), eq(ApiCreatePetResponse.class)))
                .thenReturn(responseEntity);

        ApiCreatePetResponse actualResponse = petDao.createPet(createPetRequest);

        assertNotNull(actualResponse);
        assertEquals("available", actualResponse.getStatus());
        assertEquals(Long.valueOf(1), actualResponse.getId());
    }

    @Test(expected = PetException.class)
    public void createPet_Error() throws PetException {
        ApiCreatePetRequest createPetRequest = new ApiCreatePetRequest();

        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(), eq(ApiCreatePetResponse.class)))
                .thenThrow(new RuntimeException("Error creating pet"));

        petDao.createPet(createPetRequest);
    }
}
