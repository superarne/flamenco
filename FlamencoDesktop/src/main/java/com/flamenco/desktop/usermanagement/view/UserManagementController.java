package com.flamenco.desktop.usermanagement.view;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.flamenco.desktop.usermanagement.model.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UserManagementController {
	
	private ObservableList<User> userList;
	
	@FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    
    @FXML
    private TextField idTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private Button saveButton;
    
    private User user;
    private Client restclient;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public UserManagementController() {    	
    	restclient = Client.create();
		Collection<User> users = restclient.resource("http://localhost:8080/FlamencoRest/user/users").get(new GenericType<List<User>>(){});
		for(User u : users){
			if(u.getUsername()==null)
				System.out.println("null");
		}
		userList = FXCollections.observableArrayList(users);		
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the UserModel table with the two columns.
    	usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
    	emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());  
    	userTable.setItems(userList);
    	
    	// Listen for selection changes and show the person details when changed.
    	userTable.getSelectionModel().selectedItemProperty().addListener(
               (observable, oldValue, newValue) -> showUserDetails(newValue));
    	
    	
    	//Listen for TextField Changes
    	addTextFieldListener(idTextField);
    }
    
    private void addTextFieldListener(TextField textfield){
    	textfield.textProperty().addListener((observable, oldValue, newValue) -> {
    		saveButton.setDisable(false);
		});
    }    
    
    /**
     * Fills all text fields to show details about the user.
     * @param user
     */
    private void showUserDetails(User user) {
    	this.user=user;
        idTextField.textProperty().bindBidirectional(user.idProperty());
        usernameTextField.textProperty().bindBidirectional(user.usernameProperty());
        passwordTextField.textProperty().bindBidirectional(user.passwordProperty());
        emailTextField.textProperty().bindBidirectional(user.emailProperty());        
    }
    
    @FXML
    private void saveUserDetails() {
    	WebResource resource = restclient.resource("http://localhost:8080/FlamencoRest/user/updateUser");
        MultivaluedMap queryParams = new MultivaluedMapImpl();
        queryParams.add("user", user);
        resource.queryParams(queryParams).get(String.class);
    }

}