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
 *  Added toString(), Removed most static methods.
 */

/**
 * A Deck is an array of cards.
 */
class Deck {

  /**
   * The array containing the Deck's Cards.
   */
  private Card[] cards;

  /**
   * Parametric constructo makes a Deck with room for n Cards (but no Cards yet).
   * @param n the size of the Deck
   * @return an Deck with room for n Cards
   */
  public Deck(int n) {
    this.cards = new Card[n];
  }

  /**
   * Default constructor makes a standard Deck of 52 cards.
   * 
   * The Cards are ordered in the standard way:
   * AC 2C ... KC AD 2D ... KD AH 2H ... KH AS 2S ... KS
   *
   * @return a Deck with 52 standard Cards.
   */
  public Deck() {
    this.cards = new Card [52];
	
    int index = 0;
    for (int suit = 0; suit <= 3; suit++) {
      for (int rank = 1; rank <= 13; rank++) {
      	this.cards[index] = new Card(suit, rank);
      	index++;
      }
    }
  }

  /**
   * Returns a String representation of the Deck.
   *
   * @return a string with Card names
   */
  public String toString() {
    String s = "";
    for (int i=0; i < cards.length; i++) {
      s += cards[i] + " ";
    }
    return s;
  }

  /**
   * Getter for the cards array.
   * @return an array of Cards
   */
  public Card[] toArray() {
    return cards;
  }

  /**
   * Finds a card in a deck of cards.
   *
   * @param deck a Deck of 0 or more Cards
   * @param card a Card with a suit and rank
   * @return the index of the first occurrence of card in deck  or -1 if no occurrence
   */

  public static int findCard (Deck deck, Card card) {
    for (int i = 0; i< deck.cards.length; i++) {
      if (card.equals(deck.cards[i])) {
	return i;
      }
    }
    return -1;
  }

  /**
   * Generate a random int in the range [low, high).
   * @param low the bound in the range
   * @param high the upper bound of the range
   * @return a random int between low and high, including low but not including high. 
   */
  public static int randInt(int low, int high) {
    int range = high - low;
    return low + (int)(Math.random() * range);
  }

  /**
   * Swaps the cards at indexes i and j in the cards array.
   * @param i index of one card
   * @param j index of the other card
   */
  private void swapCards(int i, int j) {
    Card temp = cards[i];
    cards[i] = cards[j];
    cards[j] = temp;
  }

  /**
   * Shuffles the deck.
   *
   * Algorithm: For each card, C, in the deck choose a random card whose
   * index is >= C's index and swap them. 
   */
  public void shuffle() {
    for (int i = 0; i < cards.length; i++) {
      int j = Deck.randInt(i, cards.length);
      swapCards(i,j);
    }
  }

  /**
   * Sorts the deck using selection sort algorithm.
   *
   * Algorithm:  For each card, C,  in the deck, find the smallest
   *  card whose index is >= C's index and swap them.
   */
  public void sort() {
    for (int i = 0; i < cards.length; i++) {
      int j = indexLowestCard(i, cards.length-1);
      swapCards(i, j);
    }
  }

  /**
   * Finds the lowest card in Deck in the range low to high inclusive, i.e., [low, high].
   * @param low the lower bound of the range
   * @param high the upper bound of the range, included in the range
   * @return the index of the lowest card in the range [low,high]
   */
  private int indexLowestCard(int low, int high) {
    int lowIndex = low;
    for (int i = low; i <= high; i++) {
      Card card = cards[i];
      int comp = card.compareTo(cards[lowIndex]);
      if (comp < 0) {
	lowIndex = i;
      }
    }
    return lowIndex;
  }

  /**
   * Find the lowest card in the deck
   * @return the index of the lowest card in the Deck.
   */
  public int indexLowestCard() {
    return indexLowestCard(0, cards.length-1);
  }

  /**
   * Makes a new deck of cards from a subset of cards from the original.
   *
   * The subdeck contains clones of the original cards.
   * @param low the lower bound of the range of the subdeck
   * @param high the upper bound of the range of the subdeck, inclusive
   * @param a new Deck consisting of copies of the cards at locations low...high of this Deck.
   */
  public Deck subdeck(int low, int high) {
    Deck sub = new Deck(high-low+1);
	
    for (int i = 0; i<sub.cards.length; i++) {
      sub.cards[i] = cards[low+i];
    }
    return sub;
  }

  /**
   *  Code to test the Deck class.
   */
  public static void main(String args[]) {

    Deck deck = new Deck();   // Create a deck of 52 cards and print it
    System.out.println("Printing the original deck.");
    System.out.println(deck);
    System.out.println("The Queen of Spades is at location " + Deck.findCard(deck, new Card(3,12)));
    System.out.println("The index of lowest card is " + deck.indexLowestCard());    

    deck.shuffle();
    System.out.println("\nPrinting the shuffled deck.");
    System.out.println(deck);
    System.out.println("The Queen of Spades is at location " + Deck.findCard(deck, new Card(3,12)));
    System.out.println("The index of lowest card is " + deck.indexLowestCard());    

    deck.sort();
    System.out.println("\nPrinting the sorted deck.");
    System.out.println(deck);
    System.out.println("The Queen of Spades is at location " + Deck.findCard(deck, new Card(3,12)));
    System.out.println("The index of lowest card is " + deck.indexLowestCard());    

//     System.out.println("Shuffling and dealing two hands");
//     deck.shuffle();
//     System.out.println(deck);
//     Deck hand1 = deck.subdeck(0,4);
//     Deck hand2 = deck.subdeck(5,9);
//     System.out.println("Hand1: " + hand1);
//     System.out.println("Hand2: " + hand2);
  }
}
