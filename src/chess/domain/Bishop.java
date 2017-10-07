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
public class Bishop extends Piece{

    public Bishop(Color color, Square square) {
        super(color, square);
    }

    @Override
    public String toString() {
        return this.getColor() == Color.WHITE ? "\u2657 " : "\u265D ";
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.BISHOPH;
    }

    protected Image getImage() {
        try {
            if (this.getColor() == Color.WHITE){
                return ImageIO.read(new File("/Users/knightSky1/fun/web/img/chesspieces/wikipedia/wB.png"));
            }
            return ImageIO.read(new File("/Users/knightSky1/fun/web/img/chesspieces/wikipedia/bB.png"));
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public boolean straight() {
        return false;
    }
}
