import java.util.*;
import java.io.Console;

class crazy8 {
  public static void main(String args[]) {
    Console consolePlayer = System.console();

    int players = 0;
    while(players == 0) {
      System.out.print("How many players (2-6)? ");
      try {
        players = Integer.parseInt(consolePlayer.readLine());
      } catch(Exception e) {
        players = 0;
      }
      if(players < 2 || players > 6) {
        System.out.print("Please enter a valid number of players");
        players = 0;
      }
    }

    Deck orgDeck = new Deck();
    orgDeck.shuffle();
    ArrayList<Deck> playerHands = new ArrayList<>();
    for(int i = 0;i < players;i++) {
      playerHands.add(new Deck(0));
    }
    Deck usedCard = new Deck(0);

    for(Deck d : playerHands) {
      drawHand(7,d,orgDeck);
    }

    drawHand(1,usedCard,orgDeck);

    int player = 0;
    while(true) {
      if(orgDeck.getSize() == 0) {
        orgDeck.mergeDeck(usedCard);
        orgDeck.shuffle();
        drawHand(1,usedCard, orgDeck);
      }

      if(usedCard.getSize() > 0) {
        Card c = usedCard.printFirstCard();
        usedCard.addCard(c);
      }

      System.out.println("");
      System.out.println("");
      System.out.println("Player " + (player + 1));
      System.out.println("");
      System.out.println("");
      System.out.println("Deck: " + playerHands.get(player).displayDeck());
      System.out.println("");
      System.out.println("");
      playCard(playerHands.get(player), usedCard, orgDeck);

      if(playerHands.get(player).getSize() == 0) {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.print("Game Over!!!!!!!!!");
        System.out.print("The winner is player + " + player);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        break;
      }
      player = 1 + player;
      player %= players;
    }
  }

  public static void playCard(abstractDeck hand, abstractDeck disCard, abstractDeck orgDeck) {
    Console console = System.console();
    int value = -1;
    int suit = -1;
    Card c = disCard.printFirstCard(); //initial first card

    while(value == -1 || suit == -1) {
      System.out.println("");
      System.out.println("Top Card: " + c.displayCard());
      System.out.println("");
      String line = console.readLine("Choose the card that you want to play(Type 'No Card' if you can't play): ");
      String parts[] = line.split(" ");
      String valCard = parts[0];
      String suitCard = parts[1];

      if(valCard.equalsIgnoreCase("No") && suitCard.equalsIgnoreCase("Card")) {
        drawHand(1, hand, orgDeck);
        System.out.print("Deck: " + hand.displayDeck());
      } else if(valCard.equalsIgnoreCase("Two")) {
        value = 2;
      } else if(valCard.equalsIgnoreCase("King")) {
        value = 13;
      } else if(valCard.equalsIgnoreCase("Three")) {
        value = 3;
      } else if(valCard.equalsIgnoreCase("Four")) {
        value = 4;
      } else if(valCard.equalsIgnoreCase("Five")) {
        value = 5;
      } else if(valCard.equalsIgnoreCase("Six")) {
        value = 6;
      } else if(valCard.equalsIgnoreCase("Seven")) {
        value = 7;
      } else if(valCard.equalsIgnoreCase("Eight")) {
        value = 8;
      } else if(valCard.equalsIgnoreCase("Nine")) {
        value = 9;
      } else if(valCard.equalsIgnoreCase("Ten")) {
        value = 10;
      } else if(valCard.equalsIgnoreCase("Jack")) {
        value = 11;
      } else if(valCard.equalsIgnoreCase("Queen")) {
        value = 12;
      } else if(valCard.equalsIgnoreCase("Ace")) {
        value = 1;
      } else {
        value = -1;
      }


      if(suitCard.equalsIgnoreCase("Hearts")) {
        suit = 1;
      } else if(suitCard.equalsIgnoreCase("Diamond")) {
        suit = 2;
      } else if(suitCard.equalsIgnoreCase("Clubs")) {
        suit = 3;
      } else if(suitCard.equalsIgnoreCase("Spades")) {
        suit = 4;
      } else {
        suit = -1;
      }

      if(hand.removeCard(value,suit)) {
        if(value == 8) {
          Console consoleSuit = System.console();
          suit = -1;
          while(suit == -1) {
            String nextSuit = consoleSuit.readLine("Set the suit(Hearts, Diamond, Clubs, or Spades): ");
            if(nextSuit.equalsIgnoreCase("Hearts")) {
              suit = 1;
            } else if(nextSuit.equalsIgnoreCase("Diamond")) {
              suit = 2;
            } else if(nextSuit.equalsIgnoreCase("Clubs")) {
              suit = 3;
            } else if(nextSuit.equalsIgnoreCase("Spades")) {
              suit = 4;
            } else {
              suit = -1;
            }
          }
          disCard.addCard(c);
          disCard.addCard(new Card(value, suit));
        } else {
          if(c.getValue() != value && c.getSuit() != suit) {
            hand.addCard(new Card(value, suit));
            value = -1;
          } else {
            disCard.addCard(new Card(value, suit));
          }
        }
      } else {
        value = -1;
      }
    }
  }

  public static void drawHand(int cards, abstractDeck to, abstractDeck from) {
    for(int i = 1;i <= cards;i++) {
      to.addCard(from.drawCard());
    }
  }

}
