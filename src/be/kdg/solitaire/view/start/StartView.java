package be.kdg.solitaire.view.start;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class StartView extends HBox {
    private Button btnNewGame,btnExit;
    private HBox hBoxButtons;
    private VBox vBoxHighScores;
    private Label lblHighScores;
    private Canvas canvasLine;

    public StartView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.btnNewGame = new Button("Start Game");
        this.btnExit = new Button("Exit");
        this.hBoxButtons = new HBox();
        this.vBoxHighScores = new VBox();
        this.lblHighScores = new Label("High Scores");
        this.canvasLine = new Canvas(10,400);
    }

    private void layoutNodes() {
        //Hbox Buttons
        this.hBoxButtons.getChildren().addAll(this.btnNewGame,this.btnExit);
        this.hBoxButtons.setAlignment(Pos.CENTER);
        this.hBoxButtons.setSpacing(40);

        //Canvas
        GraphicsContext gc = canvasLine.getGraphicsContext2D();
        gc.setLineWidth(2.5);
        gc.strokeLine(0,0,0,400);

        //HighScores
        this.vBoxHighScores.getChildren().add(this.lblHighScores);



        //This
        this.getChildren().add(this.hBoxButtons);
        this.getChildren().add(canvasLine);
        this.getChildren().add(this.vBoxHighScores);
        this.setSpacing(50);



        //this.lblHighScores.setPadding(new Insets(10,150,40,100));
        this.setPadding(new Insets(0,20,0,80));

    }

    Button getBtnNewGame() {
        return btnNewGame;
    }

    Button getBtnExit() {
        return btnExit;
    }
}
