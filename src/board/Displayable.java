package board;

import cards.Card;

public interface Displayable {
    public void add(Card newCard);
    public int size();
    public Card getElementAt(int pos);
    public Card removeElement(int pos);
}
