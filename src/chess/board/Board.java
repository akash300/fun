package chess.board;

import javax.swing.*;
import java.awt.*;

/**
 * @author akashMaurya
 * @Date 16/09/17.
 */
public class Board extends JFrame{

    private Square[][] squares;
    private final Game game;

    public Board(Square[][] squares) {
        this.squares = squares;
        this.setLayout(new GridLayout(9,8));
        for (int i=0 ;i <9; i++) {
            for (int j=0; j<8; j++) {
                this.add(squares[i][j]);
            }
        }
        this.setSize(900,900);
        this.setVisible(true);
        this.game = new Game();
    }

    public Square getSquare(int x, int y) {
        if (BoardUtils.isValidSquare(x, y)) {
            return squares[x][y];
        }
        return null;
    }

    public Square getScoreSquare() {
        return squares[8][0];
    }

    public Game getGame() {
        return game;
    }
}
