package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import jodd.json.JsonSerializer;


public class Controller implements Initializable {


    @FXML
    TextField name;
    @FXML
    TextField phoneNumber;
    @FXML
    TextField email;
    @FXML
    ListView list;

    static ObservableList<Contact> contacts = FXCollections.observableArrayList();

    public void onAdd() throws IOException {
        Contact contact = new Contact(name.getText(), phoneNumber.getText(), email.getText());
        if (!name.getText().isEmpty() && !phoneNumber.getText().isEmpty() && !email.getText().isEmpty()) {
            contacts.add(contact);
        }
        writeFileJson(contacts.toString());
        name.clear();
        phoneNumber.clear();
        email.clear();
    }
    public void onRemove(){
        SelectionModel model = list.getSelectionModel();
        Contact contact = (Contact) model.getSelectedItem();
        contacts.remove(contact);

    }

    public static void writeFileJson(String contacts) throws IOException {
        File f = new File("contactList");
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.serialize(contacts);
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.setItems(contacts);
    }
}
