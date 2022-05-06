package cards;

public abstract class EdibleItem extends Card {
    protected int flavourPoints;

    public EdibleItem(CardType type, String cardName) {
        super(type,cardName);
    }

    public int getFlavourPoints() {
        return this.flavourPoints;
    }
}
