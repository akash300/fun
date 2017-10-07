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
                if (MoveCalculator.getCastlingSquares(sourceSquare, sourceSquare.getPiece()).contains(destinationSquare)) {
                    if (destinationSquare == BoardUtils.getLeftCastlingSquare(sourceSquare.getPiece().getColor())) {
                        Square rookSquare = BoardUtils.getRelativeSquare(destinationSquare, 0, 1);
                        Square bishophSquare = BoardUtils.getRelativeSquare(destinationSquare, 0, -1);
                        bishophSquare.setPiece(rookSquare.getPiece());
                        bishophSquare.getPiece().setHasMoved(true);
                        rookSquare.setPiece(null);
                    } else if (destinationSquare == BoardUtils.getRightCastlingSquare(sourceSquare.getPiece().getColor())) {
                        Square rookSquare = BoardUtils.getRelativeSquare(destinationSquare, 0, -2);
                        Square queenSquare = BoardUtils.getRelativeSquare(destinationSquare, 0, 1);
                        queenSquare.setPiece(rookSquare.getPiece());
                        queenSquare.getPiece().setHasMoved(true);
                        rookSquare.setPiece(null);
                    }
                }

                destinationSquare.setPiece(sourceSquare.getPiece());
                destinationSquare.getPiece().setHasMoved(true);
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
