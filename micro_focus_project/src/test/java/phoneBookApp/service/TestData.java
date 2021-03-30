package phoneBookApp.service;

import phoneBookApp.dto.ContactCreateDTO;
import phoneBookApp.dto.ContactDTO;
import phoneBookApp.entity.Contact;

import java.util.List;


/**
 * Test data (contacts, contactDTOs, and contactCreateDTOs) for my tests
 */
public class TestData {
    public final static Contact contact1 = new Contact("43423423423" , "Sansa" , "Stark");
    public final static Contact contact2 = new Contact("47283749173" , "Arya" , "Stark");
    public final static Contact contact3 = new Contact("37782739477" , "Daenerys" , "Targaryen");
    public final static Contact contact4 = new Contact("434211222938" , "Jon" , "Snow");
    public final static Contact contact5 = new Contact("434231234343" , "Sansa" , "Baratheon");

    public final static Contact badContact1 = new Contact("43423423423" , "Melisandre" , "");
    public final static Contact badContact2 = new Contact("" , "Theon" , "");
    public final static Contact badContact3 = new Contact("" , "" , "Drogo");

    public final static ContactDTO contact1DTO = new ContactDTO(1,"43423423423" , "Sansa" , "Stark");
    public final static ContactDTO contact2DTO = new ContactDTO(2,"47283749173" , "Arya" , "Stark");
    public final static ContactDTO contact3DTO = new ContactDTO(3,"37782739477" , "Daenerys" , "Targaryen");
    public final static ContactDTO contact4DTO = new ContactDTO(4,"434211222938" , "Jon" , "Snow");
    public final static ContactDTO contact5DTO = new ContactDTO(5,"434231234343" , "Sansa" , "Baratheon");

    public final static ContactDTO badContact1DTO = new ContactDTO(6,"43423423423" , "Melisandre" , "");
    public final static ContactDTO badContact2DTO = new ContactDTO(7,"" , "Theon" , "");
    public final static ContactDTO badContact3DTO = new ContactDTO(8,"" , "" , "Drogo");

    public final static ContactCreateDTO contact1CDTO = new ContactCreateDTO("43423423423" , "Sansa" , "Stark");
    public final static ContactCreateDTO contact2CDTO = new ContactCreateDTO("47283749173" , "Arya" , "Stark");
    public final static ContactCreateDTO contact3CDTO = new ContactCreateDTO("37782739477" , "Daenerys" , "Targaryen");
    public final static ContactCreateDTO contact4CDTO = new ContactCreateDTO("434211222938" , "Jon" , "Snow");
    public final static ContactCreateDTO contact5CDTO = new ContactCreateDTO("434231234343" , "Sansa" , "Baratheon");

    public final static ContactCreateDTO badContact1CDTO = new ContactCreateDTO("43423423423" , "Melisandre" , "");
    public final static ContactCreateDTO badContact2CDTO = new ContactCreateDTO("" , "Theon" , "");
    public final static ContactCreateDTO badContact3CDTO = new ContactCreateDTO("" , "" , "Drogo");

    //find all by name Sansa
    public final static List<ContactDTO> dtoList = List.of(contact1DTO,contact5DTO);
    public final static  List<Contact> list = List.of(contact1,contact5);

    //find all
    public final static List<ContactDTO> dtoListFA = List.of(contact1DTO,contact2DTO,contact3DTO,contact4DTO,contact5DTO);
    public final static  List<Contact> listFA = List.of(contact1,contact2,contact3,contact4,contact5);
}
