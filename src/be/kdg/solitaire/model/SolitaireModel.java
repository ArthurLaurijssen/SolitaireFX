package be.kdg.solitaire.model;

import be.kdg.solitaire.model.Cards.Deck;

public class SolitaireModel {
    private int score;
    private final Deck deck;
    public SolitaireModel() {
        deck = new Deck();
        deck.shuffle();
        this.score = 100;
    }

    public SolitaireModel(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck() {
        return deck;
    }

    public void stappelTurned() {
        score= score-10;
    }
    public void minutePassed() {
        score = score-15;
    }
    public void stappelKlik() {
        score = score-1;
    }
    public void cardAddedToFoundation() {
        score = score + 15;
    }
    public String getScore() {
        return "Score:\t" + this.score +"\t";
    }
}
