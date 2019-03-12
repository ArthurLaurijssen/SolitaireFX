package be.kdg.solitaire.view.solitaire;

import be.kdg.solitaire.model.SolitaireModel;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


class PopUpWon extends VBox {
    private Label lblGewonnen;
    private int tijd;
    private SolitaireModel model;

    PopUpWon(SolitaireModel model, int tijd) {
        this.model = model;
        this.tijd = tijd;
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes(){
            this.lblGewonnen = new Label();

    }
    private void layoutNodes(){
        this.lblGewonnen.setText("Proficiat " + model.getPlayerName() +" heeft gewonnen!\n\nScore: " + model.getScore()+"\nTijd: " + this.tijd);
        this.getChildren().add(this.lblGewonnen);
    }

}
