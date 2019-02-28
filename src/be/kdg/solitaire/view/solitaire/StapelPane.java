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
    private final double offset = 20.0;
    private List<Card> cardsOnStapel = new ArrayList<>();
    StapelPane(Stapels stapel,Deck deck) {
        for (int i = 0; i<=stapel.ordinal();i++) {
            Card c = deck.getNext();
           cardsOnStapel.add(c);
           if (i == stapel.ordinal()) {
               getChildren().add(makeImageview(deck.getImages().getimage(c),offset*i));
           }
           else {
               getChildren().add(makeImageview(deck.getImages().getBack(),offset*i));
           }
        }
    }
    private ImageView makeImageview(Image img,double offset) {
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(150);
        imageView.setFitWidth(100);
        imageView.setTranslateY(offset);
        return  imageView;
    }
}
