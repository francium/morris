package se2aa4.morris;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

/**
 * Controller class
 * Handles UI events and communicates with Game (model)
 * @author Varun Hooda 001412942
 * @author Aushim Lakhana 001201528
 * @author Matthew Shortt 001417616
 * @version 1.0
 */
public class Controller implements Initializable {

	// initialize game object
	private Game game = new Game();

	// paint constants
	private Paint COLRED = Paint.valueOf("#ff0000"),
				  COLBLUE = Paint.valueOf("#0000ff"),
				  COLBLACK = Paint.valueOf("#000000"),
				  COLGREEN = Paint.valueOf("#00ff00"),
				  COLYELLOW = Paint.valueOf("#f4de00");

	// UI elements
	@FXML
	private Button endTurnButton;
	@FXML
	private Shape iR0, iR1, iR2, iR3, iR4, iR5,
				  iB0, iB1, iB2, iB3, iB4, iB5,
				  nONW, nON, nONE, nOE, nOSE, nOS, nOSW, nOW,
				  nINW, nIN, nINE, nIE, nISE, nIS, nISW, nIW;
	@FXML
	private Text msgLabel;

	/**
	 * initialize ui
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// unused, but required
	}

	@FXML
	private void processNewGame(ActionEvent event) {
		// handle new game button click
		resetNodeFill(game.getSelected());
		game.newGame();
		// enable button
		endTurnButton.setDisable(false);
		for (Node n: Node.values()) {
			// clear frame of pieces
			if (n != Node.NONODE && n != Node.INVALID) {
				resetNodeFill(n);
				if (n.getId().contains("n")) resetNodeRadius(n);
				if (!getShape(n).isVisible()) getShape(n).setVisible(true);
			}
		}
		// display game state
		msgLabel.setText(game.getWhosTurn().toString() + "'s Turn");
	}

	@FXML
	private void processEndTurn(ActionEvent event) {
		// handle end turn button click
		if (game.getIsMoveMade() && game.getIsValid()
				  && !game.getAreMultipleMovesMade()) {
			// deselect any pieces
			if (game.getSelected() != Node.NONODE) {
				resetNodeFill(game.getSelected());
			}
			game.endTurn();
			// display game state
			msgLabel.setText(game.getWhosTurn().toString() + "'s Turn");
		} else {
			// can't end turn
			if (!game.getIsMoveMade()) {
				// no moves made
				msgLabel.setText(msgLabel.getText() + " | No moves made");
			}
			else if (game.getAreMultipleMovesMade()) {
				// multiple moves
				changeNodeFill(getShape(game.getLastMove()), COLYELLOW);
				msgLabel.setText(msgLabel.getText() + " | Multiple moves");
			} else {
				// overlapping pieces
				changeNodeFill(getShape(game.getInvalidNode()), COLYELLOW);
				msgLabel.setText(msgLabel.getText() + " | Overlapping Pieces");
			}
			endTurnButton.setDisable(true);
		}
	}

	// TODO for future implementation
	@FXML
	private void processRestore(ActionEvent event) {
		game.restore();
	}

	@FXML
	private void processNodeClick(MouseEvent event) {
		// handle node (pieces) click
		// if game isn't started yet, do nothing
		if (!game.isStarted()) return;
		String nodeId = ((Shape)event.getSource()).getId();
		Node node = Node.valueOf(nodeId);

		// if another node has been clicked since last time
		if (node != game.getSelected()) resetNodeFill(game.getSelected());

		// if play piece and not frame node
		if (node.getId().contains("i")) {
			// only allow player to select own piece
			if (node.getId().contains("R") && game.getWhosTurn() == Player.RED) {
				game.setSelected(node);
				changeNodeFill(getShape(node), COLGREEN);
			} else if (node.getId().contains("B") && game.getWhosTurn() == Player.BLUE) {
				game.setSelected(node);
				changeNodeFill(getShape(node), COLGREEN);
			}
		} else {
			// if node selected is frame node
			if (game.getSelected() != Node.NONODE) {
				getShape(game.getSelected()).setVisible(false);
				Shape shape = getShape(node);
				((Circle)shape).setRadius(10);
				if (game.getWhosTurn() == Player.RED) changeNodeFill(shape, COLRED);
				else changeNodeFill(shape, COLBLUE);
				game.move(node);
			}
		}
		// display game state
		msgLabel.setText(game.getWhosTurn().toString() + "'s Turn");
	}

	private void resetNodeFill(Node node) {
		// reset nodes to original colour
		if (node == Node.NONODE) return;
		if (node.getId().contains("R")) changeNodeFill(getShape(node), COLRED);
		else if (node.getId().contains("B")) changeNodeFill(getShape(node), COLBLUE);
		else changeNodeFill(getShape(node), COLBLACK);
	}

	// reset nodes to original radius
	private void resetNodeRadius(Node node) {
		((Circle)getShape(node)).setRadius(8);
	}

	// change node colour
	private void changeNodeFill(Shape shape, Paint paint) {
		shape.setFill(paint);
	}

	// get javafx shape from a given Node type
	private Shape getShape(Node node) {
		switch (node.getId()) {
			case ("iR0"):
				return iR0;
			case ("iR1"):
				return iR1;
			case ("iR2"):
				return iR2;
			case ("iR3"):
				return iR3;
			case ("iR4"):
				return iR4;
			case ("iR5"):
				return iR5;
			case ("iB0"):
				return iB0;
			case ("iB1"):
				return iB1;
			case ("iB2"):
				return iB2;
			case ("iB3"):
				return iB3;
			case ("iB4"):
				return iB4;
			case ("iB5"):
				return iB5;
			case ("nONW"):
				return nONW;
			case ("nON"):
				return nON;
			case ("nONE"):
				return nONE;
			case ("nOE"):
				return nOE;
			case ("nOSE"):
				return nOSE;
			case ("nOS"):
				return nOS;
			case ("nOSW"):
				return nOSW;
			case ("nOW"):
				return nOW;
			case ("nINW"):
				return nINW;
			case ("nIN"):
				return nIN;
			case ("nINE"):
				return nINE;
			case ("nIE"):
				return nIE;
			case ("nISE"):
				return nISE;
			case ("nIS"):
				return nIS;
			case ("nISW"):
				return nISW;
			case ("nIW"):
				return nIW;
			default:
				return null;
		}
	}

}
