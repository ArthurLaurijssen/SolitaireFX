package be.kdg.solitaire;

import be.kdg.solitaire.model.SolitaireModel;
import be.kdg.solitaire.view.solitaire.SolitairePresenter;
import be.kdg.solitaire.view.start.StartPresenter;
import be.kdg.solitaire.view.start.StartView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private final String title = "Solitaire";
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        StartView view = new StartView();
        SolitaireModel model = new SolitaireModel();
        StartPresenter presenter = new StartPresenter(model,view);
        Scene scene = new Scene(view);
        scene.getStylesheets().add("css/solitaire.css");
        primaryStage.setWidth(800);
        primaryStage.setHeight(500);
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.show();
    }
}
