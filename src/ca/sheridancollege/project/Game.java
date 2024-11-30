/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */

package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.List;

/**
 * The class that models your game.
 * @author Kirandeep Kaur Nov 2024
 */
public class Game {
    private List<Player> players;
    private int rounds;
    private GroupOfCard deck;

    public Game(List<String> playerNames) {
        players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        rounds = 0;
        deck = new GroupOfCard();
    }

    public void startGame() {
        deck.shuffle();
        dealCards();
        playRounds();
        determineWinner();
    }

    private void dealCards() {
        while (deck.size() > 0) {
            for (Player player : players) {
                Card card = deck.drawCard();
                if (card != null) {
                    player.receiveCard(card);
                }
            }
        }
    }

    private void playRounds() {
        while (players.stream().anyMatch(player -> player.cardsRemaining() > 0)) {
            rounds++;
            playRound();
        }
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
        Card winningCard = playedCards.get(0);
        Player roundWinner = players.get(0);

        for (int i = 1; i < playedCards.size(); i++) {
            if (playedCards.get(i).outranks(winningCard)) {
                winningCard = playedCards.get(i);
                roundWinner = players.get(i);
            }
        }
        System.out.println(roundWinner.getName() + " wins this round with " + winningCard);
        roundWinner.receiveCard(winningCard);
    }

    private void determineWinner() {
        Player winner = players.stream()
                .max((p1, p2) -> Integer.compare(p1.cardsRemaining(), p2.cardsRemaining()))
                .orElse(null);
        
        if (winner != null) {
            System.out.println("Game Over! The winner is " + winner.getName() + 
                               " with " + winner.cardsRemaining() + " cards!");
        } else {
            System.out.println("Game Over! It's a tie!");
        }
    }
}
