package board;

import java.util.ArrayList;
import cards.*;

public class Player {
    private Hand h;
    private Display d;
    private int score;
    private int handlimit;
    private int sticks;

    public Player() {
        this.h = new Hand();
        this.d = new Display();
        this.d.add(new Pan());
        this.score = 0;
        this.handlimit = 8;
        this.sticks = 0;
    }

    public int getScore() {
        return this.score;
    }

    public int getHandLimit() {
        return this.handlimit;
    }

    public int getStickNumber() {
        return this.sticks;
    }

    public void addSticks(int moreSticks) {
        this.sticks = this.sticks + moreSticks;
    }

    public void removeSticks(int lessSticks) {
        this.sticks = this.sticks - lessSticks;
    }

    public Hand getHand() {
        return this.h;
    }

    public Display getDisplay() {
        return this.d;
    }

    public void addCardtoHand(Card newCard) {
        if (newCard.getType().equals(CardType.BASKET)) {
            addCardtoDisplay(newCard);
            this.handlimit = this.handlimit + 2;
        } else {
            this.h.add(newCard);
        }
    }

    public void addCardtoDisplay(Card newCard) {
        this.d.add(newCard);
    }

    public boolean takeCardFromTheForest(int pos) {
        if (h.size() < this.handlimit) {
            if (pos - this.sticks < 3) {
                if (pos > 2) {
                    this.sticks = (this.sticks - pos + 2);
                    for (int i=0;i<this.d.size();i++) {
                        if (this.d.getElementAt(i).getType().equals(CardType.STICK)) {
                            this.d.removeElement(i);
                        }
                    }
                    for (int i=0;i<this.sticks;i++) {
                        this.d.add(new Stick());
                    }
                }
                if (Board.getForest().getElementAt(pos).getType().equals(CardType.BASKET)) {
                    addCardtoDisplay(Board.getForest().removeCardAt(pos));
                    this.handlimit = this.handlimit + 2;
                } else {
                    addCardtoHand(Board.getForest().removeCardAt(pos));
                }
                return true;
            }
        }
        return false;
    }

    public boolean takeFromDecay() {
        int basketCount = 0;
        if (h.size() + Board.getDecayPile().size() <= this.handlimit) {
            for (Card card: Board.getDecayPile()) {
                addCardtoHand(card);
            }
            Board.getDecayPile().clear();
            return true;
        } else {
            for (Card card: Board.getDecayPile()){
                if (card.getType().equals(CardType.BASKET)) {
                    basketCount = basketCount + 1;
                }
            }
            if (Board.getDecayPile().size() <= 3 && basketCount > 0) {
                for (Card card: Board.getDecayPile()) {
                    addCardtoHand(card);
                }
                Board.getDecayPile().clear();
                return true;
            }
            if (Board.getDecayPile().size() == 4 && basketCount > 1) {
                for (Card card: Board.getDecayPile()) {
                    addCardtoHand(card);
                }
                Board.getDecayPile().clear();
                return true;
            }
        }
        return false;
    }

