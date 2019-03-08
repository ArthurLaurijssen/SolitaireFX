package be.kdg.solitaire;

import be.kdg.solitaire.model.SolitaireModel;
import be.kdg.solitaire.view.start.StartPresenter;
import be.kdg.solitaire.view.start.StartView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;

public class Main extends Application {
    private final String title = "Solitaire";

    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        StartView view = new StartView();
        SolitaireModel model = new SolitaireModel();
        new StartPresenter(model,view);
        Scene scene = new Scene(view);
        scene.getStylesheets().add("css/solitaire.css");
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                final Alert closeGameAlert = new Alert(Alert.AlertType.CONFIRMATION);
                closeGameAlert.setTitle("Close game");
                closeGameAlert.setHeaderText("You are exiting the game are you sure?");
                Optional<ButtonType> btnKeuze = closeGameAlert.showAndWait();
                if (btnKeuze.get().getText().equals("Cancel")) {
                    event.consume();
                }
            }
        });
        primaryStage.setWidth(1300);
        primaryStage.setHeight(800);
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.show();
    }
}
