package tictactoe;

import javax.swing.SwingUtilities;


/**
 * Method takes care of any updates to the GUI that is implemented in the player class.
 * @author IsaackMorales
 *
 */
public class main {

/**
 * Method takes care of any updates to the GUI that is implemented in the player class.
 *
 *
 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() { //implements an interface by calling an anonymous class

			@Override
			public void run() {
				new player(); //instance of the player class
				
			}
			
		});
		
	}

}