    public boolean cookMushrooms(ArrayList<Card> cards) {
        boolean panAvailable = false;
        boolean panInHand = false;
        boolean mushroomsCooked = false;
        int honeyfungusDayCount = 0;
        int honeyfungusNightCount = 0;
        int treeearDayCount = 0;
        int treeearNightCount = 0;
        int lawyerswigDayCount = 0;
        int lawyerswigNightCount=0;
        int shiitakeDayCount = 0;
        int shiitakeNightCount = 0;
        int henofwoodsDayCount = 0;
        int henofwoodsNightCount = 0;
        int birchboleteDayCount = 0;
        int birchboleteNightCount = 0;
        int porciniDayCount = 0;
        int porciniNightCount = 0;
        int chanterelleDayCount = 0;
        int chanterelleNightCount = 0;
        int morelDayCount = 0;
        int mushroomCount = 0;
        int cardCount = 0;
        int butterCount = 0;
        int ciderCount = 0;
        for (Card card: cards) {
            if (card.getType().equals(CardType.PAN)) {
                this.d.add(card);
                cards.remove(card);
                panInHand = true;
                break;
            }
        }
        for (int i=0;i<this.d.size();i++) {
            if (this.d.getElementAt(i).getType().equals(CardType.PAN)) {
                panAvailable = true;
            }
        }
        if (panAvailable) {
            for (Card card: cards) {
                switch (card.getName()) {
                    case "honeyfungus":
                        if (card.getType().equals(CardType.DAYMUSHROOM)) {
                            honeyfungusDayCount = honeyfungusDayCount + 1;
                        } else {
                            honeyfungusNightCount = honeyfungusNightCount + 1;
                        }
                        break;
                    case "treeear":
                        if (card.getType().equals(CardType.DAYMUSHROOM)) {
                            treeearDayCount = treeearDayCount + 1;
                        } else {
                            treeearNightCount = treeearNightCount + 1;
                        }
                        break;
                    case "lawyerswig":
                        if (card.getType().equals(CardType.DAYMUSHROOM)) {
                            lawyerswigDayCount = lawyerswigDayCount + 1;
                        } else {
                            lawyerswigNightCount = lawyerswigNightCount + 1;
                        }
                        break;
                    case "shiitake":
                        if (card.getType().equals(CardType.DAYMUSHROOM)) {
                            shiitakeDayCount = shiitakeDayCount + 1;
                        } else {
                            shiitakeNightCount = shiitakeNightCount + 1;
                        }
                        break;
                    case "henofwoods":
                        if (card.getType().equals(CardType.DAYMUSHROOM)) {
                            henofwoodsDayCount = henofwoodsDayCount + 1;
                        } else {
                            henofwoodsNightCount = henofwoodsNightCount + 1;
                        }
                        break;
                    case "birchbolete":
                        if (card.getType().equals(CardType.DAYMUSHROOM)) {
                            birchboleteDayCount = birchboleteDayCount + 1;
                        } else {
                            birchboleteNightCount = birchboleteNightCount + 1;
                        }
                        break;
                    case "porcini":
                        if (card.getType().equals(CardType.DAYMUSHROOM)) {
                            porciniDayCount = porciniDayCount + 1;
                        } else {
                            porciniNightCount = porciniNightCount + 1;
                        }
                        break;
                    case "chanterelle":
                        if (card.getType().equals(CardType.DAYMUSHROOM)) {
                            chanterelleDayCount = chanterelleDayCount + 1;
                        } else {
                            chanterelleNightCount = chanterelleNightCount + 1;
                        }
                        break;
                    case "morel":
                        morelDayCount = morelDayCount + 1;
                        break;
                    case "butter":
                        butterCount = butterCount + 1;
                        break;
                    case "cider":
                        ciderCount = ciderCount + 1;
                        break;
                    default:
                        return false;
                }
            }
            if (honeyfungusDayCount+(2*honeyfungusNightCount) > 2) {
                cardCount = honeyfungusDayCount + honeyfungusNightCount;
                mushroomCount = honeyfungusDayCount + (2*honeyfungusNightCount);
                score = score + (1*mushroomCount);
                mushroomsCooked = true;
                for (int i=0;i<this.d.size();i++) {
                    if (this.d.getElementAt(i).getType().equals(CardType.PAN)) {
                        this.d.removeElement(i);
                        break;
                    }
                }
                for (int i=0;i<this.h.size();i++) {
                    if (this.h.getElementAt(i).getName().equals("honeyfungus") && cardCount > 0) {
                        this.h.removeElement(i);
                        cardCount = cardCount - 1;
                    }
                    if (this.h.getElementAt(i).getName().equals("pan") && panInHand == true) {
                        this.h.removeElement(i);
                        panInHand = false;
                    }
                }
            }
            if (treeearDayCount+(2*treeearNightCount) > 2) {
                cardCount = treeearDayCount + treeearNightCount;
                mushroomCount = treeearDayCount + (2*treeearNightCount);
                score = score + (1*mushroomCount);
                mushroomsCooked = true;
                for (int i=0;i<this.d.size();i++) {
                    if (this.d.getElementAt(i).getType().equals(CardType.PAN)) {
                        this.d.removeElement(i);
                        break;
                    }
                }
                for (int i=0;i<this.h.size();i++) {
                    if (this.h.getElementAt(i).getName().equals("treeear") && cardCount > 0) {
                        this.h.removeElement(i);
                        cardCount = cardCount - 1;
                    }
                    if (this.h.getElementAt(i).getName().equals("pan") && panInHand == true) {
                        this.h.removeElement(i);
                        panInHand = false;
                    }
                }
            }
            if (lawyerswigDayCount+(2*lawyerswigNightCount) > 2) {
                cardCount = lawyerswigDayCount + lawyerswigNightCount;
                mushroomCount = lawyerswigDayCount + (2*lawyerswigNightCount);
                score = score + (2*mushroomCount);
                mushroomsCooked = true;
                for (int i=0;i<this.d.size();i++) {
                    if (this.d.getElementAt(i).getType().equals(CardType.PAN)) {
                        this.d.removeElement(i);
                        break;
                    }
                }
                for (int i=0;i<this.h.size();i++) {
                    if (this.h.getElementAt(i).getName().equals("lawyerswig") && cardCount > 0) {
                        this.h.removeElement(i);
                        cardCount = cardCount - 1;
                    }
                    if (this.h.getElementAt(i).getName().equals("pan") && panInHand == true) {
                        this.h.removeElement(i);
                        panInHand = false;
                    }
                }
            }
            if (shiitakeDayCount+(2*shiitakeNightCount) > 2) {
                cardCount = shiitakeDayCount + shiitakeNightCount;
                mushroomCount = shiitakeDayCount + (2*shiitakeNightCount);
                score = score + (2*mushroomCount);
                mushroomsCooked = true;
                for (int i=0;i<this.d.size();i++) {
                    if (this.d.getElementAt(i).getType().equals(CardType.PAN)) {
                        this.d.removeElement(i);
                        break;
                    }
                }
                for (int i=0;i<this.h.size();i++) {
                    if (this.h.getElementAt(i).getName().equals("shiitake") && cardCount > 0) {
                        this.h.removeElement(i);
                        cardCount = cardCount - 1;
                    }
                    if (this.h.getElementAt(i).getName().equals("pan") && panInHand == true) {
                        this.h.removeElement(i);
                        panInHand = false;
                    }
                }
            }
            if (henofwoodsDayCount+(2*henofwoodsNightCount) > 2) {
                cardCount = henofwoodsDayCount + henofwoodsNightCount;
                mushroomCount = henofwoodsDayCount + (2*henofwoodsNightCount);
                score = score + (3*mushroomCount);
                mushroomsCooked = true;
                for (int i=0;i<this.d.size();i++) {
                    if (this.d.getElementAt(i).getType().equals(CardType.PAN)) {
                        this.d.removeElement(i);
                        break;
                    }
                }
                for (int i=0;i<this.h.size();i++) {
                    if (this.h.getElementAt(i).getName().equals("henofwoods") && cardCount > 0) {
                        this.h.removeElement(i);
                        cardCount = cardCount - 1;
                    }
                    if (this.h.getElementAt(i).getName().equals("pan") && panInHand == true) {
                        this.h.removeElement(i);
                        panInHand = false;
                    }
                }
            }
            if (birchboleteDayCount+(2*birchboleteNightCount) > 2) {
                cardCount = birchboleteDayCount + birchboleteNightCount;
                mushroomCount = birchboleteDayCount + (2*birchboleteNightCount);
                score = score + (3*mushroomCount);
                mushroomsCooked = true;
                for (int i=0;i<this.d.size();i++) {
                    if (this.d.getElementAt(i).getType().equals(CardType.PAN)) {
                        this.d.removeElement(i);
                        break;
                    }
                }
                for (int i=0;i<this.h.size();i++) {
                    if (this.h.getElementAt(i).getName().equals("birchbolete") && cardCount > 0) {
                        this.h.removeElement(i);
                        cardCount = cardCount - 1;
                    }
                    if (this.h.getElementAt(i).getName().equals("pan") && panInHand == true) {
                        this.h.removeElement(i);
                        panInHand = false;
                    }
                }
            }
            if (porciniDayCount+(2*porciniNightCount) > 2) {
                cardCount = porciniDayCount + porciniNightCount;
                mushroomCount = porciniDayCount + (2*porciniNightCount);
                score = score + (3*mushroomCount);
                mushroomsCooked = true;
                for (int i=0;i<this.d.size();i++) {
                    if (this.d.getElementAt(i).getType().equals(CardType.PAN)) {
                        this.d.removeElement(i);
                        break;
                    }
                }
                for (int i=0;i<this.h.size();i++) {
                    if (this.h.getElementAt(i).getName().equals("porcini") && cardCount > 0) {
                        this.h.removeElement(i);
                        cardCount = cardCount - 1;
                    }
                    if (this.h.getElementAt(i).getName().equals("pan") && panInHand == true) {
                        this.h.removeElement(i);
                        panInHand = false;
                    }
                }
            }
            if (chanterelleDayCount+(2*chanterelleNightCount) > 2) {
                cardCount = chanterelleDayCount + chanterelleNightCount;
                mushroomCount = chanterelleDayCount + (2*chanterelleNightCount);
                score = score + (4*mushroomCount);
                mushroomsCooked = true;
                for (int i=0;i<this.d.size();i++) {
                    if (this.d.getElementAt(i).getType().equals(CardType.PAN)) {
                        this.d.removeElement(i);
                        break;
                    }
                }
                for (int i=0;i<this.h.size();i++) {
                    if (this.h.getElementAt(i).getName().equals("chanterelle") && cardCount > 0) {
                        this.h.removeElement(i);
                        cardCount = cardCount - 1;
                    }
                    if (this.h.getElementAt(i).getName().equals("pan") && panInHand == true) {
                        this.h.removeElement(i);
                        panInHand = false;
                    }
                }
            }
            if (morelDayCount > 2) {
                cardCount = morelDayCount;
                mushroomCount = morelDayCount;
                score = score + (6*mushroomCount);
                mushroomsCooked = true;
                for (int i=0;i<this.d.size();i++) {
                    if (this.d.getElementAt(i).getType().equals(CardType.PAN)) {
                        this.d.removeElement(i);
                        break;
                    }
                }
                for (int i=0;i<this.h.size();i++) {
                    if (this.h.getElementAt(i).getName().equals("morel") && cardCount > 0) {
                        this.h.removeElement(i);
                        cardCount = cardCount - 1;
                    }
                    if (this.h.getElementAt(i).getName().equals("pan") && panInHand == true) {
                        this.h.removeElement(i);
                        panInHand = false;
                    }
                }
            }
            if (ciderCount > 0) {
                for (int i=0;i<ciderCount;i++) {
                    if (mushroomCount > 4) {
                        mushroomCount = mushroomCount - 5;
                        score = score + 5;
                        for (int j=0;j<this.h.size();j++) {
                            if (this.h.getElementAt(j).getName().equals("cider")) {
                                this.h.removeElement(j);
                            }
                        }
                    } else {
                        mushroomsCooked = false;
                    }
                }
            }
            if (butterCount > 0) {
                for (int i=0;i<butterCount;i++) {
                    if (mushroomCount > 3) {
                        mushroomCount = mushroomCount - 4;
                        score = score + 3;
                        for (int j=0;i<this.h.size();j++) {
                            if (this.h.getElementAt(j).getName().equals("butter")) {
                                this.h.removeElement(j);
                            }
                        }
                    } else {
                        mushroomsCooked = false;
                    }
                }
            }
        }
        return mushroomsCooked;
    }

