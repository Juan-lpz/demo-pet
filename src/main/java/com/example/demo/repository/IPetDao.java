package com.example.demo.repository;

import com.example.demo.exception.PetException;
import com.example.demo.model.ApiCreatePetRequest;
import com.example.demo.model.ApiCreatePetResponse;
import com.example.demo.model.GetPetApiResponse;


public interface IPetDao {

    GetPetApiResponse getPetById(Long petId ) throws PetException;
    ApiCreatePetResponse createPet(ApiCreatePetRequest apiCreatePetRequest) throws PetException;
}
