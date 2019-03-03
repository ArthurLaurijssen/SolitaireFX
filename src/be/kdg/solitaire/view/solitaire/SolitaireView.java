package be.kdg.solitaire.view.solitaire;
import be.kdg.solitaire.model.Cards.Card;
import be.kdg.solitaire.model.Cards.Stapels;
import be.kdg.solitaire.model.Cards.Suits;
import be.kdg.solitaire.model.SolitaireModel;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolitaireView extends GridPane {
    private SolitaireModel model;
    private HBox hboxStapels;
    private HBox hBoxFoundations;
    private HBox hboxPot;
    private Map<Card,ImageView> imageViewCardMap;
    private List<StapelPane> stapelPanes;
    private List<FoundationPane> foundationPanes;
    private SolitairePresenter presenter;

    public SolitaireView(SolitaireModel model) {
        this.model = model;
        this.presenter= new SolitairePresenter(this,model);
        this.imageViewCardMap = new HashMap<>();
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
            stapelPanes.add(new StapelPane(stapel,model.getDeck(),this));
        }
    }

    List<StapelPane> getStapelPanes() {
        return stapelPanes;
    }

    List<FoundationPane> getFoundationPanes() {
        return foundationPanes;
    }

    public SolitairePresenter getPresenter() {
        return presenter;
    }

    public Map<Card, ImageView> getImageViewCardMap() {
        return imageViewCardMap;
    }

    void updateStapels(int target, int source, String id) {
        stapelPanes.get(target).addCard(id);
        stapelPanes.get(source).removeCard();
    }


}
