package be.kdg.solitaire.view.solitaire;

import be.kdg.solitaire.model.Cards.*;
import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

class StapelPane extends StackPane {
    private final double offsetOriginal;
    private double offset = 0;
    private List<Card> cardsOnStapel;
    private Deck deck;
    private SolitaireView view;
    private Stapels stapel;
    private Card cardOnTop;
    StapelPane(Stapels stapel,Deck deck,SolitaireView view) {
        this.offsetOriginal = 25;
        this.cardsOnStapel = new ArrayList<>();
        this.view = view;
        this.deck = deck;
        this.stapel = stapel;
        for (int i = 0; i<=stapel.ordinal();i++) {
            Card c = deck.getNext();
           cardsOnStapel.add(c);
           if (i == stapel.ordinal()) {
               getChildren().add(this.makeImageView(c));
               cardOnTop = c;
           }
           else {
               getChildren().add(this.makeImageViewBack());
           }
        }
    }

    List<Card> getCardsOnStapel() {
        return cardsOnStapel;
    }

    Stapels getStapel() {
        return stapel;
    }

    void addCard(String id) {
        Card c = deck.idToCard(id);
        cardsOnStapel.add(c);
        ImageView imgV = this.makeImageView(c);
        getChildren().add(imgV);
        cardOnTop = c;
    }
    void removeCard() {
        cardsOnStapel.remove(cardOnTop);
        getChildren().remove(getChildren().size()-1);
        offset = offset-offsetOriginal;
        if (!cardsOnStapel.isEmpty()) {
            getChildren().remove(getChildren().size()-1);
            offset = offset-offsetOriginal;
            cardOnTop = cardsOnStapel.get(cardsOnStapel.size()-1);
            getChildren().add(makeImageView(cardOnTop));
        }
        else {
            cardOnTop = null;
            getChildren().add(makeEmptyPane());
        }
    }

    private ImageView makeImageViewBack() {
        ImageView imageView = new ImageView(this.deck.getImages().getBack());
        imageView.setFitHeight(150);
        imageView.setFitWidth(100);
        imageView.setTranslateY(offset);
        this.offset = this.offset+ this.offsetOriginal;
        return  imageView;
    }
    private ImageView makeImageView(Card c) {
        ImageView imageView = new ImageView(this.deck.getImages().getimage(c));
        imageView.setFitHeight(150);
        imageView.setFitWidth(100);
        imageView.setTranslateY(offset);
        this.offset = this.offset+ this.offsetOriginal;
        this.view.getImageViewCardMap().put(c,imageView);
        view.getPresenter().addEventhandlers(imageView,c);
        return  imageView;
    }
    private ImageView makeEmptyPane() {
        ImageView imageView = new ImageView(deck.getImages().getBack());
        imageView.setFitHeight(150);
        imageView.setFitWidth(100);
        view.getPresenter().emptyStapelEventHandlers(imageView,this);
        return imageView;
    }
}
