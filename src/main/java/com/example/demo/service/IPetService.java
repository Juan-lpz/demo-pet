package com.example.demo.service;

import com.example.demo.exception.PetException;
import com.example.demo.model.CreatePetRequest;
import com.example.demo.model.CreatePetResponse;
import com.example.demo.model.ListPetResponse;

public interface IPetService {
    ListPetResponse getPetById(Long petId ) throws PetException;


    CreatePetResponse createPet(CreatePetRequest createPetRequest) throws PetException;
}
