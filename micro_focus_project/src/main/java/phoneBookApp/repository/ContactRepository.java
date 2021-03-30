package phoneBookApp.repository;

import phoneBookApp.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * Repository Interface that communicates with the database
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {
    List<Contact> findContactByFirstName(String first_name);
}
