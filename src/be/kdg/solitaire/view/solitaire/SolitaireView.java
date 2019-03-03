package be.kdg.solitaire.view.solitaire;
import be.kdg.solitaire.model.Cards.Card;
import be.kdg.solitaire.model.Cards.Stapels;
import be.kdg.solitaire.model.Cards.Suits;
import be.kdg.solitaire.model.SolitaireModel;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class SolitaireView extends GridPane {
    private SolitaireModel model;
    private HBox hboxStapels;
    private HBox hBoxFoundations;
    private HBox hboxPot;

    private List<StapelPane> stapelPanes;
    private List<FoundationPane> foundationPanes;
    public SolitaireView(SolitaireModel model) {
        this.model = model;
        this.initialiseNodes();
        this.layoutNodes();
    }
    private void initialiseNodes() {
        //7 stapels beneden
        this.hboxStapels = new HBox();

        //4 foundation stapels
        this.hBoxFoundations = new HBox();

        //pot
        this.hboxPot = new HBox();
    }

    private void layoutNodes() {
        //7 stapels beneden
        this.fillStapelPanes();
        for (StapelPane stapelPane: stapelPanes) {
            this.hboxStapels.getChildren().add(stapelPane);
        }
        this.hboxStapels.setSpacing(40);
        this.add(this.hboxStapels,0,1,2,1);

        //4 foundation stappels
        this.fillFoundationPanes();
        for (FoundationPane foundationPane:foundationPanes) {
            this.hBoxFoundations.getChildren().add(foundationPane);
        }
        this.hBoxFoundations.setSpacing(40);
        this.add(this.hBoxFoundations,1,0);

        //pot
        ImageView img = new ImageView(model.getDeck().getImages().getBack());
        img.setFitHeight(150);
        img.setFitWidth(100);
        this.hboxPot.getChildren().add(img);
        this.add(this.hboxPot,0,0);

        //gridpane
        this.setHgap(300);
        this.setVgap(130);
        this.setPadding(new Insets(50));

    }
    private void fillFoundationPanes() {
        foundationPanes = new ArrayList<>();
        for (Suits suit :  Suits.values()){
            foundationPanes.add(new FoundationPane(model.getDeck(),suit));
        }
    }
    private void fillStapelPanes() {
        stapelPanes = new ArrayList<>();
        for (Stapels stapel: Stapels.values()) {
            stapelPanes.add(new StapelPane(stapel,model.getDeck()));
        }
    }

    List<StapelPane> getStapelPanes() {
        return stapelPanes;
    }

    List<FoundationPane> getFoundationPanes() {
        return foundationPanes;
    }

    void updateStapels(int target,int source,String id) {
        int index =0;
        for (Node child : this.hboxStapels.getChildren()) {
            if (index == target) {
                StapelPane stapelPane = (StapelPane) child;
                stapelPane.addCard(id);
                this.hboxStapels.getChildren().set(target,stapelPane);
            }
            index++;
        }
    }


}
