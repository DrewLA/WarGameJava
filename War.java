/* 
 * War card game.
 * 2 players
 * Shuffle the deck and deal 26 cards to each player
 * Both players flip the top cards at the same time
 * Card with a higher value win, and winner take both cards, placing them face up at the bottom
 * Continue flipping, when you reach the face up cards, shuffle them
 * If 2 cards are ==, both players take a card from their deck and place it face down on the 1st 
 * card, and take a 3rd card from their deck and place ot face up, players w/ higher value win
 * all cards. Keep doing this if the cards are equal.
 * A player win if he or she collects all 52 cards
 * 2 is the highest and Ace is the lowest 
 * @author Binh Vo and Andrew Lewis
 * 
 */
import java.awt.image.BufferedImage;
import java.util.Scanner;

/**
 * The HighLow class models a high-low card game.
 */
public class War {

  public static final int QUIT = 2;
  public static final int DRAW = 1;


	private Deck deck; //standard deck of 52 cards
	private Deck deckPlayer; //Player 1 with 26 cards
	private Deck deckComp; //Computer with 26 cards

	private int top;

	private int topPlayer; //top card for player 1
	private int topComp; //top card for Computer

	private Card upCardPlayer; //up card for player 1
	private Card upCardComp; //up card for Computer
  private Card cards[];

  private String status; //Status of the game
  private int play;
  private int playerScore;
  private int computerScore;
  private int gameStop;
  private String score;

	public War() { 				//Constructing the War game 
		init();
	}

	public void init() { //initializes the game
    deck = new Deck();
		deck.shuffle();
		top = 0;
		topPlayer = 0;
		topComp = 0;
    gameStop = 0;
    playerScore = 0;
    computerScore = 0;
    cards = new Card[2];
		deckPlayer = deal(26);
		deckComp = deal(26);		
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
   * Deal cards from the computer's deck.
   * @param n the number of cards to deal
   * @return a subdeck of the deck
   */
  public Deck dealComp(int n) {
    Deck hand = deckComp.subdeck(topComp, topComp + n -1);
    topComp += n;
    return hand;
  }

  /**
   * Deal cards from the player's deck.
   * @param n the number of cards to deal
   * @return a subdeck of the deck
   */
  public Deck dealPlayer(int n) {
    Deck hand = deckPlayer.subdeck(topPlayer, topPlayer + n -1);
    topPlayer += n;
    return hand;
  }


   /**
   * Deal one card from the top of the computer's deck.
   * 
   * @return the top Card of the deck
   */
  public Card dealTopComp() {
    return dealComp(1).toArray()[0];
  }

  /**
   * Deal one card from the top of the playter's deck.
   * 
   * @return the top Card of the deck
   */
  public Card dealTopPlayer() {
    return dealPlayer(1).toArray()[0];
  }
  
  /**
   * Getter for upCardPlayer.
   * @return the current upCardPlayer
   */
  public Card getUpCardPlayer() {
    return upCardPlayer;
  }
  /**
   * Getter for upCardComp.
   * @return the current upCardComp.
   */
  public Card getUpCardComp() {
    return upCardComp;
  }

  /**
   * Getter for the status string.
   * @return the game's current status
   */
  public String getStatus() {
    return status;
  }
  /**
   * Getter for the status string.
   * @return the game's current score
   */
  public String getScore() {
    return score;
  }

  /**
   * Processes commands from the GUI.
   *
   * @param play a command from the GUI
   * @return the upCardPlayer
   * @return the upCardComp
   */
  public Card[] guiMove(int play) {

    //System.out.println("rournd: " + gameStop); //Debug code for monitoring gameStop
    if (play == DRAW && gameStop < 26) {
      upCardPlayer = dealTopPlayer();
      upCardComp = dealTopComp();
      cards[0] = upCardPlayer;
      cards[1] = upCardComp; 
      int compare = upCardComp.compareTo(upCardPlayer);      
      if (compare < 0) {
        status = "Your card is Lower.";
        computerScore += 1;        
      } else if( compare > 0) {
        status = "Your card is Higher.";
        playerScore += 1;
      }else {
          status = "It is a war, you get nothing out of a war";
        }
        score  = "Player (Left Deck): " + playerScore + " Computer (Right Deck): " + computerScore;
        gameStop +=1;
      return cards;
    }

    else if (play == DRAW && gameStop == 26) { 
      if (playerScore > computerScore) {
        status = "Congrats, you are the winner!";
      } else {
        status = "You lost!";
      }

      return cards;

    }
    else if (play == QUIT) {
      status = "Thanks for playing, Bye!, press 'Draw Card' to restart";
      score = "";
      init();
    }
    
    return cards;
  }

  //play method
  public void play() {
  	Scanner scanner = new Scanner(System.in);
    String prompt = "Your play,  1=draw card, 2=quit: ";
     play = -1;
     playerScore = 0;
     computerScore = 0;
     gameStop = 0;
    while (play != QUIT && gameStop < 26) {
      System.out.print(prompt);
      play = scanner.nextInt();
      if (play != QUIT) {
      	upCardPlayer = dealTopPlayer(); //get player's card 
      	System.out.println("Your card: " + upCardPlayer.toString());	//printing player's card
      	upCardComp = dealTopComp();  //get computers's card
    		System.out.println("Computer's card: " + upCardComp.toString());	//printing computer's card
    		int compare  = upCardPlayer.compareTo(upCardComp); //compare player card with computer card
      	if (compare > 0) {
      	  status = "Your card is Higher!";
      	  playerScore += 1;
      	} else if (compare < 0) {
      	  status = "Your card is Lower";
      	  computerScore += 1;
      	} else {
      	  status = "It is a war, you get nothing out of a war";
      	}
      	gameStop += 1;
      	System.out.println(status); 
      	System.out.println("Player's Score is: " + playerScore);   
      	System.out.println("Computer's Score is: " + computerScore); 
      }
    }

    if (playerScore > computerScore) {
    	System.out.println("Congrats, you are the winner!");
    } else {
    	System.out.println("Hopefully, you play again!");
    }
  }

  public static void main(String argv[]) {
    War w = new War();
    w.play();
    System.out.println("Bye~");
  }

}







