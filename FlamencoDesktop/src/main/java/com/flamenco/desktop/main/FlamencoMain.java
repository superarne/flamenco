package com.flamenco.desktop.main;
	
import java.io.IOException;

import com.flamenco.desktop.main.view.RootController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class FlamencoMain extends Application {
	
	private Stage primaryStage;
    private BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Flamenco");
	    
		primaryStage.getIcons().add(new Image("/com/flamenco/desktop/main/flamenco.png"));
		initRootLayout();
	}
	
	/**
     * Initializes the root layout.
     */
    public void initRootLayout() {
    	 try {    
    		 // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FlamencoMain.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();			

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();
            
            // Give the controller access to the main app.
            RootController controller = loader.getController();
            controller.setMain(this);
    	 } catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    } 
    
    public BorderPane getRootLayout() {
		return rootLayout;
	}

	public static void main(String[] args) {
        launch(args);  
    }
	
}
