package be.kdg.solitaire.model.Cards;

import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.List;

public class CardPNG {
    private HashMap<Card, Image> imageMap = new HashMap<>();

    CardPNG(List<Card> cards) {
        char[] RANKS = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
        char[] SUITS = {'S','H','C','D'};
        HashMap<Suits,Character> suitsHashMap = new HashMap<>();
        HashMap<Ranks,Character> ranksCharacterHashMap = new HashMap<>();
        int i = 0;
        for (Suits suit: Suits.values()) {
            suitsHashMap.put(suit,SUITS[i++]);
        }
        i = 0;
        for (Ranks rank: Ranks.values()) {
            ranksCharacterHashMap.put(rank,RANKS[i++]);
        }
        for (Card card: cards) {
            String source = "/images/" + ranksCharacterHashMap.get(card.getRank()) + suitsHashMap.get(card.getSuit()) + ".png";
            Image img = new Image(source);
            imageMap.put(card,img);
        }
    }
    public Image getimage(Card card) {
        return imageMap.get(card);
    }
    public Image getBack() {
        return new Image ("/images/red_back.png");
    }
}
