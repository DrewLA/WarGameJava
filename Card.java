/* 
 * Example code for Think Java (http://thinkapjava.com)
 *
 * Copyright(c) 2011 Allen B. Downey
 * GNU General Public License v3.0 (http://www.gnu.org/copyleft/gpl.html)
 *
 * @author Allen B. Downey
 * @author CPSC 115
 * 
 * Modified for CPSC 115 - Fall 2015 by R. Morelli.
 *  - Added toString(), equals(), compareTo().
 */

/**
 * A Card represents a playing card with rank and suit.
 */
public class Card {

  /**
   * The Cards suit, where  0=clubs, 1=diamonds, 2=hearts, 3=spades.
   */
  private int suit;

  /**
   * The Cards rank, where  1=Ace, 11=jack, 12=queen, 13=king
   */
  private int rank;

  /**
   *  Static array of suit names.
   */
  public static String[] suits = { "C", "D", "H", "S" };

  /**
   *  Static array of rank names.
   */
  public static String[] ranks = { "", "A", "2", "3", "4", "5", "6",
				   "7", "8", "9", "10", "J", "Q", "K" };

  /**
   * Default constructor initializes both rank and suit to 0.
   * @return a Card with suit and rank both set to 0
   */
  public Card() { 
    this.suit = 0;  this.rank = 0;
  }

  /**
   * Parametric onstructor initializes rank and suit from parameters.
   * @param suit the Card's suit, 0=clubs, 1=diamonds, 2=hearts, 3=spades
   * @param rank the Card's rank, 1 to 13, 11=jack, 12=queen, 13=king
   * @return an initialized Card
   */
  public Card(int suit, int rank) { 
    this.suit = suit;  this.rank = rank;
  }

  /**
   *  Returns a String representation of a Card.
   * 
   * @return the Cards rank followed by suit with no spaces.
   */
  public String toString() {
    return ranks[rank] + "" + suits[suit];
  }

  /**
   * Returns the Card's suit.
   * @return a number between 0 and 3
   */
  public int getSuit() {
    return suit;
  }

  /**
   * Sets the Card's suit.
   * @param suit the Card's suit, 0=clubs, 1=diamonds, 2=hearts, 3=spades
   */
  public void setSuit(int suit) {
    this.suit = suit;
  }

  /**
   * Returns the Card's rank.
   * @return a number between 1 and 13
   */
  public int getRank() {
    return rank;
  }

  /**
   * Sets the Card's rank.
   * @param rank the Card's rank, 1 to 13, 11=jack, 12=queen, 13=king
   */
  public void setRank(int rank) {
    this.rank = rank;
  }

  /**
   * Determines equality of two cards, this and other.
   * @param other a Card with suit and rank
   * @return true if this and other have the same rank and suit, otherwise false
   */
  public boolean equals(Card other) {
    return this.suit == other.suit && this.rank == other.rank;
  }

  /**
   * Compares this card to an other card.
   * @param other a Card with suit and rank
   * @return a negative number if this less than other, 0 if equal, a positive value if this greater than other
   */
//   public int compareTo(Object other) {
//     return this.rank - ((Card) other).rank;
//   }

  public int compareTo(Object other) {
    int rankThis = (this.rank == 1) ? 14 : this.rank;
    int rankOther = (((Card)other).rank == 1) ? 14 : ((Card)other).rank;
    return rankThis - rankOther;  
  }

  /*
   * Testing code for the Card class
   */
  public static void main(String args[]) {
    Deck decktest = new Deck();
    decktest.shuffle();
    decktest.sort();
    System.out.println(decktest.toString());
    Card c1 = new Card(1, 11);   // Jack of diamonds
    System.out.println("First card = " + c1);
    Card c2 = new Card(3, 12);   // Queen of spades
    System.out.println("Second card = " + c2);
    Card c3 = new Card(3, 12);   // Queen of spades
    System.out.println("Third card = " + c3);

    System.out.println("Card 1 equals Card2 = " + c1.equals(c2));

    int comp = c1.compareTo(c2);
    if (comp < 0) {
      System.out.println(c1 + " comes before " + c2);
    } else if (comp > 0) {
      System.out.println(c1 + " comes after " + c2);
    } else {
      System.out.println(c1 + " equals " + c2);
    }

    comp = c3.compareTo(c2);
    if (comp < 0) {
      System.out.println(c3 + " comes before " + c2);
    } else if (comp > 0) {
      System.out.println(c3 + " comes after " + c2);
    } else {
      System.out.println(c3 + " equals " + c2);
    }
  }
}

