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
public class Rook extends Piece {

    public Rook(Color color, Square square) {
        super(color, square);
    }

    @Override
    public String toString() {
        return this.getColor() == Color.WHITE ? "\u2656 " : "\u265C ";
    }

    @Override
    public String getLabel() {
        return "ROOK";
    }

    protected Image getImage() {
        try {
            if (this.getColor() == Color.WHITE){
                return ImageIO.read(new File("/Users/knightSky1/fun/web/img/chesspieces/wikipedia/wR.png"));
            }
            return ImageIO.read(new File("/Users/knightSky1/fun/web/img/chesspieces/wikipedia/bR.png"));
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public boolean diagonal() {
        return false;
    }
}
