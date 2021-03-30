package phoneBookApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import phoneBookApp.dto.ContactCreateDTO;
import phoneBookApp.dto.ContactDTO;
import phoneBookApp.entity.Contact;
import phoneBookApp.service.ContactService;

import java.net.URI;
import java.util.List;
import java.util.Optional;


/**
 * Controller class for our server
 */
@RestController
@RequestMapping(value = "api/v1/contact")
public class ContactlConstoller {

    private final ContactService contactService;

    @Autowired
    public ContactlConstoller(ContactService contactService) { this.contactService = contactService; }

    //gets request to create a contact
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ContactDTO> create(@RequestBody ContactCreateDTO contactCreateDTO){
        ContactDTO created = null;
        try {
            created = contactService.create(contactCreateDTO);
        } catch (Exception e){
            if(e.getMessage().equals("Phone Number cannot be Null")){
                throw new ResponseStatusException(HttpStatus.CONFLICT);
            }
        }
        assert created != null;
        return ResponseEntity.created(URI.create("http://loacalhost:8080/api/v1/contact/" + created.getId())).body(created);
    }

    //gets request for a contact with a unique ID
    @GetMapping("/{id}")
    public ContactDTO readOneName(@PathVariable int id){
        Optional<ContactDTO> optionalContactDTO = contactService.findByIdAsDTO(id);
        return optionalContactDTO.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //gets request for contacts on a certain page (5 on each page)
    @GetMapping
    public Page<ContactDTO> readAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        return contactService.findByPage(PageRequest.of(page, size));
    }

    //gets request to delete a certain contact with a unique ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        try{
            contactService.deleteById(id);
        } catch (Exception e){
            if(e.getMessage().equals("Contact Does Not Exist")){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
    }

    //gets request for all contacts with specific name
    @GetMapping("/name/{name}")
    public List<ContactDTO> readOneName(@PathVariable String name){
        return contactService.findByName(name);
    }

    //gets request for amount of contacts
    @GetMapping("/count")
    public Integer getNumContacts(){
        return contactService.findAll().size();
    }

}
