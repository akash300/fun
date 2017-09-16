package chess.domain;

import chess.board.Square;

/**
 * @author akashMaurya
 * @Date 16/09/17.
 */
public class Rook extends Piece {

    public Rook(Color color, Square square) {
        super(color, square);
    }

    @Override
    public String toString() {
        return this.getColor() == Color.WHITE ? "\u2656 " : "\u265C ";
    }
}
