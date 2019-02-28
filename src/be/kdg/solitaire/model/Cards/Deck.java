package be.kdg.solitaire.model.Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();
    private List<Card> verdeeld = new ArrayList<>();
    private CardPNG images;
    private int card;

    public Deck() {
        this.generateCards();
    }

    private void generateCards() {
        for (Suits suit: Suits.values()) {
            for (Ranks rank: Ranks.values()) {
                cards.add(new Card(suit,rank));
            }
        }
        this.shuffle();
        images = new CardPNG(cards);
        card=0;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card getNext() {
        Card c = cards.get(card++);
        cards.remove(c);
        verdeeld.add(c);
        return c;
    }

    public CardPNG getImages() {
        return images;
    }

    public List<Card> getCards() {
        return cards;
    }
}
