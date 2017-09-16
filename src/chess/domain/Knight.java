package chess.domain;

import chess.board.Square;

/**
 * @author akashMaurya
 * @Date 16/09/17.
 */
public class Knight extends Piece {

    public Knight(Color color, Square square) {
        super(color, square);
    }

    @Override
    public String toString() {
        return this.getColor() == Color.WHITE ? "\u2658 " : "\u265E ";
    }
}
