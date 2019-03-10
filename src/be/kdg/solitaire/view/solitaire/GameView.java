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

class GameView extends GridPane {
    private final SolitaireModel model;
    private HBox hboxStapels,hBoxFoundations;
    private Pot hboxPot;
    private Map<Card,ImageView> imageViewCardMap;
    private List<StapelPane> stapelPanes;
    private List<FoundationPane> foundationPanes;
    private final SolitairePresenter presenter;
    private SolitaireView vboxView;

    GameView(SolitaireModel model,SolitaireView vboxView) {
        this.model = model;
        this.vboxView = vboxView;
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
        this.hboxPot = new Pot(model,this);

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
        this.add(this.hboxPot,0,0);

        //gridpane
        this.setHgap(200);
        this.setVgap(80);
        this.setPadding(new Insets(15,75,50,75));


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
        return this.presenter;
    }


    Map<Card, ImageView> getImageViewCardMap() {
        return imageViewCardMap;
    }

    Pot getPot() {
        return this.hboxPot;
    }


    void updateFoundations(String id,int source) {
        Card c = model.getDeck().idToCard(id);
        model.cardAddedToFoundation();
        this.vboxView.getScore().setText(model.getScore());
        if (source ==-1) {
            model.getDeck().getCards().remove(c);
            model.getDeck().getVerdeeld().add(c);
            Card c1 = model.getDeck().getPreviousPot();
            this.hboxPot.switchPot(c1);
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
                this.hboxPot.switchPot(c);
            }
            else {
                System.out.println("Pot legen");
                this.hboxPot.potEmpty();
            }
        }
        else if (stapelPanes.get(source).getCardsOnStapel().contains(model.getDeck().idToCard(id))) {
            stapelPanes.get(source).removeCard();
        }
    }

    SolitaireView getVboxView() {
        return vboxView;
    }
}