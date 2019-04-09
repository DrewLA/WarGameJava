/* 
 * High Low card game.
 *
 * Deal a card from the top of the deck then 
 *  guess whether the next card will be higher or lower.
 *
 * @author R. Morelli
 * 
 */
import java.awt.image.BufferedImage;
import java.util.Scanner;

/**
 * The HighLow class models a high-low card game.
 */
public class HighLow {

  /**
   *  Class constants for the keyboard commands.
   */
  public static final int LOW = 1;
  public static final int HIGH = 2;
  public static final int QUIT = 3;
  public static final int AGAIN = 4;
  
  /**
   * A Deck of cards.
   */
  private Deck deck;   

  /**
   * Top card of the deck.
   */
  private int top;     

  /**
   * The current upcard.
   */
  private Card upCard;

  /**
   * The card that is flipped after the user's guess.
   */
  private Card nextCard;

  /**
   * String representing the game's status.
   */
  private String status = "Next Card:";

  /**
   * Constructor creates a new deck and intializes the game.
   */
  public HighLow() {
    deck = new Deck();
    init();
  }

  /**
   *  Initialize the game by shuffling the deck and resetting top.
   */
  public void init() {
    deck.shuffle();
    top = 0;
    upCard = dealTopCard();
    status = "Next Card:";
  }

  /**
   * Deal cards from the deck.
   * @param n the number of cards to deal
   * @return a subdeck of the deck
   */
  public Deck deal(int n) {
    Deck hand = deck.subdeck(top, top + n -1);
    top += n;
    return hand;
  }

  /**
   * Deal one card from the top of the deck.
   * 
   * @return the top Card of the deck
   */
  public Card dealTopCard() {
    return deal(1).toArray()[0];
  }

  /**
   * Getter for upCard.
   * @return the current upCard
   */
  public Card getUpCard() {
    return upCard;
  }

  /**
   * Getter for the status string.
   * @return the game's current status
   */
  public String getStatus() {
    return status;
  }

  /**
   * A string representation of the game.
   * @return the current upCard
   */
  public String toString() {
    return "HIgh/Low upCard: " + upCard;
  }

  /**
   * Processes commands from the GUI.
   *
   * @param play a command from the GUI
   * @return a Card, either the upCard or the nextCard
   */
  public Card guiMove(int play) {
    if (play == AGAIN) {
      upCard = dealTopCard();
      return upCard;
    }
    nextCard = dealTopCard();
    int compare = nextCard.compareTo(upCard);
    if (play == LOW && compare < 0) {
      status = "Right";
    } else if (play == HIGH && compare > 0) {
      status = "Right";
    } else {
      status = "Wrong";
    }
    return nextCard;
  }

  /**
   * The play loop for the command-line version of the game.
   *
   * On each iteration the user is prompted to guess whether
   *  the next card will be higher or lower than the up card.
   *  Commands are LOW, HIGH, QUIT.
   */
  public void play() {
    Scanner scanner = new Scanner(System.in);
    String prompt = "Your play,  1=lower, 2=higher, " + QUIT + "=quit : ";
    int play = -1;
    while (play != QUIT) {
      System.out.println("upCard = " + upCard + " ");
      System.out.print(prompt);
      play = scanner.nextInt();
      if (play != QUIT) {
      	Card nextCard = dealTopCard();
      	int compare  = nextCard.compareTo(upCard); //next card  compare to upcard
      	if (play == LOW && compare < 0) {
      	  status = "Right";
      	} else if (play == HIGH && compare > 0) {
      	  status = "Right";
      	} else {
      	  status = "Wrong";
      	}
      	System.out.println(nextCard  + " " + status);      
      	upCard = dealTopCard();  // Deal another card.
      }
    }   
  }

  public static void main(String argv[]) {
    HighLow hl = new HighLow();
    hl.play();
    System.out.println("Bye");
  }
}