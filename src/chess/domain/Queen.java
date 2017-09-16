package chess.domain;

import chess.board.Square;

/**
 * @author akashMaurya
 * @Date 16/09/17.
 */
public class Queen extends Piece {

    public Queen(Color color, Square square) {
        super(color, square);
    }

    @Override
    public String toString() {
        return this.getColor() == Color.WHITE ? "\u2655 " : "\u265B ";
    }
}
