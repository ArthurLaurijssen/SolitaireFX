package be.kdg.solitaire.view.solitaire;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


class PopUpAbout extends VBox {
    private Label version,auteurs;
    PopUpAbout() {
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes(){
        this.version = new Label("Solitaire v2.2");
        this.auteurs = new Label("Gemaakt door: \n- Arthur Laurijssen\n- Francis Iragi Bahizire\n");
    }
    private void layoutNodes(){
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(25));
        this.getChildren().add(this.version);
        this.getChildren().add(this.auteurs);
    }
}
