package be.kdg.solitaire.model;

import be.kdg.solitaire.model.Cards.Deck;

public class SolitaireModel {
    private Deck deck;
    public SolitaireModel() {
        deck = new Deck();
        deck.shuffle();
    }

    public SolitaireModel(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck() {
        return deck;
    }
}
