package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import jodd.json.JsonParser;
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

        writeFileJson(contacts);
        name.clear();
        phoneNumber.clear();
        email.clear();
    }
    public void onRemove(){
        SelectionModel model = list.getSelectionModel();
        Contact contact = (Contact) model.getSelectedItem();
        contacts.remove(contact);

    }

    public static void writeFileJson(ObservableList<Contact> contacts) throws IOException {
        File f = new File("contactList.json");
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.serialize(contacts);
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();
    }
//    public static Contact loadContactList(String filename) {
//        File f = new File(filename);
//        try {
//            Scanner scanner = new Scanner(f);
//            scanner.useDelimiter("\\Z");
//            String contents = scanner.next();
//            JsonParser parser = new JsonParser();
//            ObservableList<Contact> contacts = FXCollections.observableArrayList();
//            return parser.parse(contents, Contact.class);
//        } catch (FileNotFoundException e) {
//        }
//        return null;
//    }
    //might need to use ArrayList<Hashmap<String, String>> list = parser.parse(contents);
    //for (Hashmap<String, String> h: list){
    //contacts.add(new Contact(h.get("name"), h.get("email"), h.get("phonenumber")



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.setItems(contacts);
        //loadContactList("contactList.json");

    }
}
