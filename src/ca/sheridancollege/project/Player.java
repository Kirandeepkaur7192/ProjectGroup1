package ca.sheridancollege.project;

/**
 * @author Ishanpreet singh oct 2024
 */
public abstract class Player {
    private String name;
    private GroupOfCard hand;

    public Player(String name) {
        this.name = name;
        this.hand = new GroupOfCard();
    }

    public String getName() {
        return name;
    }

    public Card playCard() {
        return hand.drawCard();
    }

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    public int cardsRemaining() {
        return hand.size();
    }

}
