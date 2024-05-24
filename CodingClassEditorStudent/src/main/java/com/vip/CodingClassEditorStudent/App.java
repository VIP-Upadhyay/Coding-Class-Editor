package com.vip.CodingClassEditorStudent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;



/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
    	 scene = new Scene(loadFXML("loginsignup"), 640, 480);
         loadCSS("application");
//         scene = new Scene(loadFXML("home"), 640, 480);
//         loadCSS("home");
         stage.setMaximized(true);
         stage.setScene(scene);
         stage.show(); 
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    static void setRootWithCss(String fxml,String css) throws IOException {
        scene.setRoot(loadFXML(fxml));
        loadCSS(css); 
    }
    static Image loadImage(String image){
        return new Image(App.class.getResource(image).toString());
    }
    
    
    private static void loadCSS(String css) throws IOException {
    	scene.getStylesheets().add(App.class.getResource("css/"+css + ".css").toExternalForm());
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
	static ImageView loadGraphics(String url,int fit) {
		Image img = App.loadImage(url);
		ImageView view = new ImageView(img);
	    view.setFitHeight(fit);
	    view.setPreserveRatio(true); 
	    return view;
	}
	static Parent loadFXMLScreen(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/screen/"+fxml + ".fxml"));
        return fxmlLoader.load(); 
    }
	@SuppressWarnings("exports")
	public static FXMLLoader loadFXMLItems(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/items/"+fxml + ".fxml"));
        return fxmlLoader; 
    }
	static FXMLLoader loadFXMLScreenLoader(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/screen/"+fxml + ".fxml"));
        return fxmlLoader; 
    }
    
	static AnchorPane setScreen(String screen) {
		try {
			return (AnchorPane) App.loadFXMLScreen(screen);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
    public static void main(String[] args) {
        launch();
    }
}