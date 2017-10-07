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
public class Pawn extends Piece {

    public Pawn(Color color, Square square) {
        super(color, square);
    }

    @Override
    public String toString() {
        return this.getColor() == Color.WHITE ? "\u2659 " : "\u265F ";
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }

    protected Image getImage() {
        try {
            if (this.getColor() == Color.WHITE){
                return ImageIO.read(new File("/Users/knightSky1/fun/web/img/chesspieces/wikipedia/wP.png"));
            }
            return ImageIO.read(new File("/Users/knightSky1/fun/web/img/chesspieces/wikipedia/bP.png"));
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public boolean multiSteps() {
        return false;
    }

    @Override
    public boolean diagonal() {
        return false;
    }

    @Override
    public boolean reverse() {
        return false;
    }
}
