package chess.board;

import chess.domain.*;

import java.awt.*;

/**
 * @author akashMaurya
 * @Date 16/09/17.
 */
public class BoardUtils {

    private static final Integer MIN = 0;
    private static final Integer MAX = 7;

    private static Board BOARD;

    public static boolean isValidSquare(int x, int y) {
        return  (x <= MAX && x >= MIN) && (y <= MAX && y >= MIN);
    }

    public static Board getInitialBoard(){
        if (BOARD == null) {
            Board board = new Board(initializeSquares());
            createKings(board);
            createQueens(board);
            createRooks(board);
            createBishops(board);
            createKnights(board);
            createPawns(board);
            BOARD = board;
        }
        return BOARD;
    }

    public static Board getBoard(){
        return BOARD;
    }

    public static Square getLeftCastlingSquare(Color color) {
        if (Color.WHITE == color) {
            return BOARD.getSquare(0, 6);
        }
        return BOARD.getSquare(7, 6);
    }

    public static Square getRightCastlingSquare(Color color) {
        if (Color.WHITE == color) {
            return BOARD.getSquare(0, 2);
        }
        return BOARD.getSquare(7, 2);
    }

    public static Square getRelativeSquare(Square currentSquare, int x, int y) {
        int newRow = currentSquare.getRow()+x;
        int newCol = currentSquare.getCol()+y;
        return BOARD.getSquare(newRow, newCol);
    }

    public static Square getNextSquareToMove(Square currentSquare, int x, int y) {
        Square newSquare = getRelativeSquare(currentSquare, x, y);
        if (newSquare == null) {
            return null;
        }
        final Piece piece = newSquare.getPiece();
        if (piece == null) {
            return newSquare;
        }
        if (piece.getColor() != currentSquare.getPiece().getColor()) {
            return newSquare;
        }
        return null;
    }

    public static void resetSquareColors() {
        for (int i= MIN; i<= MAX; i++) {
            for (int j= MIN; j <= MAX; j++) {
                BOARD.getSquare(i, j).setBackground((i+j)%2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
            }
        }
    }

    public static Color reverse(Color color) {
        return color == Color.black ? Color.WHITE : Color.BLACK;
    }

    private static Square[][] initializeSquares() {
        Square[][] squares = new Square[MAX+2][MAX+1];
        for (int i= MIN; i<= MAX; i++) {
            for (int j= MIN; j <= MAX; j++) {
                squares[i][j] = new Square(i, j, (i+j)%2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
            }
        }

        for (int j= MIN; j<= MAX; j++) {
            squares[MAX+1][j] = new Square(MAX+1, j, Color.YELLOW);
        }
        return squares;
    }

    private static void createKings(Board board) {
        King whiteKing = new King(Color.WHITE, board.getSquare(0, 4));
        King blackKing = new King(Color.BLACK, board.getSquare(7, 4));
        board.getGame().addPiece(whiteKing);
        board.getGame().addPiece(blackKing);
    }

    private static void createQueens(Board board) {
        Queen whiteQueen = new Queen(Color.WHITE, board.getSquare(0, 3));
        Queen blackQueen = new Queen(Color.BLACK, board.getSquare(7, 3));
        board.getGame().addPiece(whiteQueen);
        board.getGame().addPiece(blackQueen);
    }

    private static void createRooks(Board board) {
        Rook rightWhiteRook = new Rook(Color.WHITE, board.getSquare(0, 7));
        Rook leftWhiteRook = new Rook(Color.WHITE, board.getSquare(0, 0));
        board.getGame().addPiece(rightWhiteRook);
        board.getGame().addPiece(leftWhiteRook);

        Rook rightBlackRook = new Rook(Color.BLACK, board.getSquare(7, 7));
        Rook leftBlackRook = new Rook(Color.BLACK, board.getSquare(7, 0));
        board.getGame().addPiece(rightBlackRook);
        board.getGame().addPiece(leftBlackRook);
    }

    private static void createKnights(Board board) {
        Knight rightWhiteKnight = new Knight(Color.WHITE, board.getSquare(0, 1));
        Knight leftWhiteKnight = new Knight(Color.WHITE, board.getSquare(0, 6));
        board.getGame().addPiece(rightWhiteKnight);
        board.getGame().addPiece(leftWhiteKnight);

        Knight rightBlackKnight = new Knight(Color.BLACK, board.getSquare(7, 1));
        Knight leftBlackKnight = new Knight(Color.BLACK, board.getSquare(7, 6));
        board.getGame().addPiece(rightBlackKnight);
        board.getGame().addPiece(leftBlackKnight);
    }

    private static void createBishops(Board board) {
        Bishop rightWhiteBishop = new Bishop(Color.WHITE, board.getSquare(0, 2));
        Bishop leftWhiteBishop = new Bishop(Color.WHITE, board.getSquare(0, 5));
        board.getGame().addPiece(rightWhiteBishop);
        board.getGame().addPiece(leftWhiteBishop);

        Bishop rightBlackBishop = new Bishop(Color.BLACK, board.getSquare(7, 2));
        Bishop leftBlackBishop = new Bishop(Color.BLACK, board.getSquare(7, 5));
        board.getGame().addPiece(rightBlackBishop);
        board.getGame().addPiece(leftBlackBishop);
    }

    private static void createPawns(Board board) {
        for (int i= MIN; i <= MAX; i++) {
            Pawn pawn = new Pawn(Color.WHITE, board.getSquare(1, i));
            board.getGame().addPiece(pawn);
        }

        for (int i= MIN; i <= MAX; i++) {
            Pawn pawn = new Pawn(Color.BLACK, board.getSquare(6, i));
            board.getGame().addPiece(pawn);
        }
    }
}
