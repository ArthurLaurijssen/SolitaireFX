package be.kdg.solitaire.model.Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>(52);
    private List<Card> verdeeld = new ArrayList<>();
    private CardPNG images;

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
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card getNext() {
        Card c = cards.get(0);
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

    public Card idToCard(String id) {
        String[] parts = id.split(":");
        Suits suit =   Suits.values()[Integer.parseInt(parts[0])];
        Ranks rank = Ranks.values()[Integer.parseInt(parts[1])];
        for (Card card : this.verdeeld) {
            if(card.getSuit().equals(suit) && card.getRank().equals(rank)) {
                return card;
            }
        }
        return null;
    }
}
