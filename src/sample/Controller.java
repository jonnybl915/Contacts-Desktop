package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    TextField name;
    @FXML
    TextField phoneNumber;
    @FXML
    TextField email;
    @FXML
    ListView list;

    ObservableList<Contact> contacts = FXCollections.observableArrayList();

    public void onAdd(){
        Contact contact = new Contact(name.getText(), phoneNumber.getText(), email.getText());
        if (!(name.getText().isEmpty() || (phoneNumber.getText().isEmpty() || (email.getText().isEmpty())))); {
        }
        contacts.add(contact);
        name.clear();
    }
    public void onRemove(){
        SelectionModel model = list.getSelectionModel();
        Contact contact = (Contact) model.getSelectedItem();
        contacts.remove(contact);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // if (!(name.getText().isEmpty() || (phoneNumber.getText().isEmpty() || (email.getText().isEmpty())))); {
        //}
        list.setItems(contacts);

    }
}
