package be.kdg.solitaire.view.solitaire;

import be.kdg.solitaire.model.Cards.Card;
import be.kdg.solitaire.model.Cards.Deck;
import be.kdg.solitaire.model.Cards.Stapels;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

class StapelPane extends StackPane {
    private final double offsetOriginal = 25;
    private double offset = 0;
    private List<Card> cardsOnStapel = new ArrayList<>();
    private ImageView imgTop;
    private Card cardOnTop;
    private Deck deck;
    private Stapels stapel;

    StapelPane(Stapels stapel,Deck deck) {
        this.deck = deck;
        this.stapel = stapel;
        for (int i = 0; i<=stapel.ordinal();i++) {
            Card c = deck.getNext();
           cardsOnStapel.add(c);
           if (i == stapel.ordinal()) {
               imgTop = makeImageview(deck.getImages().getimage(c),offset);
               offset = offset+ offsetOriginal;
               getChildren().add(imgTop);
               cardOnTop = c;
           }
           else {
               getChildren().add(makeImageview(deck.getImages().getBack(),offset));
               offset = offset+ offsetOriginal;
           }
        }

    }

    Stapels getStapel() {
        return stapel;
    }

    ImageView getImgTop() {
        return imgTop;
    }

    Card getCardOnTop() {
        return cardOnTop;
    }
    void addCard(String id) {
        Card c = deck.idToCard(id);
        cardsOnStapel.add(c);
        ImageView imgV = makeImageview(deck.getImages().getimage(c),offset);
        offset = offset+ offsetOriginal;
        getChildren().add(imgV);
    }

    private ImageView makeImageview(Image img, double offset) {
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(150);
        imageView.setFitWidth(100);
        imageView.setTranslateY(offset);
        return  imageView;
    }
}
