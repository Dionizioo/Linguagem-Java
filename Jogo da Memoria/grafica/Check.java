package grafica;

import logica.GameLogic;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

public class Check extends AbstractAction {
    GamePiece[][] board;
    private GameLogic game;

    public Check(GamePiece[][] board, GameLogic game) {
        this.board = board;
        this.game = game;
    }

    private int endGame() {
        int aux = 0;
        int winGame = 16;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].isSelected()) {
                    aux++;
                }
            }
        }
        // São 16 casas, então se 16 forem selecionadas, o jogador ganhou.
        if (aux == winGame) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GamePiece key = (GamePiece) e.getSource();
        int keyNumber = key.getNumber();
        if (game.isWaiting()) {
            if (keyNumber == game.getPiece().getNumber()) {
                game.addPoints();
                game.setisWaiting(false);
                key.showPiece();
                if (endGame() == 1) {
                    JOptionPane.showMessageDialog(null, String.format("Você ganhou! Pontuação final %d", game.getPoints()),
                            "jogo da memória", JOptionPane.INFORMATION_MESSAGE);
                    ;
                }
                key.setEnabled(false);
                game.getPiece().setEnabled(false);
            } else {
                game.reducePoints();
                game.setisWaiting(false);
                key.hidePiece();
                game.getPiece().hidePiece();
            }
        } else {
            game.setPiece(key);
            game.setisWaiting(true);
            key.showPiece();
        }

    }

}
