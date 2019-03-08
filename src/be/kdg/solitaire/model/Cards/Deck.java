package be.kdg.solitaire.model.Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>(52);
    private List<Card> verdeeld = new ArrayList<>();
    private CardPNG images;
    private int indexPot;

    public Deck() {
        this.generateCards();
        this.indexPot =0;
    }

    private void generateCards() {
        for (Suits suit: Suits.values()) {
            for (Ranks rank: Ranks.values()) {
                cards.add(new Card(suit,rank));
            }
        }
        this.shuffle();
        this.images = new CardPNG(cards);
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
    public Card getPreviousPot() {
            indexPot = indexPot-2;
            if (indexPot>=0) {
                return cards.get(indexPot++);
            }
            else  {
                indexPot=0;
                if (cards.isEmpty()) {
                    return null;
                }
                return cards.get(indexPot++);
            }
    }
    public Card getNextPot() {
        if (cards.size() != indexPot) {
            if (cards.isEmpty()) {
                return null;
            }
            return cards.get(indexPot++);
        }
        else {
            indexPot =0;
            if (!cards.isEmpty()) {
                return  cards.get(indexPot++);
            }
        }
        return null;

    }
    public CardPNG getImages() {
        return images;
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<Card> getVerdeeld() {
        return verdeeld;
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
        for (Card card : this.cards) {
            if(card.getSuit().equals(suit) && card.getRank().equals(rank)) {
                this.verdeeld.add(card);
                this.cards.remove(card);
                return card;
            }
        }
        return null;
    }
}
