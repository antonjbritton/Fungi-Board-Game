package board;

import java.util.ArrayList;
import cards.Card;

public class CardList {
    private ArrayList<Card> cList;

    public CardList() {
        this.cList = new ArrayList<Card>();
    }

    public void add(Card newCard) {
        this.cList.add(0,newCard);
    }

    public int size() {
        return this.cList.size();
    }

    public Card getElementAt(int pos) {
        return this.cList.get(pos);
    }

    public Card removeCardAt(int pos) {
        return this.cList.remove(8 - pos);
    }
}
