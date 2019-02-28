package be.kdg.solitaire.view.solitaire;
import be.kdg.solitaire.model.Cards.Stapels;
import be.kdg.solitaire.model.SolitaireModel;
import javafx.scene.layout.GridPane;

public class SolitaireView extends GridPane {
    SolitaireModel model;

    public SolitaireView(SolitaireModel model) {
        this.model = model;
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() {
        add(new StapelPane(Stapels.EEN,model.getDeck()),0,0);
        add(new StapelPane(Stapels.TWEE,model.getDeck()),1,0);
        add(new StapelPane(Stapels.DRIE,model.getDeck()),2,0);
        add(new StapelPane(Stapels.VIER,model.getDeck()),3,0);
        add(new StapelPane(Stapels.VIJF,model.getDeck()),4,0);
        add(new StapelPane(Stapels.ZES,model.getDeck()),5,0);
        add(new StapelPane(Stapels.ZEVEN,model.getDeck()),6,0);
        /*StackPane st = new StackPane();
        ImageView imgv = new ImageView(new Image("/images/TC.png"));
        ImageView imgv2 = new ImageView(model.getDeck().getImages().getBack());
        imgv2.setTranslateY(10);
        imgv.setFitWidth(100);
        imgv.setFitHeight(150);
        imgv2.setFitHeight(150);
        imgv2.setFitWidth(100);
        st.getChildren().add(imgv);
        st.getChildren().add(imgv2);
        add(st,0,1);*/

    }


    private void layoutNodes() {

    }
}
