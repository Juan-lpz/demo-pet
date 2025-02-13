package com.example.demo.repository;

import com.example.demo.exception.PetException;
import com.example.demo.model.ApiCreatePetRequest;
import com.example.demo.model.ApiCreatePetResponse;
import com.example.demo.model.GetPetApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class PetDao implements IPetDao {

    private static final Logger logger = LoggerFactory.getLogger(PetDao.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.host:https://petstore.swagger.io/v2}")
    private String host;

    @Value("${api.path.createPet:/pet}")
    private String pathCreatePet;

    @Value("${api.path.getPet:/pet/}")
    private String pathGetPet;

    @Override
    public GetPetApiResponse getPetById(Long petId) throws PetException {

        String url = host + pathGetPet + petId;
        logger.info(">> URL: {}", url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<GetPetApiResponse> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,requestEntity,
                    GetPetApiResponse.class
            );

            return responseEntity.getBody();

        }catch (Exception e) {
            logger.error(">> Error al comunicarse con el microservicio: {}", e.getMessage());
            throw new PetException(HttpStatus.INTERNAL_SERVER_ERROR,"E500", "Ocurrio un error al crear el registro de Pet"+e.getMessage());
        }

    }

    @Override
    public ApiCreatePetResponse createPet(ApiCreatePetRequest apiCreatePetRequest) throws PetException {


        String url = host + pathCreatePet;
        logger.info(">> URL: {}", url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");

        HttpEntity<ApiCreatePetRequest> requestEntity = new HttpEntity<>(apiCreatePetRequest, headers);

        try {
            ResponseEntity<ApiCreatePetResponse> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    ApiCreatePetResponse.class
            );

            return responseEntity.getBody();

        } catch (Exception e) {
            logger.error(">> Error al comunicarse con el microservicio: {}", e.getMessage());
            throw new PetException(HttpStatus.INTERNAL_SERVER_ERROR,"E500", "Ocurrio un error al crear el registro de Pet"+e.getMessage());
        }
    }
}
