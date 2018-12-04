class Card {
  private int value;
  private int suit;

  public Card(int v, int s) {
    this.value = v;
    this.suit = s;
  }

  public int getValue() {
    return this.value;
  }

  public int getSuit() {
    return this.suit;
  }

  public String displayCard() {
    String description = " | ";
    switch(this.value) {
      case 1:
        description += "Ace";
        break;
      case 2:
        description += "Two";
        break;
      case 3:
        description += "Three";
        break;
      case 4:
        description += "Four";
        break;
      case 5:
        description += "Five";
        break;
      case 6:
        description += "Six";
        break;
      case 7:
        description += "Seven";
        break;
      case 8:
        description += "Eight";
        break;
      case 9:
        description += "Nine";
        break;
      case 10:
        description += "Ten";
        break;
      case 11:
        description += "Jack";
        break;
      case 12:
        description += "Queen";
        break;
      case 13:
        description += "King";
        break;
      default:
        description = "Invalid value for the card.";
    }
    description += " | ";
    switch(this.suit) {
      case 1:
        description += "Hearts";
        break;
      case 2:
        description += "Diamond";
        break;
      case 3:
        description += "Clubs";
        break;
      case 4:
        description += "Spades";
        break;
      default:
        description = "Invalid suit for the card.";
    }
    return description + " | ";
  }
}
