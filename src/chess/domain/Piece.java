package chess.domain;

import chess.board.Square;

import java.awt.*;

/**
 * @author akashMaurya
 * @Date 16/09/17.
 */
public abstract class Piece{

    private boolean dead = false;
    private final Color color;
    private Square square;
    private final Image imageIcon;

    public Piece(Color color, Square square) {
        this.color = color;
        this.square = square;
        this.imageIcon = getImage();
        square.setPiece(this);
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

    public Image getImageIcon() {
        return imageIcon;
    }

    protected abstract Image getImage();

    public abstract String getLabel();

    public boolean multiSteps() {
        return true;
    }

    public boolean straight() {
        return true;
    }

    public boolean diagonal() {
        return true;
    }

    public boolean twoAndHalf() {
        return false;
    }

    public boolean castle(){
        return false;
    }

    public boolean reverse(){
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (dead != piece.dead) return false;
        if (color != null ? !color.equals(piece.color) : piece.color != null) return false;
        if (square != null ? !square.equals(piece.square) : piece.square != null) return false;
        return imageIcon != null ? imageIcon.equals(piece.imageIcon) : piece.imageIcon == null;

    }

    @Override
    public int hashCode() {
        int result = (dead ? 1 : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (square != null ? square.hashCode() : 0);
        result = 31 * result + (imageIcon != null ? imageIcon.hashCode() : 0);
        return result;
    }
}
