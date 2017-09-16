package chess.board;

/**
 * @author akashMaurya
 * @Date 16/09/17.
 */
public class Board {

    private Square[][] squares;

    public Board(Square[][] squares) {
        this.squares = squares;
    }

    public Square getSquare(int x, int y) {
        if (BoardUtils.isValidSquare(x, y)) {
            return squares[x][y];
        }
        return null;
    }

    public void printBoard() {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (squares[i][j].getPiece() == null) {
                    System.out.print("X ");
                } else {
                    System.out.print(squares[i][j].getPiece().toString());
                }
            }
            System.out.println("\n");
        }
    }


}
