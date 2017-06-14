package launcher.localLauncher;

import java.awt.Dimension;
import java.util.Observer;

import javax.swing.JFrame;

import controller.ChessGameControlers;
import controller.controlerLocal.ChessGameControler;
import model.observable.ChessGame;
import vue.ChessGameGUI;



/**
 * @author francoise.perrin
 * Lance l'exécution d'un jeu d'échec en mode graphique.
 * La vue (ChessGameGUI) observe le modèle (ChessGame)
 * les échanges passent par le contrôleur (ChessGameControlers)
 * 
 */
public class LauncherGUI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ChessGame chessGame = new ChessGame();
		ChessGameControlers chessGameControler = new ChessGameControler(chessGame);
		JFrame frame;
		Dimension dim = new Dimension(700, 700);
		
		frame = new ChessGameGUI("Jeu d'échec", chessGameControler,  dim);
		chessGame.addObserver((Observer) frame);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(600, 10);
		frame.setPreferredSize(dim);
                frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}
}
