package cz.phonebook.client;

import cz.phonebook.client.DTO.ContactCreateDTO;
import cz.phonebook.client.DTO.ContactDTO;
import cz.phonebook.client.resources.ContactResource;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class Controller implements Initializable {
    private final ContactResource contactResource;

    @FXML
    private TextField deleteId;

    @FXML
    private TextField findName;

    @FXML
    private TextField createName;

    @FXML
    private TextField createSurname;

    @FXML
    private TextField createPhone;

    @FXML
    private TableView<ContactDTO> tableView;

    @FXML
    private TableColumn<ContactDTO, Integer> idCol;

    @FXML
    private TableColumn<ContactDTO, String > nameCol;

    @FXML
    private TableColumn<ContactDTO, String> secondNameCol;

    @FXML
    private TableColumn<ContactDTO, String> phoneCol;

    private int page;

    public Controller(ContactResource contactResource) {
        this.contactResource = contactResource;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getId()));
        nameCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getFirstName()));
        secondNameCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getSurname()));
        phoneCol.setCellValueFactory(p-> new ReadOnlyObjectWrapper(p.getValue().getPhone()));

        page = 0;
        List<ContactDTO> contactDTOS = new ArrayList<>(contactResource.readPage(page, 5).getContent());
        tableView.getItems().setAll(contactDTOS);
    }
    public void onReadOne(){
        if(findName.getText().equals("") )
            return;
        List<ContactDTO> contactDTOS = new ArrayList<>(contactResource.readByName(findName.getText()));
        findName.clear();
        tableView.getItems().clear();
        tableView.getItems().setAll(contactDTOS);
    }

    public void onCreate(){
        if(createName.getText().equals("") || createPhone.getText().equals("") || !createPhone.getText().matches("-?\\d+"))
            return;

        ContactCreateDTO contactCreateDTO = new ContactCreateDTO(createName.getText() ,
                createSurname.getText() ,
                createPhone.getText());
        contactResource.create(contactCreateDTO);

        createSurname.clear();
        createName.clear();
        createPhone.clear();

        refreshTableOnLast();
    }

    public void onDelete(){
        if(deleteId.getText().equals("") || !deleteId.getText().matches("-?\\d+"))
            return;
        int i = Integer.parseInt(deleteId.getText());
        contactResource.deleteById(i);
        deleteId.clear();
        refreshTable();
    }



    public void onReadAll(){
        page = 0;
        refreshTable();
    }


    public void onNext(){
        if(page < contactResource.getNumCustomers()/5) {
            page++;
            refreshTable();
        }
    }

    public void onPrev(){
        if(page>0) {
            page--;
            refreshTable();
        }
    }

    private void refreshTableOnLast(){
        int i = contactResource.getNumCustomers();
        if(i%5 == 0){
            List<ContactDTO> contactDTOS = new ArrayList<>(contactResource.readPage(i/6, 5).getContent());
            tableView.getItems().setAll(contactDTOS);
        }
        else{
            List<ContactDTO> contactDTOS = new ArrayList<>(contactResource.readPage(i/5, 5).getContent());
            tableView.getItems().setAll(contactDTOS);
        }
    }

    private void refreshTable(){
        List<ContactDTO> contactDTOS = new ArrayList<>(contactResource.readPage(page, 5).getContent());
        tableView.getItems().setAll(contactDTOS);
    }

}
