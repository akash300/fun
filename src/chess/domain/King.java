package chess.domain;

import chess.board.Square;

/**
 * @author akashMaurya
 * @Date 16/09/17.
 */
public class King extends Piece {

    public King(boolean dead, Color color, Square square) {
        super(dead, color, square);
    }
}
