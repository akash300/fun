package chess.board;

/**
 * @author akashMaurya
 * @Date 16/09/17.
 */
public class ChessMain {

    public static void main(String[] args) {
        final Board initialBoard = BoardUtils.getInitialBoard();
        initialBoard.printBoard();
    }
}
