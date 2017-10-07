package chess.board;

import chess.domain.Piece;
import chess.domain.PieceType;

import java.awt.*;
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

            if (piece.reverse() || piece.getColor() == Color.WHITE) {
                for (int i = 1; i <= size; i++) {
                    final Square nextSquare = BoardUtils.getNextSquareToMove(source, i, 0);
                    if (addOrBreak(source, squares, nextSquare)) break;
                }
            }

            if (piece.reverse() || piece.getColor() == Color.BLACK) {
                for (int i = 1; i <= size; i++) {
                    final Square nextSquare = BoardUtils.getNextSquareToMove(source, -i, 0);
                    if (addOrBreak(source, squares, nextSquare)) break;
                }
            }

            if (piece.getPieceType() != PieceType.PAWN) {

                for (int i = 1; i <= size; i++) {
                    final Square nextSquare = BoardUtils.getNextSquareToMove(source, 0, i);
                    if (addOrBreak(source, squares, nextSquare)) break;
                }

                for (int i = 1; i <= size; i++) {
                    final Square nextSquare = BoardUtils.getNextSquareToMove(source, 0, -i);
                    if (addOrBreak(source, squares, nextSquare)) break;
                }
            }
        }

        if (piece.diagonal()) {
            for (int i=1; i<=size; i++){
                final Square nextSquare = BoardUtils.getNextSquareToMove(source, i, i);
                if (addOrBreak(source, squares, nextSquare)) break;
            }

            for (int i=1; i<=size; i++){
                final Square nextSquare = BoardUtils.getNextSquareToMove(source, i, -i);
                if (addOrBreak(source, squares, nextSquare)) break;
            }

            for (int i=1; i<=size; i++){
                final Square nextSquare = BoardUtils.getNextSquareToMove(source, -i, i);
                if (addOrBreak(source, squares, nextSquare)) break;
            }

            for (int i=1; i<=size; i++){
                final Square nextSquare = BoardUtils.getNextSquareToMove(source, -i, -i);
                if (addOrBreak(source, squares, nextSquare)) break;
            }
        }

        if (piece.twoAndHalf()) {
            addNullSafe(BoardUtils.getNextSquareToMove(source, 1, 2), squares);
            addNullSafe(BoardUtils.getNextSquareToMove(source, 1, -2), squares);
            addNullSafe(BoardUtils.getNextSquareToMove(source, -1, 2), squares);
            addNullSafe(BoardUtils.getNextSquareToMove(source, -1, -2), squares);
            addNullSafe(BoardUtils.getNextSquareToMove(source, 2, 1), squares);
            addNullSafe(BoardUtils.getNextSquareToMove(source, 2, -1), squares);
            addNullSafe(BoardUtils.getNextSquareToMove(source, -2, 1), squares);
            addNullSafe(BoardUtils.getNextSquareToMove(source, -2, -1), squares);
        }

        squares.addAll(getCustomMoves(source, piece));

        return squares;
    }

    public static Set<Square> getCastlingSquares(Square sourceSquare, Piece piece) {

        Set<Square> castlingMoves = new HashSet<>();
        if (!piece.hasMoved()) {
            final Square lBSquare = BoardUtils.getRelativeSquare(sourceSquare, 0, 1);
            final Square lKSquare = BoardUtils.getRelativeSquare(sourceSquare, 0, 2);
            final Square lRSquare = BoardUtils.getRelativeSquare(sourceSquare, 0, 3);
            if (lBSquare != null && lKSquare != null && lBSquare.getPiece() == null && lKSquare.getPiece() == null
                    && lRSquare != null && lRSquare.getPiece() != null && !lRSquare.getPiece().hasMoved()) {
                castlingMoves.add(lKSquare);
            }

            final Square rQSqaure = BoardUtils.getRelativeSquare(sourceSquare, 0, -1);
            final Square rBSqaure = BoardUtils.getRelativeSquare(sourceSquare, 0, -2);
            final Square rKSquare = BoardUtils.getRelativeSquare(sourceSquare, 0, -3);
            final Square rRSquare = BoardUtils.getRelativeSquare(sourceSquare, 0, -4);
            if (rQSqaure != null && rBSqaure != null && rKSquare!= null && rQSqaure.getPiece() == null
                    && rBSqaure.getPiece() == null && rKSquare.getPiece() == null && rRSquare != null
                    && rRSquare.getPiece() != null && !rRSquare.getPiece().hasMoved()) {
                castlingMoves.add(rBSqaure);
            }
        }
        return castlingMoves;
    }

    private static boolean addOrBreak(Square source, Set<Square> squares, Square nextSquare) {
        if (nextSquare == null) {
            return true;
        }
        if (!isAttackMove(source, nextSquare) || PieceType.PAWN != source.getPiece().getPieceType()) {
            squares.add(nextSquare);
        }
        if (isAttackMove(source, nextSquare)) {
            return true;
        }
        return false;
    }

    private static void addNullSafe(Square square, Set<Square> squares) {
        if (square == null) {
            return;
        }
        squares.add(square);
    }

    private static Set<Square> getCustomMoves(Square sourceSquare, Piece piece) {

        Set<Square> customMoves = new HashSet<>();
        if (piece == null) {
            return customMoves;
        }
        switch (piece.getPieceType()) {
            case PAWN:
                final Square left = BoardUtils.getNextSquareToMove(sourceSquare, piece.getDirection(), 1);
                if (left != null && isAttackMove(sourceSquare, left)){
                    customMoves.add(left);
                }
                final Square right = BoardUtils.getNextSquareToMove(sourceSquare, piece.getDirection(), -1);
                if (right != null && isAttackMove(sourceSquare, right)){
                    customMoves.add(right);
                }

                if (!piece.hasMoved()) {
                    final Square twice = BoardUtils.getNextSquareToMove(sourceSquare, piece.getDirection() * 2, 0);
                    if (twice != null && !isAttackMove(sourceSquare, twice)){
                        customMoves.add(twice);
                    }
                }
                break;
            case KING:
                Set<Square> castlingMoves = getCastlingSquares(sourceSquare, piece);

                if (AppUtils.isNotEmpty(castlingMoves)) {
                    customMoves.addAll(castlingMoves);
                }
        }
        return customMoves;
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
