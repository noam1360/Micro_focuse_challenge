package cz.phonebook.client.resources;

import cz.phonebook.client.DTO.ContactCreateDTO;
import cz.phonebook.client.DTO.ContactDTO;
import cz.phonebook.client.DTO.ContactListDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContactResource {
    private final RestTemplate restTemplate;

    public ContactResource(RestTemplateBuilder restTemplateBuilder){
        restTemplate = restTemplateBuilder.rootUri(ROOT_RESOURCE_URL).build();
    }

    private static final String COLLECTTION_PAGED_URI = "/?page={page}&?size={size}";
    private static final String ROOT_RESOURCE_URL = "http://localhost:8080/api/v1/contact";
    private static final String ONE_URI = "/{id}";

    public URI create (ContactCreateDTO data){
        return restTemplate.postForLocation(ROOT_RESOURCE_URL, data);
    }

    public List<ContactDTO> readByName(String name){
        ResponseEntity<List<ContactDTO>> response = restTemplate.exchange(ROOT_RESOURCE_URL+"/name/"+name, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ContactDTO>>(){});
        assert response != null;

        return response.getBody();
    }

    public PagedModel<ContactDTO> readPage(int page , int size){
        ResponseEntity<PagedModel<ContactDTO>> result = restTemplate.exchange(COLLECTTION_PAGED_URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedModel<ContactDTO>>() {},
                page , size);
        return result.getBody();
    }

    public void deleteById(int id){

        restTemplate.delete(ROOT_RESOURCE_URL+"/"+id);
    }

    public int getNumCustomers(){
        return restTemplate.getForObject(ROOT_RESOURCE_URL+"/count",Integer.class);
    }
}
