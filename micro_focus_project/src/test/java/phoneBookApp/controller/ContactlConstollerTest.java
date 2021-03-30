package phoneBookApp.controller;


import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import phoneBookApp.Application;
import phoneBookApp.dto.ContactDTO;
import phoneBookApp.service.ContactService;

import java.util.List;
import java.util.Optional;

import static phoneBookApp.service.TestData.*;

/**
 * Tests for all functions in controller
 */

@SpringBootTest
@AutoConfigureMockMvc
class ContactlConstollerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService contactService;

    //testing the creating function in my controller
    @Test
    void create() throws Exception {
        BDDMockito.given(contactService.create(contact1CDTO)).willReturn(contact1DTO);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\""+
                                contact1CDTO.getFirstName()+ "\", "+"\"surname\":\""+
                                contact1CDTO.getSurname()+"\", "+"\"phone\":\""+
                                contact1CDTO.getPhone()+"\"}")
        ).andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(contactService, Mockito.atLeastOnce()).create(contact1CDTO);
    }

    //testing the readOne function in my controller
    @Test
    void readOne() throws Exception {
        BDDMockito.given(contactService.findByIdAsDTO(1)).willReturn(Optional.of(contact1DTO));
        BDDMockito.given(contactService.findByIdAsDTO(8)).willReturn(Optional.empty());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/contact/{id}", contact1DTO.getId())
                        .contentType("application/json")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone", CoreMatchers.is(contact1DTO.getPhone())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", CoreMatchers.is(contact1DTO.getSurname())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(contact1DTO.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(contact1DTO.getFirstName())))
                ;

        Mockito.verify(contactService, Mockito.atLeastOnce()).findByIdAsDTO(1);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/contact/{id}", 8)
                .contentType("application/json")
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Mockito.verify(contactService, Mockito.atLeastOnce()).findByIdAsDTO(8);
    }

    //testing the readAll function in my controller
    @Test
    void readAll() throws Exception {
        final Pageable pageable = PageRequest.of(0,2);
        final Page<ContactDTO> page = new PageImpl<>(dtoList, pageable, dtoList.size()+1);

        BDDMockito.given(contactService.findByPage(pageable)).willReturn(page);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/contact?page={page}&size={size}", 0, 2)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.size",CoreMatchers.is(2)));
        Mockito.verify(contactService, Mockito.atLeastOnce()).findByPage(pageable);
    }

    //testing the delete function in my controller
    @Test
    void delete() throws Exception {
        BDDMockito.doThrow(new Exception("Contact Does Not Exist")).when(contactService).deleteById(1);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/v1/contact/{id}", 1)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError());
        Mockito.verify(contactService, Mockito.atLeastOnce()).deleteById(1);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/v1/contact/{id}", 2)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(contactService, Mockito.atLeastOnce()).deleteById(2);

    }

    //testing counting function in my controller
    @Test
    void getNumContacts() throws Exception {
        BDDMockito.given(contactService.findAll()).willReturn(List.of(contact1DTO));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/contact/count")
                        .content("1")
        ).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(contactService , Mockito.atLeastOnce()).findAll();

    }

    //testing finding my name function in my controller
    @Test
    void readOneName() throws Exception {
        BDDMockito.given(contactService.findByName("Sansa")).willReturn(dtoList);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/contact/name/{name}", "Sansa")
                        .contentType("application/json")
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(contactService, Mockito.atLeastOnce()).findByName("Sansa");
    }
}