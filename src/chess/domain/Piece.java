package chess.domain;

import chess.board.Square;

/**
 * @author akashMaurya
 * @Date 16/09/17.
 */
public class Piece {

    private boolean dead = false;
    private final Color color;
    private Square square;

    public Piece(boolean dead, Color color, Square square) {
        this.dead = dead;
        this.color = color;
        this.square = square;
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Color getColor() {
        return color;
    }
}
