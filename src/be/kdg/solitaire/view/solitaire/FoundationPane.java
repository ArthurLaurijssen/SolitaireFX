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
    private final SolitaireView view;
    private final Deck deck;

    FoundationPane(Deck deck,SolitaireView view,Suits suit) {
        this.view = view;
        this.deck = deck;
        this.suit = suit;
        this.getChildren().add(makeImageviewBack(deck.getImages().getBack()));
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

    private ImageView makeImageviewBack(Image img) {
        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(150);
        imageView.setFitWidth(100);
        view.getPresenter().foundationsAddEventhandlers(imageView,this);
        return  imageView;
    }
    private ImageView makeImageView(Card c) {
        ImageView imageView = new ImageView(this.deck.getImages().getimage(c));
        imageView.setFitWidth(100);
        imageView.setFitHeight(150);
        view.getPresenter().foundationsAddEventhandlers(imageView,this);
        return  imageView;
    }
}
