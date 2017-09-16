package chess.board;

import chess.domain.Piece;

/**
 * @author akashMaurya
 * @Date 16/09/17.
 */
public class Square {

    private int row;
    private int col;
    private boolean isOccupied;
    private Piece piece;

    public Square(int row, int col, boolean isOccupied) {
        this.row = row;
        this.col = col;
        this.isOccupied = isOccupied;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Piece getPiece() {
        return isOccupied() ? piece : null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
