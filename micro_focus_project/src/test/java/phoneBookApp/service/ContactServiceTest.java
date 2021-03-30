package phoneBookApp.service;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import phoneBookApp.entity.Contact;
import phoneBookApp.repository.ContactRepository;

import static org.mockito.Mockito.*;
import static phoneBookApp.service.TestData.*;

/**
 * Tests for most functions in my Service class
 */

@SpringBootTest
class ContactServiceTest {

    @Autowired
    ContactService contactService;

    @MockBean
    ContactRepository contactRepository;

    //testing the creating function in my service class
    @Test
    void create() throws Exception {
        ReflectionTestUtils.setField(contact1, "contact_id", 1);
        ReflectionTestUtils.setField(badContact2, "contact_id", 2);

        BDDMockito.given(contactRepository.save(any(Contact.class))).willReturn(contact1);
        Assertions.assertTrue(contact1DTO.equals(contactService.create(contact1CDTO)));

        try{
            contactService.create(badContact2CDTO);
        } catch (Exception e){
            Assertions.assertEquals("Phone Number cannot be Null", e.getMessage());
        } finally {
            Mockito.verify(contactRepository, never()).save(badContact2);
        }
    }

    //testing deleting function in my service class
    @Test
    void deleteById() throws Exception{
        ReflectionTestUtils.setField(contact1, "contact_id", 1);
        BDDMockito.given(contactRepository.findById(1)).willReturn(Optional.of(contact1));
        BDDMockito.given(contactRepository.findById(2)).willReturn(Optional.empty());

        try{
            contactService.deleteById(2);
        } catch (Exception e){
            Assertions.assertEquals("Contact Does Not Exist", e.getMessage());
        } finally {
            Mockito.verify(contactRepository, never()).deleteById(2);
        }

        contactService.deleteById(1);
        Mockito.verify(contactRepository, atLeastOnce()).deleteById(1);
    }

    //testing my find by name class in my service function
    @Test
    void findByName() {
        //Define Mock
        ReflectionTestUtils.setField(contact1, "contact_id", 1);
        ReflectionTestUtils.setField(contact2, "contact_id", 2);
        ReflectionTestUtils.setField(contact3, "contact_id", 3);
        ReflectionTestUtils.setField(contact4, "contact_id", 4);
        ReflectionTestUtils.setField(contact5, "contact_id", 5);
        BDDMockito.given(contactRepository.findContactByFirstName("Sansa")).willReturn(list);

        //Testing
        Assertions.assertEquals(contactService.findByName("Sansa") ,dtoList);
        Mockito.verify(contactRepository , Mockito.atLeastOnce()).findContactByFirstName("Sansa");
    }

    //tests find all function in my service class
    @Test
    void findAll() {
        ReflectionTestUtils.setField(contact1, "contact_id", 1);
        ReflectionTestUtils.setField(contact2, "contact_id", 2);
        ReflectionTestUtils.setField(contact3, "contact_id", 3);
        ReflectionTestUtils.setField(contact4, "contact_id", 4);
        ReflectionTestUtils.setField(contact5, "contact_id", 5);
        BDDMockito.given(contactRepository.findAll()).willReturn(listFA);

        Assertions.assertEquals(dtoListFA, contactService.findAll());

    }
}