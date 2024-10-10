/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */

package ca.sheridancollege.project;

/**
 * The class that models your game. You should create a more specific child of this class and instantiate the methods
 * given.
 *
 * @author Kirandeep Kaur Oct 2024
 */
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private int rounds;

    public Game(List<String> playerNames) {
        players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        rounds = 0;
    }

    public void startGame() {
        GroupOfCard deck = new GroupOfCard();
        for (int i = 0; i < deck.size() / players.size(); i++) {
            for (Player player : players) {
                player.receiveCard(deck.drawCard());
            }
        }
        playRounds();
    }

    private void playRounds() {
        while (players.stream().anyMatch(player -> player.cardsRemaining() > 0)) {
            rounds++;
            playRound();
        }
        determineWinner();
    }

    private void playRound() {
        System.out.println("Round " + rounds);
        List<Card> playedCards = new ArrayList<>();
        for (Player player : players) {
            Card card = player.playCard();
            if (card != null) {
                playedCards.add(card);
                System.out.println(player.getName() + " plays " + card);
            }
        }
        determineRoundWinner(playedCards);
    }

    private void determineRoundWinner(List<Card> playedCards) {
        Card winningCard = null;
        Player roundWinner = null;

        for (int i = 0; i < playedCards.size(); i++) {
            if (winningCard == null || playedCards.get(i).getValue() > winningCard.getValue()) {
                winningCard = playedCards.get(i);
                roundWinner = players.get(i);
            }
        }
        System.out.println(roundWinner.getName() + " wins this round with " + winningCard);
        
    }

    private void determineWinner() {
        System.out.println("Game Over!");
    }
}
