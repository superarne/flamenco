package com.flamenco.desktop.main.view;

import java.io.IOException;

import com.flamenco.desktop.main.FlamencoMain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class RootController {
	
	private FlamencoMain main;
	
	@FXML
	private TabPane pane;
	
	/**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public RootController() {
		
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }
    
    /**
     * Add a Tab to the root Layout
     */
    private void addTab(String title, String resource) {
    	try {    		
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FlamencoMain.class.getResource(resource));
            Tab tab = new Tab();
            tab.setText(title);
            tab.setContent(loader.load());
            pane.getTabs().add(tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Shows the user management inside the root layout.
     */
    public void showUserManagement() {
    	addTab("Benutzerverwaltung","../usermanagement/view/UserManagement.fxml");
    }

	public void setMain(FlamencoMain main) {
		this.main = main;
	}    
    
    

}