package be.kdg.solitaire.view.solitaire;

import be.kdg.solitaire.model.Cards.Card;
import be.kdg.solitaire.model.Cards.Deck;
import be.kdg.solitaire.model.Cards.Ranks;
import be.kdg.solitaire.model.Cards.Suits;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


class FoundationPane extends StackPane {
    private final Suits suit;
    private Ranks highestRank;
    private final GameView view;
    private final Deck deck;

    FoundationPane(Deck deck,GameView view,Suits suit) {
        this.view = view;
        this.deck = deck;
        this.suit = suit;
        this.getChildren().add(makeImageViewFoundations(suit));
    }


    void addCard(Card card) {
        this.getChildren().add(makeImageView(card));
        this.highestRank = card.getRank();
    }

    Suits getSuit() {
        return suit;
    }

    Ranks getHighestRank() {
        return highestRank;
    }

    private ImageView makeImageViewFoundations(Suits suit) {
        char[] SUITS = {'S','H','C','D'};
        String url = "/images/" + SUITS[suit.ordinal()] + ".png";
        ImageView imageView = new ImageView(new Image(url));
        imageView.setFitHeight(150);
        imageView.setFitWidth(100);
        view.getPresenter().foundationsAddEventhandlers(imageView,this);
        return imageView;
    }
    private ImageView makeImageView(Card c) {
        ImageView imageView = new ImageView(this.deck.getImages().getimage(c));
        imageView.setFitWidth(100);
        imageView.setFitHeight(150);
        view.getPresenter().foundationsAddEventhandlers(imageView,this);
        return  imageView;
    }
}
