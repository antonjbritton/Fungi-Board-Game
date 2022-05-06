package board;

import java.util.ArrayList;
import cards.Card;

public class Hand implements Displayable {
    private ArrayList<Card> handList = new ArrayList<Card>();

    @Override
    public void add(Card newCard) {
        this.handList.add(newCard);
    }

    @Override
    public int size() {
        return this.handList.size();
    }

    @Override
    public Card getElementAt(int pos) {
        return this.handList.get(pos);
    }

    @Override
    public Card removeElement(int pos) {
        return this.handList.remove(pos);
    }
}
