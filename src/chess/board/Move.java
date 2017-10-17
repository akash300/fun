package chess.board;

import chess.domain.Piece;

/**
 * @author akashMaurya
 * @Date 14/10/17.
 */
public class Move implements Cloneable{

    private Square firstSquare;
    private Square secondSquare;

    private Piece firstPiece;
    private boolean firstPieceMoved;
    private Piece secondPiece;
    private boolean secondPieceMoved = false;

    private boolean isSecondPieceKilled;
    private boolean isCastlingMove;

    public Square getFirstSquare() {
        return firstSquare;
    }

    public void setFirstSquare(Square firstSquare) {
        this.firstSquare = firstSquare;
    }

    public Square getSecondSquare() {
        return secondSquare;
    }

    public void setSecondSquare(Square secondSquare) {
        this.secondSquare = secondSquare;
    }

    public Piece getFirstPiece() {
        return firstPiece;
    }

    public void setFirstPiece(Piece firstPiece) {
        this.firstPiece = firstPiece;
    }

    public Piece getSecondPiece() {
        return secondPiece;
    }

    public void setSecondPiece(Piece secondPiece) {
        this.secondPiece = secondPiece;
    }

    public boolean isSecondPieceKilled() {
        return isSecondPieceKilled;
    }

    public void setSecondPieceKilled(boolean secondPieceKilled) {
        isSecondPieceKilled = secondPieceKilled;
    }

    public boolean isCastlingMove() {
        return isCastlingMove;
    }

    public void setCastlingMove(boolean castlingMove) {
        isCastlingMove = castlingMove;
    }

    public boolean isFirstPieceMoved() {
        return firstPieceMoved;
    }

    public void setFirstPieceMoved(boolean firstPieceMoved) {
        this.firstPieceMoved = firstPieceMoved;
    }

    public boolean isSecondPieceMoved() {
        return secondPieceMoved;
    }

    public void setSecondPieceMoved(boolean secondPieceMoved) {
        this.secondPieceMoved = secondPieceMoved;
    }

    @Override
    public Move clone() {
        try {
            return (Move) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }

    }
}
