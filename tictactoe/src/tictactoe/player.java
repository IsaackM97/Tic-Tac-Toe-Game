package tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;




import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Player class is used to create a Tic Tac Toe game with a GUI for the user and is immutable. 
 * @author IsaackMorales
 *
 */
public class player extends JFrame {


	private JButton[][]tictacboard; //board
	private Container pan; //container is named pan 

	private JMenuBar menub; //menu bar 
	private JMenu menu; //menu
	private JMenuItem quit; //item in menu to quit the game 
	private JMenuItem newgame; //item in menu to create a new game

	private String currentplyer; //current player
	private boolean is_a_winner; //the winner

	private int px = 0; //score counter for player x
	private int py = 0; //score counter for player y

	/**
	 * Method is the default constructor, where player class extends from  JFrame. 
	 * 
	 */
	public player() {

		super();
		pan = getContentPane(); //JFrame method

		pan.setLayout(new GridLayout(3,3)); //Layout of grid is 3x3

		setTitle("Tic Tac Toe "); //Title of frame

		setSize(900,700); //size of frame

		setResizable(false); //We do not  want the user to resize the frame, so setResizeable is set to false

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setVisible(true); //To see the GUI

		currentplyer = "X"; //Initial player is X

		tictacboard = new JButton[3][3];

		is_a_winner = false; //there is currently no winner 

		setup_board();
		setup_menu();



	}
	/**
	 * Methods initializes the menu bar of the frame.
	 * 
	 *  @throws MalformedURLException is thrown to indicate that a malformed URL has occurred. 
	 *  Either no legal protocol could be found in a specification string or the string could not be parsed.
	 * 
	 */
	public void setup_menu() { //Initializes menu bar 


		menub = new JMenuBar();

		menu = new JMenu("Help"); //menu tab name is Help


		newgame = new JMenuItem("Restart Game"); //option from clicking on Help tab: "New Game"


		newgame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				URL soundbyte = null;
				try {
					soundbyte = new File("/Users/IsaackMorales/documents/resource/air.wav").toURI().toURL();
				} catch (MalformedURLException e1) {

					e1.printStackTrace();

				} //sound of winning the game 
				java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
				clip.play(); //plays sound file
				new_board(); //resets the game board to empty boxes or buttons

			}

		});

		quit = new JMenuItem("Quit Game"); //option from clicking on Help tab: "Quit Game"
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				URL soundbyte = null;
				try {
					soundbyte = new File("/Users/IsaackMorales/documents/resource/claps.wav").toURI().toURL();
				} catch (MalformedURLException e1) {

					e1.printStackTrace();

				} //sound of winning the game 
				java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
				clip.play(); //plays sound file 
				JOptionPane.showMessageDialog(null,"You are about to quit." );
				System.exit(0); //terminates program

			}




		});

		menu.add(newgame); //adds New Game option in Help menu
		menu.add(quit);  //adds Quit Game option in Help menu
		menub.add(menu); //adds menu
		setJMenuBar(menub);


	}

	/**
	 * Method resets the board, once a new game has been started.
	 * 
	 */
	public void new_board() { //resets the board once the game is over if player X or player O has won a game

		currentplyer = "X";
		is_a_winner = false;
		for(int i=0; i < 3; i++) {
			for(int j =0; j < 3; j++) {
				tictacboard[i][j].setText("");



			}

		}

		JOptionPane.showMessageDialog(null,"A new game has started." );
	}

	/**
	 * Method initializes the Tic Tac Toe board.
	 * 
	 * 
	 */
	public void setup_board() { //Initializes board 

		for(int i=0; i < 3; i++) { //traverses through rows of matrix 
			for(int j =0; j < 3; j++) { //traverses through columns of matrix 

				JButton inp = new JButton(); //btn is the button
				inp.setFont(new Font(Font.MONOSPACED, Font.BOLD,150));  //sets the characteristics of btn
				tictacboard[i][j] = inp;
				inp.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(((JButton)e.getSource()).getText().equals("") && 
								is_a_winner == false){
							inp.setText(currentplyer);
							try {

								u_won();


							} catch (MalformedURLException e1) {
								e1.printStackTrace();
							}
							change_player();

						}



					}

				});
				pan.add(inp);

			}




		}
	}
	/**
	 * Method toggles Player X to Player O and vice versa via buttons.
	 * 
	 */
	public void change_player() { //if player is X once button is hit, switched to O
		if(currentplyer.equals("X")) {

			JOptionPane.showMessageDialog(null,"Player O turn" );

			currentplyer = "O";

		}

		else {
			JOptionPane.showMessageDialog(null,"Player X turn" );
			currentplyer = "X";
		}


	}


	/**
	 * Method checks to see if Player X or Player O has won or if there is a draw
	 *  by checking to see if the X and O values are placed in a row, column, or diagonal three times.
	 *  
	 *   @throws MalformedURLException is thrown to indicate that a malformed URL has occurred. 
	 *   Either no legal protocol could be found in a specification string or the string could not be parsed.
	 */
	public void u_won() throws MalformedURLException {

		//checks if met matching X and O in columns
		if(tictacboard[0][0].getText().contentEquals(currentplyer) && tictacboard[1][0].getText().equals(currentplyer) 
				&& tictacboard[2][0].getText().equals(currentplyer)) {
			URL soundbyte = new File("/Users/IsaackMorales/documents/resource/Win.wav").toURI().toURL(); //sound of winning the game 
			java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
			clip.play();
			JOptionPane.showMessageDialog(null,"Player " + currentplyer + " has won! " );
			is_a_winner = true;
			if(currentplyer.equals("X")) {
				px++;
				JOptionPane.showMessageDialog(null,"Player " + currentplyer + " score: " + px);
				System.out.println("Player " + currentplyer + " has won: " + px + " times");
			}
			if(currentplyer.equals("O")) {
				py++;
				JOptionPane.showMessageDialog(null,"Player " + currentplyer + " score: " + py);
				System.out.println("Player " + currentplyer + " has won: " + py + " times");
			}



		}
		//checks if met matching X and O in columns
		else if(tictacboard[0][1].getText().contentEquals(currentplyer) && tictacboard[1][1].getText().equals(currentplyer) 
				&& tictacboard[2][1].getText().equals(currentplyer)) {
			URL soundbyte = new File("/Users/IsaackMorales/documents/resource/Win.wav").toURI().toURL(); //sound of winning the game 
			java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
			clip.play();
			JOptionPane.showMessageDialog(null,"Player " + currentplyer + " has won! " );
			is_a_winner = true;

			if(currentplyer.equals("X")) {
				px++;
				JOptionPane.showMessageDialog(null,"Player " + currentplyer + " score: " + px);
				System.out.println("Player " + currentplyer + " has won: " + px + " times");
			}
			if(currentplyer.equals("O")) {
				py++;
				JOptionPane.showMessageDialog(null,"Player " + currentplyer + " score: " + py);
				System.out.println("Player " + currentplyer + " has won: " + py + " times");

			}

		}

		//checks if met matching X and O in columns
		else if(tictacboard[0][2].getText().contentEquals(currentplyer) && tictacboard[1][2].getText().equals(currentplyer) 
				&& tictacboard[2][2].getText().equals(currentplyer)) {
			URL soundbyte = new File("/Users/IsaackMorales/documents/resource/Win.wav").toURI().toURL(); //sound of winning the game 
			java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
			clip.play();
			JOptionPane.showMessageDialog(null," Player " + currentplyer + " has won! " );
			is_a_winner = true;
			if(currentplyer.equals("X")) {
				px++;
				JOptionPane.showMessageDialog(null,"Player " + currentplyer + " score: " + px);
				System.out.println("Player " + currentplyer + " has won: " + px + " times");
			}
			if(currentplyer.equals("O")) {
				py++;
				JOptionPane.showMessageDialog(null,"Player " + currentplyer + " score: " + py);
				System.out.println("Player " + currentplyer + " has won: " + py + " times");

			}


		}

		//checks if met matching X and O in rows
		else if(tictacboard[0][0].getText().contentEquals(currentplyer) && tictacboard[0][1].getText().equals(currentplyer) 
				&& tictacboard[0][2].getText().equals(currentplyer)) {
			URL soundbyte = new File("/Users/IsaackMorales/documents/resource/Win.wav").toURI().toURL(); //sound of winning the game 
			java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
			clip.play();
			JOptionPane.showMessageDialog(null," Player " + currentplyer + " has won!" );
			is_a_winner = true;
			if(currentplyer.equals("X")) {
				px++;
				JOptionPane.showMessageDialog(null,"Player " + currentplyer + " score: " + px);
				System.out.println("Player " + currentplyer + " has won: " + px + " times");
			}
			if(currentplyer.equals("O")) {
				py++;
				JOptionPane.showMessageDialog(null,"Player " + currentplyer + " score: " + py);
				System.out.println("Player " + currentplyer + " has won: " + py + " times");

			}

		}

		//checks if met matching X and O in rows
		else if(tictacboard[1][0].getText().contentEquals(currentplyer) && tictacboard[1][1].getText().equals(currentplyer) 
				&& tictacboard[1][2].getText().equals(currentplyer)) {
			URL soundbyte = new File("/Users/IsaackMorales/documents/resource/Win.wav").toURI().toURL(); //sound of winning the game 
			java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
			clip.play();
			JOptionPane.showMessageDialog(null," Player " + currentplyer + " has won!" );
			is_a_winner = true;
			if(currentplyer.equals("X")) {
				px++;
				JOptionPane.showMessageDialog(null,"Player " + currentplyer + " score: " + px);
				System.out.println("Player " + currentplyer + " has won: " + px + " times");
			}
			if(currentplyer.equals("O")) {
				py++;
				JOptionPane.showMessageDialog(null,"Player " + currentplyer + " score: " + py);
				System.out.println("Player " + currentplyer + " has won: " + py + " times");

			}

		}
		//checks if met matching X and O in rows
		else if(tictacboard[2][0].getText().contentEquals(currentplyer) && tictacboard[2][1].getText().equals(currentplyer) 
				&& tictacboard[2][2].getText().equals(currentplyer)) {
			URL soundbyte = new File("/Users/IsaackMorales/documents/resource/Win.wav").toURI().toURL(); //sound of winning the game 
			java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
			clip.play();
			JOptionPane.showMessageDialog(null," Player " + currentplyer + " has won!" );
			is_a_winner = true;
			if(currentplyer.equals("X")) {
				px++;
				JOptionPane.showMessageDialog(null,"Player " + currentplyer + " score: " + px);
				System.out.println("Player " + currentplyer + " has won: " + px + " times");
			}
			if(currentplyer.equals("O")) {
				py++;
				JOptionPane.showMessageDialog(null,"Player " + currentplyer + " score: " + py);
				System.out.println("Player " + currentplyer + " has won: " + py + " times");

			}

		}
		//checks if met matching X and O  diagonally 
		else if(tictacboard[0][0].getText().contentEquals(currentplyer) && tictacboard[1][1].getText().equals(currentplyer) &&
				tictacboard[2][2].getText().equals(currentplyer)) {
			URL soundbyte = new File("/Users/IsaackMorales/documents/resource/Win.wav").toURI().toURL(); //sound of winning the game 
			java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
			clip.play();
			JOptionPane.showMessageDialog(null," Player " + currentplyer + " has won!" );

			is_a_winner = true;
			if(currentplyer.equals("X")) {
				px++;
				JOptionPane.showMessageDialog(null,"Player " + currentplyer + " score: " + px);
				System.out.println("Player " + currentplyer + " has won: " + px + " times");
			}
			if(currentplyer.equals("O")) {
				py++;
				JOptionPane.showMessageDialog(null,"Player " + currentplyer + " score: " + py);
				System.out.println("Player " + currentplyer + " has won: " + py + " times");

			}

		}
		//checks if met matching X and O  diagonally 
		else if(tictacboard[0][2].getText().contentEquals(currentplyer) && tictacboard[1][1].getText().equals(currentplyer)
				&& tictacboard[2][0].getText().equals(currentplyer)) {
			URL soundbyte = new File("/Users/IsaackMorales/documents/resource/Win.wav").toURI().toURL(); //sound of winning the game 
			java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
			clip.play();
			JOptionPane.showMessageDialog(null," Player " + currentplyer + " has won!" );
			is_a_winner = true;
			if(currentplyer.equals("X")) {
				px++;
				JOptionPane.showMessageDialog(null,"Player " + currentplyer + " score: " + px);
				System.out.println("Player " + currentplyer + " has won: " + px + " times");
			}
			if(currentplyer.equals("O")) {
				py++;
				JOptionPane.showMessageDialog(null,"Player " + currentplyer + " score: " + py);
				System.out.println("Player " + currentplyer + " has won: " + py + " times");

			}

		}

		//checks to see if there is a draw
		else if (!tictacboard[0][0].getText().isEmpty() && !tictacboard[0][1].getText().isEmpty()  && !tictacboard[0][2].getText().isEmpty() && !tictacboard[1][0].getText().isEmpty()
				&& !tictacboard[1][1].getText().isEmpty() && !tictacboard[1][2].getText().isEmpty()
				&& !tictacboard[2][0].getText().isEmpty() && !tictacboard[2][1].getText().isEmpty()
				&& !tictacboard[2][2].getText().isEmpty() && is_a_winner == false) {

			URL soundbyte = new File("/Users/IsaackMorales/documents/resource/Sad.wav").toURI().toURL(); //sound of winning the game 
			java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
			clip.play();
			JOptionPane.showMessageDialog(null, "There is a draw!");

		}


	}

}
