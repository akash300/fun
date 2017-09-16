package chess.domain;

import chess.board.Square;

/**
 * @author akashMaurya
 * @Date 16/09/17.
 */
public class Bishop extends Piece {

    public Bishop(Color color, Square square) {
        super(color, square);
    }

    @Override
    public String toString() {
        return this.getColor() == Color.WHITE ? "\u2657 " : "\u265D ";
    }
}
