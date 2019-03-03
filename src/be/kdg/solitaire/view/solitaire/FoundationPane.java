package be.kdg.solitaire.view.solitaire;

import be.kdg.solitaire.model.Cards.Card;
import be.kdg.solitaire.model.Cards.Deck;
import be.kdg.solitaire.model.Cards.Suits;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.List;

public class FoundationPane extends StackPane {
    private List<Card> cards;
    Suits suit;
    FoundationPane(Deck deck,Suits suit) {
        this.suit = suit;
        this.getChildren().add(makeImageview(deck.getImages().getBack()));
    }

    public List<Card> getCards() {
        return cards;
    }

    private ImageView makeImageview(Image img) {
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(150);
        imageView.setFitWidth(100);
        return  imageView;
    }
}
