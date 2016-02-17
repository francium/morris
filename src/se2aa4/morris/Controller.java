package se2aa4.morris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class Controller {

	private Game game = new Game();
	
	@FXML
	private void processNewGame(ActionEvent event) {
		game.newGame();
		System.out.println("NewGame");
	}
	
	@FXML
	private void processEndTurn(ActionEvent event) {
		System.out.println("EndTurn");
	}
	
	@FXML
	private void processRestore(ActionEvent event) {
		System.out.println("Restore");
	}
}
