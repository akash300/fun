package chess.domain;

import chess.board.Square;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author akashMaurya
 * @Date 16/09/17.
 */
public class Queen extends Piece {

    public Queen(Color color, Square square) {
        super(color, square);
    }

    @Override
    public String toString() {
        return this.getColor() == Color.WHITE ? "\u2655 " : "\u265B ";
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.QUEEN;
    }

    protected Image getImage() {
        try {
            if (this.getColor() == Color.WHITE){
                return ImageIO.read(new File("/Users/knightSky1/fun/web/img/chesspieces/wikipedia/wQ.png"));
            }
            return ImageIO.read(new File("/Users/knightSky1/fun/web/img/chesspieces/wikipedia/bQ.png"));
        } catch (IOException e) {
            return null;
        }
    }
}
