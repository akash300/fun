package chess.ai;

import chess.MoveListener;
import chess.board.*;
import chess.domain.Piece;
import chess.domain.PieceType;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author akashMaurya
 * @Date 14/10/17.
 */
public class BestMoveCalcultor {

    private static final MoveListener moveListener = MoveListener.getMoveListener();
    public static final Integer MAX_DEPTH = 4;

    public static int play(Board board, Color color, int depth) {

        if (depth == 1) {
            return board.getGame().gameValue();
        }

        final Map<PieceType, List<Piece>> piecesMap = color == Color.WHITE ? board.getGame().getWhitePiecesMap() :
                board.getGame().getBlackPiecesMap();
        int score = color == Color.WHITE ? -1000 : 1000;
        Move bestMove = null;

        GameMove.setAutoMation(true);
        Long startTime = System.currentTimeMillis();
        for (Map.Entry<PieceType, List<Piece>> entry : piecesMap.entrySet()) {

            final List<Piece> pieces = entry.getValue();
            for (Piece piece : pieces) {
                if (piece.isDead()) {
                    continue;
                }
                Move move = new Move();
                move.setFirstSquare(piece.getSquare());
                move.setFirstPiece(piece.getSquare().getPiece());
                move.setFirstPieceMoved(piece.hasMoved());
                Set<Square> possibleSquares = MoveCalculator.getPossibleSquares(piece.getSquare());
                for (Square possibleSquare : possibleSquares) {
                    move.setSecondSquare(possibleSquare);
                    move.setSecondPiece(possibleSquare.getPiece());
                    if (possibleSquare.getPiece() != null) {
                        move.setSecondPieceMoved(possibleSquare.getPiece().hasMoved());
                    }
                    MoveState moveState = MoveUtils.move(board.getGame(), move);
                    //board.printBoard();
                    if (moveState == MoveState.COMPLETE) {
                        int currentGameValue = play(board, BoardUtils.reverse(color), depth-1);
                        if (Color.WHITE == color) {
                            if (currentGameValue > score) {
                                score = currentGameValue;
                                bestMove = move.clone();
                            }
                        } else {
                            if (currentGameValue < score) {
                                score = currentGameValue;
                                bestMove = move.clone();
                            }
                        }

                    }
                    MoveUtils.undo(board.getGame(), move);
                    //board.printBoard();
                }
            }
        }

        if (depth != MAX_DEPTH) {
            return score;
        }

        GameMove.setAutoMation(false);
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        if (bestMove != null) {
            moveListener.performMove(bestMove.getFirstSquare());
            moveListener.performMove(bestMove.getSecondSquare());
        }

        return score;
    }
}
