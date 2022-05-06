package board;

import java.util.ArrayList;
import cards.Card;

public class Display implements Displayable {
    private ArrayList<Card> displayList = new ArrayList<Card>();

    @Override
    public void add(Card newCard) {
        this.displayList.add(newCard);
    }

    @Override
    public int size() {
        return this.displayList.size();
    }

    @Override
    public Card getElementAt(int pos) {
        return this.displayList.get(pos);
    }

    @Override
    public Card removeElement(int pos) {
        return this.displayList.remove(pos);
    }
}
