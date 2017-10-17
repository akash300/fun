package chess.board;

import chess.domain.Piece;

import java.awt.*;
import java.util.Set;

/**
 * @author akashMaurya
 * @Date 07/10/17.
 */

public class GameMove {

    private static final GameMove INSTANCE = new GameMove();

    private GameMove() {
    }

    private static Square sourceSquare;
    private static Square destinationSquare;
    private static boolean isAutoMation = false;

    public static boolean isAutoMation() {
        return isAutoMation;
    }

    public static void setAutoMation(boolean isAutoMation) {
        GameMove.isAutoMation = isAutoMation;
    }

    public GameMove getMove(Square square) {
        if (sourceSquare == null) {
            sourceSquare = square;
            return null;
        }
        destinationSquare = square;
        return INSTANCE;
    }

    public static MoveState getMoveState() {
        return sourceSquare != null ? MoveState.PARTIAL : MoveState.COMPLETE;
    }

    public static void undoMove(Move move) {
        move.getFirstSquare().setPiece(move.getFirstPiece());
        move.getFirstPiece().setHasMoved(move.isFirstPieceMoved());
        move.getSecondSquare().setPiece(move.getSecondPiece());
        if (move.getSecondPiece() != null) {
            move.getSecondPiece().setDead(false);
            move.getSecondPiece().setHasMoved(move.isSecondPieceMoved());
        }
    }

    public static MoveState move(Game game, Square square) {
        if (sourceSquare == null) {
            if (square.getPiece() == null) {
                return MoveState.INCORRECT;
            }
            sourceSquare = square;
            if (GameMove.isAutoMation()) {
                square.setBackground(Color.YELLOW);
            }
            return MoveState.PARTIAL;
        }

        MoveState moveState = null;
        if (square != sourceSquare) {

            if (isValidMove(sourceSquare, square)) {
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

                MoveContext moveContext = new MoveContext(sourceSquare.getPiece(), destinationSquare.getPiece(),
                        sourceSquare.getPiece().hasMoved(), sourceSquare.getPiece().getColor());

                if (destinationSquare.getPiece() != null) {
                    destinationSquare.getPiece().setDead(true);
                }
                destinationSquare.setPiece(sourceSquare.getPiece());
                destinationSquare.getPiece().setHasMoved(true);
                sourceSquare.setPiece(null);
                Color reverse = BoardUtils.reverse(moveContext.getColor());
                if (game.isCheckState(moveContext.getColor())) {
                    sourceSquare.setPiece(moveContext.getSourcePiece());
                    sourceSquare.getPiece().setHasMoved(moveContext.getSourcePieceMoved());
                    destinationSquare.setPiece(moveContext.getDestinationPiece());
                    if (destinationSquare.getPiece() != null) {
                        destinationSquare.getPiece().setDead(false);
                    }
                    game.getKingSquare(reverse).setBackground(Color.RED);
                    moveState = MoveState.INCORRECT;
                } else {
                    moveState = MoveState.COMPLETE;
                }
            } else {
                moveState = MoveState.INCORRECT;
            }
        } else {
            moveState = MoveState.ABORTED;
        }
        reset();
        return moveState;
    }

    private static boolean isValidMove(Square sourceSquare, Square destinationSquare) {
        if (GameMove.isAutoMation()) {
            return true;
        }
        final Set<Square> possibleSquares = MoveCalculator.getPossibleSquares(sourceSquare);
        return possibleSquares.contains(destinationSquare);
    }

    public static void reset() {
        sourceSquare.setBackground(sourceSquare.getColor());
        sourceSquare = null;
        destinationSquare = null;
    }

    private static class MoveContext {

        private final Piece sourcePiece;
        private final Piece destinationPiece;
        private final boolean sourcePieceMoved;
        private final Color color;

        public MoveContext(Piece sourcePiece, Piece destinationPiece, Boolean sourcePieceMoved, Color color) {
            this.sourcePiece = sourcePiece;
            this.destinationPiece = destinationPiece;
            this.sourcePieceMoved = sourcePieceMoved;
            this.color = color;
        }

        public Piece getSourcePiece() {
            return sourcePiece;
        }

        public boolean getSourcePieceMoved() {
            return sourcePieceMoved;
        }

        public Piece getDestinationPiece() {
            return destinationPiece;
        }

        public Color getColor() {
            return color;
        }
    }

}
