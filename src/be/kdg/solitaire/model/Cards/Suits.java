package be.kdg.solitaire.model.Cards;

public enum Suits {
    SCHOPPEN,HARTEN,KLAVEREN,RUITEN;

    public boolean kleurverschillend(Suits suit) {
        if (this == SCHOPPEN || this == KLAVEREN) {
            switch (suit) {
                case HARTEN: return true;
                case RUITEN: return true;
                default: return false;
            }
        }
        else {
            switch (suit) {
                case KLAVEREN: return true;
                case SCHOPPEN: return true;
                default: return false;
            }

        }
    }
}
