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

    private Map<PieceType, List<Piece>> whitePiecesMap = new HashMap<>();
    private Map<PieceType, List<Piece>> blackPiecesMap = new HashMap<>();
    private Color turn = Color.WHITE;

    public Game() {
    }

    public void addPiece(Piece piece) {
        if (piece == null || piece.getColor() == null) {
            return;
        }
        if (Color.WHITE == piece.getColor()) {
            addPieceToMap(whitePiecesMap, piece);
        } else {
            addPieceToMap(blackPiecesMap, piece);
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

    public void changeTurn() {
        if (turn == Color.WHITE) {
            turn = Color.BLACK;
        } else {
            turn = Color.WHITE;
        }
    }

    public void addPieceToMap(Map<PieceType, List<Piece>> map, Piece piece) {
        if (map == null) {
            map = new HashMap<>(32);
        }
        List<Piece> pieces = map.get(piece.getPieceType());
        if (pieces == null) {
            pieces = new ArrayList<>();
        }
        pieces.add(piece);
        map.put(piece.getPieceType(), pieces);
    }

    public boolean isCheckState(Color color) {
        final Collection<List<Piece>> oppositePieces = color == Color.BLACK ? whitePiecesMap.values() : blackPiecesMap.values();

        final Square kingSquare = getKingSquare(color);
        for (List<Piece> pieceList : oppositePieces) {
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

    public boolean isCheckMate(Color color, Square kingSquare) {

        Set<Square> destinationSquares = MoveCalculator.getPossibleSquares(kingSquare);
        for (Square destinationSquare : destinationSquares) {
            Piece piece = destinationSquare.getPiece();
            destinationSquare.setPiece(kingSquare.getPiece());
            kingSquare.setPiece(null);
            final boolean checkState = isCheckState(color);

            //Revert the state
            kingSquare.setPiece(destinationSquare.getPiece());
            destinationSquare.setPiece(piece);

            if (!checkState) {
                return false;
            }
        }

        return true;
    }

    public Square getKingSquare(Color color) {
        return color == Color.BLACK ? blackPiecesMap.get(PieceType.KING).iterator().next().getSquare() :
                whitePiecesMap.get(PieceType.KING).iterator().next().getSquare();
    }

    public int gameValue() {
        return gameValueForMap(whitePiecesMap) - gameValueForMap(blackPiecesMap);
    }

    private int gameValueForMap(Map<PieceType, List<Piece>> map) {
        int value = 0;
        for (Map.Entry<PieceType, List<Piece>> entry : map.entrySet()) {
            int pieceValue = entry.getKey().getValue();
            final List<Piece> pieces = entry.getValue();
            for (Piece piece : pieces) {
                if (!piece.isDead()) {
                    value = value + pieceValue;
                }
            }
        }
        return value;
    }
}
