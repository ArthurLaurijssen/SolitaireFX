package be.kdg.solitaire.view.solitaire;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


class PopUpWon extends VBox {
    private Label lblGewonnen;

    PopUpWon() {
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes(){
            this.lblGewonnen = new Label("You won");
    }
    private void layoutNodes(){
            this.getChildren().add(this.lblGewonnen);
    }

}
