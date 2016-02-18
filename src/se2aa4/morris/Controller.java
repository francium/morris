package se2aa4.morris;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

// test

public class Controller implements Initializable {

	private Game game = new Game();
	
	private Paint COLRED = Paint.valueOf("#ff0000"),
				  COLBLUE = Paint.valueOf("#0000ff"),
				  COLGREEN = Paint.valueOf("#00ff00");
	
	@FXML
	private Shape iR0, iR1, iR2, iR3, iR4, iR5,
				  iB0, iB1, iB2, iB3, iB4, iB5,
				  nONW, nON, nONE, nOE, nOSE, nOS, nOSW, nOW,
				  nINW, nIN, nINE, nIE, nISE, nIS, nISW, nIW;
	
	@FXML
	private Text msgLabel;
	
	@FXML
	private void processNewGame(ActionEvent event) {
		resetNodeFill(game.getSelected());
		game.newGame();
		msgLabel.setText(game.getWhosTurn().toString() + "'s Turn");
	}
	
	@FXML
	private void processEndTurn(ActionEvent event) {
		System.out.println("EndTurn");
	}
	
	@FXML
	private void processRestore(ActionEvent event) {
		System.out.println("Restore");
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
				changeNodeFill(node, COLGREEN);
			} else if (node.getId().contains("B") && game.getWhosTurn() == Player.BLUE) {
				game.setSelected(node);
				changeNodeFill(node, COLGREEN);
			} else {
				// clicked other players piece
			}
			
			// if frame node clicked
		}
		msgLabel.setText(game.getWhosTurn().toString() + "'s Turn");
	}
	
	private void resetNodeFill(Node node) {
		if (node == Node.NONODE) return;
		if (node.getId().contains("R")) changeNodeFill(node, COLRED);
		else changeNodeFill(node, COLBLUE);
	}
	
	private void changeNodeFill(Node node, Paint paint) {
		switch (node.getId()) {
			case ("iR0"):
				iR0.setFill(paint);
				break;
			case ("iR1"):
				iR1.setFill(paint);
				break;
			case ("iR2"):
				iR2.setFill(paint);
				break;
			case ("iR3"):
				iR3.setFill(paint);
				break;
			case ("iR4"):
				iR4.setFill(paint);
				break;
			case ("iR5"):
				iR5.setFill(paint);
				break;
			case ("iB0"):
				iB0.setFill(paint);
				break;
			case ("iB1"):
				iB1.setFill(paint);
				break;
			case ("iB2"):
				iB2.setFill(paint);
				break;
			case ("iB3"):
				iB3.setFill(paint);
				break;
			case ("iB4"):
				iB4.setFill(paint);
				break;
			case ("iB5"):
				iB5.setFill(paint);
				break;
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
