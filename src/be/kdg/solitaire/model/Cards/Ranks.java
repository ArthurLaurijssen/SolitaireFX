package be.kdg.solitaire.model.Cards;

public enum Ranks {
    AAS,TWEE,DRIE,VIER,VIJF,ZES,ZEVEN,ACHT,NEGEN,TIEN,BOER,VROUW,KONING;
    private static Ranks[] values = values();

    public boolean hasPrevious() {
        return this != AAS;
    }
    public Ranks getPrevious() {
        return values[(this.ordinal()-1) % values.length];
    }

    public Ranks getNext() {

        return values[(this.ordinal()+1) % values.length];
    }
}
