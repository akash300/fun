package chess.board;

import java.awt.*;
import java.util.Set;

/**
 * @author akashMaurya
 * @Date 07/10/17.
 */

public class Move {

    private static final Move INSTANCE = new Move();

    private Move() {
    }

    private static Square sourceSquare;
    private static Square destinationSquare;

    public Move getMove(Square square) {
        if (sourceSquare == null) {
            sourceSquare = square;
            return null;
        }
        destinationSquare = square;
        return INSTANCE;
    }

    public static boolean move(Square square) {
        if (sourceSquare == null) {
            if (square.getPiece() == null) {
                return true;
            }
            sourceSquare = square;
            square.setBackground(Color.YELLOW);
            return true;
        }

        if (square != sourceSquare) {
            final Set<Square> possibleSquares = MoveCalculator.getPossibleSquares(sourceSquare);
            if (possibleSquares.contains(square)) {
                destinationSquare = square;
                destinationSquare.setPiece(sourceSquare.getPiece());
                sourceSquare.setPiece(null);
            }
        }
        reset();
        return false;
    }

    public static void reset() {
        sourceSquare.setBackground(sourceSquare.getColor());
        sourceSquare = null;
        destinationSquare = null;
    }

}
