package be.kdg.solitaire.view.start;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class StartView extends HBox {
    private Button btnNewGame,btnExit;
    private HBox hBoxButtons,hBoxTextfield;
    private VBox vBoxHighScores,vBoxButtons;
    private Label lblHighScores,lblSpeler,lblError;
    private Canvas canvasLine;
    private TextField textField;

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
        this.textField = new TextField();
        this.vBoxButtons = new VBox();
        this.lblSpeler = new Label("Name:");
        this.hBoxTextfield = new HBox();
        this.lblError = new Label();
    }

    private void layoutNodes() {

        //Hbox Buttons
        this.hBoxButtons.getChildren().addAll(this.btnNewGame,this.btnExit);
        this.hBoxButtons.setSpacing(40);

        //Hbox Textfield
        this.textField.setMaxWidth(135);
        this.hBoxTextfield.setSpacing(20);
        this.hBoxTextfield.getChildren().add(this.lblSpeler);
        this.hBoxTextfield.getChildren().add(this.textField);

        //Vbox Buttons
        this.vBoxButtons.getChildren().add(this.lblError);
        this.vBoxButtons.getChildren().add(this.hBoxTextfield);
        this.vBoxButtons.getChildren().add(this.hBoxButtons);
        this.vBoxButtons.setSpacing(20);
        this.vBoxButtons.setAlignment(Pos.CENTER);

        //Canvas
        GraphicsContext gc = canvasLine.getGraphicsContext2D();
        gc.setLineWidth(2.5);
        gc.strokeLine(0,0,0,400);

        //HighScores
        this.vBoxHighScores.getChildren().add(this.lblHighScores);

        //This
        this.getChildren().add(this.vBoxButtons);
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

    Label getLblError() {
        return lblError;
    }
    TextField getTextField() {
        return textField;
    }
}
