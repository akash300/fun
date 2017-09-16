package chess.domain;

import chess.board.Square;

/**
 * @author akashMaurya
 * @Date 16/09/17.
 */
public class Pawn extends Piece {

    public Pawn(boolean dead, Color color, Square square) {
        super(dead, color, square);
    }
}
