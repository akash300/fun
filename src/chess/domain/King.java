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
public class King extends Piece {

    public King(Color color, Square square) {
        super(color, square);
    }

    @Override
    public String toString() {
        return this.getColor() == Color.WHITE ? "\u2654 " : "\u265A ";
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }

    protected Image getImage() {
        try {
            if (this.getColor() == Color.WHITE){
                return ImageIO.read(new File("/Users/knightSky1/fun/web/img/chesspieces/wikipedia/wK.png"));
            }
            return ImageIO.read(new File("/Users/knightSky1/fun/web/img/chesspieces/wikipedia/bK.png"));
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public boolean multiSteps() {
        return false;
    }

    @Override
    public boolean castle() {
        return false;
    }
}
