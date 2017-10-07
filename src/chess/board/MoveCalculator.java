package chess.board;

import chess.domain.Piece;

import java.util.HashSet;
import java.util.Set;

/**
 * @author akashMaurya
 * @Date 07/10/17.
 */
public class MoveCalculator {

    public static Set<Square> getPossibleSquares(Square source) {
        Piece piece = source.getPiece();
        Set<Square> squares = new HashSet<>();
        int size = getSize(piece);
        if (piece.straight()) {
            for (int i=1; i<=size; i++){
                final Square nextSquare = BoardUtils.getNextSquare(source, i, 0);
                if (nextSquare == null) {
                    break;
                }
                squares.add(nextSquare);
                if (isAttackMove(source, nextSquare)) {
                    break;
                }
            }

            for (int i=1; i<=size; i++){
                final Square nextSquare = BoardUtils.getNextSquare(source, -i, 0);
                if (nextSquare == null) {
                    break;
                }
                squares.add(nextSquare);
                if (isAttackMove(source, nextSquare)) {
                    break;
                }
            }

            for (int i=1; i<=size; i++){
                final Square nextSquare = BoardUtils.getNextSquare(source, 0, i);
                if (nextSquare == null) {
                    break;
                }
                squares.add(nextSquare);
                if (isAttackMove(source, nextSquare)) {
                    break;
                }
            }

            for (int i=1; i<=size; i++){
                final Square nextSquare = BoardUtils.getNextSquare(source, 0, -i);
                if (nextSquare == null) {
                    break;
                }
                squares.add(nextSquare);
                if (isAttackMove(source, nextSquare)) {
                    break;
                }
            }
        }

        if (piece.diagonal()) {
            for (int i=1; i<=size; i++){
                final Square nextSquare = BoardUtils.getNextSquare(source, i, i);
                if (nextSquare == null) {
                    break;
                }
                squares.add(nextSquare);
                if (isAttackMove(source, nextSquare)) {
                    break;
                }
            }

            for (int i=1; i<=size; i++){
                final Square nextSquare = BoardUtils.getNextSquare(source, i, -i);
                if (nextSquare == null) {
                    break;
                }
                squares.add(nextSquare);
                if (isAttackMove(source, nextSquare)) {
                    break;
                }
            }

            for (int i=1; i<=size; i++){
                final Square nextSquare = BoardUtils.getNextSquare(source, -i, i);
                if (nextSquare == null) {
                    break;
                }
                squares.add(nextSquare);
                if (isAttackMove(source, nextSquare)) {
                    break;
                }
            }

            for (int i=1; i<=size; i++){
                final Square nextSquare = BoardUtils.getNextSquare(source, -i, -i);
                if (nextSquare == null) {
                    break;
                }
                squares.add(nextSquare);
                if (isAttackMove(source, nextSquare)) {
                    break;
                }
            }
        }

        return squares;
    }

    private static int getSize(Piece piece) {
        if (piece.multiSteps()) {
            return 8;
        }
        return 1;
    }

    private static boolean isAttackMove(Square source, Square destination) {
        return destination.getPiece() != null && destination.getPiece().getColor() != source.getPiece().getColor();
    }
}
