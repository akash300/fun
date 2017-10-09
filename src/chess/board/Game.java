package chess.board;

import chess.domain.Piece;
import chess.domain.PieceType;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author akashMaurya
 * @Date 09/10/17.
 */

public class Game {

    private Map<PieceType, List<Piece>> whitePiecesMap;
    private Map<PieceType, List<Piece>> blackPiecesMap;
    private Color turn = Color.WHITE;
    private boolean isCheckState = false;

    public Game() {
    }

    public void addPiece(Piece piece) {
        if (piece == null || piece.getColor() == null) {
            return;
        }
        if (Color.WHITE == piece.getColor()) {
            if (whitePiecesMap == null) {
                whitePiecesMap = new HashMap<>(16);
            }
            List<Piece> pieces = whitePiecesMap.get(piece.getPieceType());
            if (pieces == null) {
                pieces = new ArrayList<>();
            }
            pieces.add(piece);
            whitePiecesMap.put(piece.getPieceType(), pieces);
        } else {
            if (blackPiecesMap == null) {
                blackPiecesMap = new HashMap<>(16);
            }
            List<Piece> pieces = blackPiecesMap.get(piece.getPieceType());
            if (pieces == null) {
                pieces = new ArrayList<>();
            }
            pieces.add(piece);
            blackPiecesMap.put(piece.getPieceType(), pieces);
        }
    }

    public Map<PieceType, List<Piece>> getWhitePiecesMap() {
        return whitePiecesMap;
    }

    public Map<PieceType, List<Piece>> getBlackPiecesMap() {
        return blackPiecesMap;
    }

    public Color getTurn() {
        return turn;
    }

    public void setTurn(Color turn) {
        this.turn = turn;
    }

    public void changeTurn() {
        if (turn == Color.WHITE) {
            turn = Color.BLACK;
        } else {
            turn = Color.WHITE;
        }
    }

    public boolean isCheckState() {
        final Collection<List<Piece>> availablePieces = turn == Color.WHITE ? whitePiecesMap.values() : blackPiecesMap.values();

        final Square kingSquare = getKingSquare();
        for (List<Piece> pieceList : availablePieces) {
            for (Piece piece : pieceList) {
                if (piece.isDead()) {
                    continue;
                }
                Set<Square> possibleSquares = MoveCalculator.getPossibleSquares(piece.getSquare());
                if (possibleSquares.contains(kingSquare)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Square getKingSquare() {
        return turn == Color.WHITE ? blackPiecesMap.get(PieceType.KING).iterator().next().getSquare() :
                whitePiecesMap.get(PieceType.KING).iterator().next().getSquare();
    }
}
