import java.util.*;
public class Deck extends abstractDeck {
  private ArrayList<Card> myDeck;

  // full deck of card originally
  public Deck() {
    this.myDeck = new ArrayList<>();
    for(int suit = 1; suit <= 4;suit++) {
      for(int val = 1; val <= 13;val++) {
        this.myDeck.add(new Card(val, suit));
      }
    }
  }

  // empty hand/deck to add card to
  public Deck(int count) {
    this.myDeck = new ArrayList<>(count);
  }

  public int getSize() {
    return this.myDeck.size();
  }

  public void addCard(Card c) {
    this.myDeck.add(c);
  }

  public boolean removeCard(int val, int suit) {
    for(Card c : this.myDeck) {
      if(c.getValue() == val && c.getSuit() == suit) {
        this.myDeck.remove(c);
        return true;
      }
    }
    return false;
  }

  public Card drawCard() {
    if(this.myDeck.size() == 0) return null;
    Random rand = new Random();
    return this.myDeck.remove(rand.nextInt(this.myDeck.size()));
  }

  //merge the deck with myDeck
  public void mergeDeck(abstractDeck deck) {
    while(deck.getSize() > 0) {
      addCard(deck.drawCard());
    }
  }

  public void shuffle() {
    abstractDeck deck = new Deck(0);
    deck.mergeDeck(this);
    mergeDeck(deck);
  }

  public String displayDeck() {
    String description = "";
    for(Card c : this.myDeck) {
      description += c.displayCard() + " ";
    }
    return description;
  }

  public Card printFirstCard() {
    return this.myDeck.remove(this.myDeck.size() - 1);
  }
}
