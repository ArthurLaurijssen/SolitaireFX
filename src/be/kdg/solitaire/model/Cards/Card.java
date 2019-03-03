package be.kdg.solitaire.model.Cards;


public class Card {
    private  final Suits suit;
    private final Ranks rank;

    Card(Suits suit, Ranks rank) {
        this.suit = suit;
        this.rank = rank;
    }
    public String getId() {
        return this.suit.ordinal() + ":" + this.rank.ordinal();
    }


    Suits getSuit() {
        return suit;
    }

    Ranks getRank() {
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
