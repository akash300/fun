package chess.board;

import chess.domain.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

/**
 * @author akashMaurya
 * @Date 16/09/17.
 */
public class Square extends JButton{

    private final Integer row;
    private final Integer col;
    private final Color color;
    private Piece piece;

    public Square(int row, int col, Color color) {
        this.row = row;
        this.col = col;
        this.color = color;
        this.setOpaque(true);
        this.setBackground(color);
        this.setBorderPainted(false);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final boolean showPossibleMoves = Move.move((Square) e.getSource());
                BoardUtils.resetSquareColors();
                if (showPossibleMoves) {
                    final Set<Square> possibleSquares = MoveCalculator.getPossibleSquares((Square) e.getSource());
                    if (AppUtils.isNotEmpty(possibleSquares)) {
                        for (Square possibleSquare : possibleSquares) {
                            possibleSquare.setBackground(Color.orange);
                        }
                    }
                }
            }
        });
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        if (piece != null) {
            this.setIcon(new ImageIcon(piece.getImageIcon()));
        } else {
            this.setIcon(null);
        }
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Square square = (Square) o;

        if (row != null ? !row.equals(square.row) : square.row != null) return false;
        if (col != null ? !col.equals(square.col) : square.col != null) return false;
        return color != null ? color.equals(square.color) : square.color == null;

    }

    @Override
    public int hashCode() {
        int result = row != null ? row.hashCode() : 0;
        result = 31 * result + (col != null ? col.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
