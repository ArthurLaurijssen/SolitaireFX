package be.kdg.solitaire.view.solitaire;
import be.kdg.solitaire.model.Cards.Card;
import be.kdg.solitaire.model.Cards.Stapels;
import be.kdg.solitaire.model.Cards.Suits;
import be.kdg.solitaire.model.SolitaireModel;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    private ImageView pot,potCardShown;

    public SolitaireView(SolitaireModel model) {
        this.model = model;
        this.imageViewCardMap = new HashMap<>();
        this.initialiseNodes();
        this.presenter= new SolitairePresenter(this,model);
        this.layoutNodes();
    }
    private void initialiseNodes() {
        //7 stapels beneden
        this.hboxStapels = new HBox();

        //4 foundation stapels
        this.hBoxFoundations = new HBox();

        //pot
        this.hboxPot = new HBox();
        this.pot = new ImageView(model.getDeck().getImages().getBack());
        this.potCardShown = new ImageView(new Image("/images/square.png"));

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

        this.hboxPot.setSpacing(40);
        this.pot.setFitHeight(150);
        this.pot.setFitWidth(100);
        this.potCardShown.setFitWidth(100);
        this.potCardShown.setFitHeight(150);
        this.hboxPot.getChildren().add(this.pot);
        this.hboxPot.getChildren().add(this.potCardShown);
        this.add(this.hboxPot,0,0);


        //gridpane
        this.setHgap(200);
        this.setVgap(130);
        this.setPadding(new Insets(50));


    }
    private void fillFoundationPanes() {
        foundationPanes = new ArrayList<>();
        for (Suits suit :  Suits.values()){
            foundationPanes.add(new FoundationPane(model.getDeck(),this,suit));
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

    SolitairePresenter getPresenter() {
        return presenter;
    }


    ImageView getPot() {
        return pot;
    }

    void switchPot(Card c) {
        if (this.hboxPot.getChildren().size() >= 2) {
            this.hboxPot.getChildren().remove(1);
        }
        this.potCardShown = new ImageView(model.getDeck().getImages().getimage(c));
        this.potCardShown.setFitHeight(150);
        this.potCardShown.setFitWidth(100);
        this.presenter.potAddEventHandlers(this.potCardShown,c);
        this.hboxPot.getChildren().add(1,this.potCardShown);
    }


    public ImageView getPotCardShown() {
        return potCardShown;
    }

    Map<Card, ImageView> getImageViewCardMap() {
        return imageViewCardMap;
    }
    void updateFoundations(String id,int source) {

        Card c = model.getDeck().idToCard(id);
        if (source ==-1) {
            model.getDeck().getCards().remove(c);
            model.getDeck().getVerdeeld().add(c);
            Card c1 = model.getDeck().getPreviousPot();
            this.switchPot(c1);
            for (FoundationPane pane : foundationPanes) {
                if (pane.getSuit().equals(c.getSuit())) {
                    pane.addCard(c);
                    break;
                }
            }
        }
        else {
            for (FoundationPane pane : foundationPanes) {
                if (pane.getSuit().equals(c.getSuit())) {
                    pane.addCard(c);
                    stapelPanes.get(source).removeCard();
                    break;
                }
            }
        }

    }
    void updateMultipleCards(int target,int source,String id) {
        Card c = model.getDeck().idToCard(id);
        int d=0;
        for (Card card:stapelPanes.get(source).getCardsOnStapel()) {
            if (card.equals(c)) {
                 d = stapelPanes.get(source).getCardsOnStapel().indexOf(card);
                break;
            }
        }
        ArrayList<Card> cardsToMove = new ArrayList<>();
        for (int i = d; i < stapelPanes.get(source).getCardsOnStapel().size(); i++) {
            cardsToMove.add(stapelPanes.get(source).getCardsOnStapel().get(i));
        }
        for (Card card: cardsToMove) {
            stapelPanes.get(target).addCard(card);
            stapelPanes.get(source).removeCard(card);
        }

    }
    void updateStapels(int target, int source, String id) {
        stapelPanes.get(target).addCard(id);
        if (source==-1) {
            Card c = model.getDeck().idToCard(id);
            model.getDeck().getCards().remove(c);
            model.getDeck().getVerdeeld().add(c);
            if (model.getDeck().getPreviousPot()!=null) {
                c = model.getDeck().getPreviousPot();
                this.switchPot(c);
            }
            else {
                this.hboxPot.getChildren().remove(1);
            }
        }
        else if (stapelPanes.get(source).getCardsOnStapel().contains(model.getDeck().idToCard(id))) {
            stapelPanes.get(source).removeCard();
        }
    }


}
