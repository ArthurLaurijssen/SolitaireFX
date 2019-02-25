package be.kdg.solitaire.view.start;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StartView extends BorderPane {
    private Button btnNewGame;
    private Button btnExit;
    private HBox hbox;
    private VBox vbox;
    private Label lblHighScores;
    public StartView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.btnNewGame = new Button("Start Game");
        this.btnExit = new Button("Exit");
        this.hbox = new HBox(75);
        this.vbox = new VBox(20);
        this.lblHighScores = new Label("High Scores");
    }

    private void layoutNodes() {
        this.hbox.getChildren().addAll(this.btnNewGame,this.btnExit);
        this.vbox.getChildren().add(this.lblHighScores);
        this.setLeft(this.hbox);
        this.setRight(this.vbox);
        this.hbox.setAlignment(Pos.CENTER);
        this.lblHighScores.setPadding(new Insets(10,150,40,100));
        this.setPadding(new Insets(20,20,40,150));
        this.btnNewGame.setPadding(new Insets(20));
        this.btnExit.setPadding(new Insets(20));

    }

    public Button getBtnNewGame() {
        return btnNewGame;
    }

    public Button getBtnExit() {
        return btnExit;
    }
}
