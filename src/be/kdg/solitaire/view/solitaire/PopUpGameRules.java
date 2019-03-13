package be.kdg.solitaire.view.solitaire;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


class PopUpGameRules extends VBox {
    private Label lblGameRules;
    PopUpGameRules() {
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes(){
        this.lblGameRules = new Label();
    }
    private void layoutNodes(){

    }
}
