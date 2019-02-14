package be.kdg.solitaire;

import be.kdg.solitaire.model.SolitaireModel;
import be.kdg.solitaire.view.SolitairePresenter;
import be.kdg.solitaire.view.SolitaireView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        SolitaireModel model = new SolitaireModel();
        SolitaireView view = new SolitaireView();
        SolitairePresenter presenter = new SolitairePresenter(view,model);
        primaryStage.setScene(new Scene(view));
        primaryStage.show();
    }
}
