package se2aa4.morris;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Controller implements Initializable {

	private Game game = new Game();

	private Paint COLRED = Paint.valueOf("#ff0000"),
				  COLBLUE = Paint.valueOf("#0000ff"),
				  COLBLACK = Paint.valueOf("#000000"),
				  COLGREEN = Paint.valueOf("#00ff00"),
				  COLPINK = Paint.valueOf("#f4de00");

	@FXML
	private Button endTurnButton;

	@FXML
	private Shape iR0, iR1, iR2, iR3, iR4, iR5,
				  iB0, iB1, iB2, iB3, iB4, iB5,
				  nONW, nON, nONE, nOE, nOSE, nOS, nOSW, nOW,
				  nINW, nIN, nINE, nIE, nISE, nIS, nISW, nIW;

	@FXML
	private Text msgLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

	@FXML
	private void processNewGame(ActionEvent event) {
		resetNodeFill(game.getSelected());
		game.newGame();
		endTurnButton.setDisable(false);
		for (Node n: Node.values()) {
			// clear frame
			if (n != Node.NONODE && n != Node.INVALID) {
				resetNodeFill(n);
				if (n.getId().contains("n")) resetNodeRadius(n);
				if (!getShape(n).isVisible()) getShape(n).setVisible(true);
			}
		}
		msgLabel.setText(game.getWhosTurn().toString() + "'s Turn");
	}

	@FXML
	private void processEndTurn(ActionEvent event) {
		if (game.getIsMoveMade() && game.getIsValid()
				  && !game.getAreMultipleMovesMade()) {
			if (game.getSelected() != Node.NONODE) {
				resetNodeFill(game.getSelected());
			}
			game.endTurn();
			msgLabel.setText(game.getWhosTurn().toString() + "'s Turn");
		} else {
			// TODO different errors
			if (!game.getIsMoveMade()) {
				// no moves made
				msgLabel.setText(msgLabel.getText() + " | No moves made");
			}
			else if (game.getAreMultipleMovesMade()) {
				// multiple moves
				changeNodeFill(getShape(game.getLastMove()), COLPINK);
				msgLabel.setText(msgLabel.getText() + " | Multiple moves");
			} else {
				// overlapping pieces
				changeNodeFill(getShape(game.getInvalidNode()), COLPINK);
				msgLabel.setText(msgLabel.getText() + " | Overlapping Pieces");
			}
			endTurnButton.setDisable(true);
		}
	}

	@FXML
	private void processRestore(ActionEvent event) {
		game.restore();
	}

	@FXML
	private void processNodeClick(MouseEvent event) {
		if (!game.isStarted()) return;
		String nodeId = ((Shape)event.getSource()).getId();
		Node node = Node.valueOf(nodeId);

		if (node != game.getSelected()) resetNodeFill(game.getSelected());

		if (node.getId().contains("i")) {
			if (node.getId().contains("R") && game.getWhosTurn() == Player.RED) {
				game.setSelected(node);
				changeNodeFill(getShape(node), COLGREEN);
			} else if (node.getId().contains("B") && game.getWhosTurn() == Player.BLUE) {
				game.setSelected(node);
				changeNodeFill(getShape(node), COLGREEN);
			}
		} else {
			if (game.getSelected() != Node.NONODE) {
				getShape(game.getSelected()).setVisible(false);
				Shape shape = getShape(node);
				((Circle)shape).setRadius(10);
				if (game.getWhosTurn() == Player.RED) changeNodeFill(shape, COLRED);
				else changeNodeFill(shape, COLBLUE);
				game.move(node);
			}
		}
		msgLabel.setText(game.getWhosTurn().toString() + "'s Turn");
	}

	private void resetNodeFill(Node node) {
		if (node == Node.NONODE) return;
		if (node.getId().contains("R")) changeNodeFill(getShape(node), COLRED);
		else if (node.getId().contains("B")) changeNodeFill(getShape(node), COLBLUE);
		else changeNodeFill(getShape(node), COLBLACK);
	}

	private void resetNodeRadius(Node node) {
		((Circle)getShape(node)).setRadius(8);
	}
	
	private void changeNodeFill(Shape shape, Paint paint) {
		shape.setFill(paint);
	}

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
