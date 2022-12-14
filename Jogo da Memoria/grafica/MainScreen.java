package grafica;

import java.awt.GridLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import logica.GameLogic;

public class MainScreen extends JFrame{
    GameLogic game; 
    GamePiece[][] board; 

    public MainScreen() {
        game = new GameLogic();
        board = new GamePiece[4][4];
        setLayout((new GridLayout(5, 4, 4, 5)));
        loadGame();
        setSize(new Dimension(300, 300));  
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void loadGame() {
        Check action = new Check(board, game);
     
        for (int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                int numeroSorteado = game.getGameBoard()[i][j];
                GamePiece btn = new GamePiece(numeroSorteado);
                board[i][j] = btn;
                add(btn);
                btn.addActionListener(action);
            }
        }
    }

    public void gameDisplay(int option){
        if (option == 1 ) {
            for(int i = 0; i < 4; i++){
                for (int j = 0; j < 4; j++){
                    board[i][j].showPiece();
                }
            }
        }else if(option == 2) {
            for(int i = 0; i < 4; i++){
                for (int j = 0; j < 4; j++){
                    board[i][j].hidePiece();
                }
            }
        }
        
    }

}