    public boolean sellMushrooms(String mushroom,int quantity) {
        String mushroomName = mushroom.replace("'","").replace(" ","").toLowerCase();
        int cardCount = 0;
        int mushroomCount = 0;
        int sticksPerMushroom = 0;

        for (int i=0;i<this.h.size();i++) {
            if (this.h.getElementAt(i).getName().equals(mushroomName)) {
                if (this.h.getElementAt(i).getType().equals(CardType.DAYMUSHROOM)) {
                    cardCount = cardCount + 1;
                    mushroomCount = mushroomCount + 1;
                }
                if (this.h.getElementAt(i).getType().equals(CardType.NIGHTMUSHROOM))
                {
                    cardCount = cardCount + 1;
                    mushroomCount = mushroomCount + 2;
                }

            }
        }
        switch (mushroomName) {
            case "honeyfungus":
                sticksPerMushroom = 1;
                break;
            case "treeear":
                sticksPerMushroom = 2;
                break;
            case "lawyerswig":
                sticksPerMushroom = 1;
                break;
            case "shiitake":
                sticksPerMushroom = 2;
                break;
            case "henofwoods":
                sticksPerMushroom = 1;
                break;
            case "birchbolete":
                sticksPerMushroom = 2;
                break;
            case "porcini":
                sticksPerMushroom = 3;
                break;
            case "chanterelle":
                sticksPerMushroom = 2;
                break;
            case "morel":
                sticksPerMushroom = 4;
                break;
        }
        if (quantity >= 2 && mushroomCount >= quantity) {
            for (int i=0;i<(quantity*sticksPerMushroom);i++) {
                this.d.add(new Stick());
                sticks = sticks + 1;
            }
            for (int i=0;i<this.h.size();i++) {
                if (this.h.getElementAt(i).getName().equals(mushroomName)) {
                    this.h.removeElement(i);
                    cardCount = cardCount - 1;
                }
                if (cardCount == 0) {
                    break;
                }
            }
            return true;
        }
        return false;
    }

    public boolean putPanDown() {
        for (int i=0;i<this.h.size();i++) {
            if (h.getElementAt(i).getType().equals(CardType.PAN)) {
                h.removeElement(i);
                d.add(new Pan());
                return true;
            }
        }
        return false;
    }
}
