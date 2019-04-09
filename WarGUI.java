/* 
 * War card game GUI. 
 * @author Binh Vo and Andrew Lewis
 * 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *  A GUI for playing the War card game.  The GUI
 *  is a panel (a container) that contains various graphical componentts. 
 */
public class WarGUI extends JPanel implements ActionListener {

  /**
   *  Width of the card image.
   */
  private static final int WIDTH=75;

  /**
   *  Height of the card image.
   */
  private static final int HEIGHT = 105;


  /**
   * Folder of the card images.
   */
  private static final String CARDSET = "cardset-oxymoron";

  /**
   * Reference to the high-low game.
   */
  private War war; 

  // Panels to hold the cards and buttons.
  private JPanel controlPanel = new JPanel();
  private JPanel cardPanel = new JPanel();
  private JLabel statusLabel;
  private JLabel scoreLabel; 
  private JLabel deckCompLabel;
  private JLabel deckPlayerLabel;
  private JButton quitButton = new JButton("Quit");
  private JButton drawButton = new JButton("Draw Card");
  
  /**
   *  Reference to the current topComp.
   */
  private Card topComp;

  /**
   *  Reference to the current topPlayer.
   */
  private Card topPlayer;
  
  /**
   *  Array of card images.
   */
  private BufferedImage[][] images;         

  /**
   *  Reference to image of the back of card.
   */
  private BufferedImage backImage;
  
  /**
   *  Constructor sets up the GUI Panel and intializes the game.
   */
  public WarGUI() {
    // Make this object the listener for button clicks, which are
    //  processed in the actionPerformed() method.
    quitButton.addActionListener(this);
    drawButton.addActionListener(this);
    //restartButton.addActionListener(this);

    // Create an instance of the game.
    war = new War();    

    // Initialize the images and the GUI.
    initializeImages();
    initialize();  
  }

  /**
   * Reads images from external gif files  into an array of BufferedImages.
   *
   * Example code for Think Java (http://thinkapjava.com)
   * Copyright(c) 2011 Allen B. Downey
   * GNU General Public License v3.0 (http://www.gnu.org/copyleft/gpl.html)
   *
   * @author Allen B. Downey
   * @author R. Morelli
   */
  private void initializeImages() {
    String suits = "cdhs";
    images = new BufferedImage[4][14];
    try {
      backImage = ImageIO.read(new File(CARDSET + "/" + "back111.gif"));
      for (int suit = 0; suit <= 3; suit++) {
	       for (int rank = 1; rank <= 13; rank++) {
          	  char ch = suits.charAt(suit);
          	  String filepath  = String.format("%s/%02d%c.gif", CARDSET, rank, ch);  // e.g., "cardset-oxymoron/05c.gif"
          	  images[suit][rank] = ImageIO.read(new File(filepath));
	}
      }
    } catch (IOException e) {
      System.out.println("IOException -- probably wrong path to card image");
    }
  }

  /**
   *  Initializes the game and the GUI. This is called
   *  when the app starts and when it is reset.
   */
  private void initialize() {

    // Initialize the game -- War stores the state of the game.
    war.init(); 

    // Remove all elements from the GUI and make it invisible.
    removeAll();
    setVisible(false);

    // Get the images for the deck (backImage) and the current up card.
    deckCompLabel = new JLabel(new ImageIcon(resizeImage(backImage, WIDTH, HEIGHT)));     
    //upCard = war.getUpCard();
    deckPlayerLabel = new JLabel(new ImageIcon(resizeImage(backImage, WIDTH, HEIGHT)));
    //new JLabel(new ImageIcon(resizeImage(images[upCard.getSuit()][upCard.getRank()], WIDTH, HEIGHT)));

    // Layout the up card and the deck images.
    cardPanel = new JPanel();
    cardPanel.setLayout(new GridLayout(1,3,10,10));
    cardPanel.add(deckCompLabel);
    cardPanel.add(deckPlayerLabel);
    //nextLabel = new JLabel();
    //cardPanel.add( nextLabel );  // Initially blank

    // Layout the control panel with buttons and label.
    controlPanel = new JPanel();
    statusLabel = new JLabel(war.getStatus());
    scoreLabel = new JLabel(war.getScore());
    controlPanel.add(statusLabel);
    controlPanel.add(scoreLabel);
    controlPanel.add(drawButton);
    controlPanel.add(quitButton);
    //controlPanel.add(againButton);

    // Add the various panels to this object, which is itself a panel.
    add(cardPanel);
    add(controlPanel);
    setVisible(true);
  }
			   
  /**
   *  Resizes the raw image.
   * @param image the raw image read from the gif file.
   * @param w the desired width
   * @param h the desired height
   * @return the resized image
   */
  private BufferedImage resizeImage(BufferedImage image, int w, int h) {
    BufferedImage resizedImage  = new BufferedImage(w, h, image.getType());
    Graphics2D g = resizedImage.createGraphics();
    g.drawImage(image, 0, 0, w, h, null);
    g.dispose();
    return resizedImage;
  }

  /**
   * The listener, which processes actions, such as button clicks, taken by the user.  
   *
   * There are three buttons, Again, Low, High. Again deals another card. Low guesses
   *  that the next card will be lower than the upcard. High guesses that the next
   *  card will be higher than the up card. The actual "moves", such as dealing, are
   *  performed by the highLow  object, not the GUI.
   */
  public void actionPerformed(ActionEvent e) {
    Card cardPlayer;
    Card cardComp;
    Card [] cards;
    if (e.getSource() == drawButton) {
      cards = war.guiMove(War.DRAW);
      cardPlayer = cards[0];
      cardComp = cards[1];
      deckPlayerLabel.setIcon(new ImageIcon(resizeImage(images[cardPlayer.getSuit()][cardPlayer.getRank()], WIDTH, HEIGHT)));
      deckCompLabel.setIcon(new ImageIcon(resizeImage(images[cardComp.getSuit()][cardComp.getRank()], WIDTH, HEIGHT)));
      statusLabel.setText(war.getStatus());
      scoreLabel.setText(war.getScore());
      } 
      else if (e.getSource() == quitButton) {
        war.guiMove(War.QUIT);
        statusLabel.setText(war.getStatus());
        scoreLabel.setText(war.getScore());
        }
      }
  /**
   *  Creates a top-level windoe (JFrame) and adds the GUI to it.  
   */
  public static void main(String argv[]) {

    // Our app's GUI
    WarGUI gui = new WarGUI();    

     // The top-level window
    JFrame window = new JFrame("War Card Game");
    window.setLocation(0,0);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add the GUI to the top-level window and display it.
    Container contentPane = window.getContentPane();
    contentPane.removeAll();
    contentPane.add((JPanel) gui);             // Add the GUI
    window.pack();    
    window.setSize(5 * WIDTH + 20, HEIGHT * 2);
    window.setVisible(true);    
  }
}