package be.kdg.solitaire.Cards;

public class Card {
    private  final Suits suit;
    private final Ranks rank;

    Card(Suits suit, Ranks rank) {
        this.suit = suit;
        this.rank = rank;
    }
    public Suits getSuit() {
        return suit;
    }

    public Ranks getRank() {
        return rank;
    }
    public boolean kanErop(Card card) {
       if(card.rank.hasPrevious()){
           return (this.rank.getPrevious() == card.rank && this.suit.kleurverschillend(card.suit));
       }
       else {
           return false;
       }
    }

}
