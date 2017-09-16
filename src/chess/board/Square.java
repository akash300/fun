package chess.board;

import chess.domain.Color;
import chess.domain.Piece;

/**
 * @author akashMaurya
 * @Date 16/09/17.
 */
public class Square {

    private final Integer row;
    private final Integer col;
    private final boolean isOccupied;
    private final Color color;
    private Piece piece;

    public Square(int row, int col, boolean isOccupied, Color color) {
        this.row = row;
        this.col = col;
        this.isOccupied = isOccupied;
        this.color = color;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Color getColor() {
        return color;
    }
}
