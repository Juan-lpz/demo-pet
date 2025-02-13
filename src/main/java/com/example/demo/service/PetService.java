package com.example.demo.service;

import com.example.demo.exception.PetException;
import com.example.demo.mapper.PetMapper;
import com.example.demo.model.*;
import com.example.demo.repository.PetDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService implements IPetService{

    private static final Logger logger = LoggerFactory.getLogger(PetService.class);

    @Autowired
    private PetDao petDao;

    @Override
    public ListPetResponse getPetById(Long petId) throws PetException {

        GetPetApiResponse getPetApiResponse = petDao.getPetById(petId);
        ListPetResponse listPetDataResponse = PetMapper.getListPetResponseMap(getPetApiResponse);
         logger.info("Pet List Response: {}", listPetDataResponse);
         return listPetDataResponse;
    }

    @Override
    public CreatePetResponse createPet(CreatePetRequest createPetRequest) throws PetException {

        ApiCreatePetRequest apiCreatePetRequest = PetMapper.requestCreatePetApiMap(createPetRequest);
        ApiCreatePetResponse apiCreatePetResponse = petDao.createPet(apiCreatePetRequest);
        CreatePetResponse response =  PetMapper.createPetResponseMap(apiCreatePetResponse);
        logger.info("Respose: {}", response);
        return response;
    }

}
