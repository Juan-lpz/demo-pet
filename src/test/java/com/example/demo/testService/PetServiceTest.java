package com.example.demo.testService;

import com.example.demo.exception.PetException;
import com.example.demo.model.*;
import com.example.demo.repository.PetDao;
import com.example.demo.service.PetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PetServiceTest {

    @InjectMocks
    PetService petService;

    @Mock
    PetDao petDao;

    @Before
    public void setUp() {
    }

    @Test
    public void getPetById_Success() throws PetException {
        Long petId = 1L;
        GetPetApiResponse petApiResponse = new GetPetApiResponse();
        Category category = new Category();
        category.setId(1L);
        category.setName("Razas pequeñas");
        petApiResponse.setCategory(category);
        petApiResponse.setId(1L);
        petApiResponse.setStatus("disponible");
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("http://pet.image");
        petApiResponse.setPhotoUrls(photoUrls);

        when(petDao.getPetById(petId)).thenReturn(petApiResponse);
        ListPetResponse actualResponse = petService.getPetById(petId);
        assertNotNull(actualResponse);

    }

    @Test(expected = PetException.class)
    public void getPetById_Error() throws PetException {
        Long petId = 1L;
        PetException petException = new PetException( HttpStatus.INTERNAL_SERVER_ERROR,"Pet not found","E500");
        when(petDao.getPetById(petId)).thenThrow(petException);
        petService.getPetById(petId);
    }

    @Test
    public void createPet_Success() throws PetException {
        CreatePetRequest createPetRequest = new CreatePetRequest();
        ApiCreatePetRequest apiCreatePetRequest = new ApiCreatePetRequest();
        ApiCreatePetResponse apiCreatePetResponse = new ApiCreatePetResponse();

        Category category = new Category();
        category.setId(1L);
        category.setName("Razas pequeñas");
        apiCreatePetResponse.setCategory(category);
        apiCreatePetResponse.setId(1L);
        apiCreatePetResponse.setStatus("disponible");
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("http://pet.image");
        apiCreatePetResponse.setPhotoUrls(photoUrls);
        apiCreatePetResponse.setTags(null);

        when(petDao.createPet(any())).thenReturn(apiCreatePetResponse);
        CreatePetResponse actualResponse = petService.createPet(createPetRequest);
        assertNotNull(actualResponse);

    }

    @Test(expected = NullPointerException.class)
    public void createPet_Error() throws PetException {
        CreatePetRequest createPetRequest = new CreatePetRequest();
        petService.createPet(createPetRequest);
    }

}
