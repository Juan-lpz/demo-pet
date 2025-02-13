package com.example.demo.controller;

import com.example.demo.exception.PetException;
import com.example.demo.mapper.PetMapper;
import com.example.demo.model.CreatePetRequest;
import com.example.demo.model.CreatePetResponse;
import com.example.demo.model.ListPetResponse;
import com.example.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PetController {

    @Autowired
    private PetService petService;

   @GetMapping("pet/{petId}")
    public ResponseEntity<ListPetResponse> getPetById(@PathVariable("petId") Long petId){
        try {
            return new ResponseEntity<>(petService.getPetById(petId), HttpStatus.OK);
        }catch (PetException e){
            return new ResponseEntity<>(PetMapper.listPetResponseErrorMap(e),e.getStatusHttp());
        }
    }

    @PostMapping("/pet")
    public ResponseEntity<CreatePetResponse> insertPet(@RequestBody CreatePetRequest createPetRequest){

        try {
            return new ResponseEntity<>(petService.createPet(createPetRequest), HttpStatus.OK);
        }catch (PetException e){
            return new ResponseEntity<>(PetMapper.createPetResponseErrorMap(e),e.getStatusHttp());
        }

    }


}
