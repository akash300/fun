package chess;

import chess.board.*;
import chess.domain.Piece;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

/**
 * @author akashMaurya
 * @Date 09/10/17.
 */

public class MoveListener implements ActionListener{

    private Board board;

    private MoveListener() {
    }

    private static final MoveListener moveListener = new MoveListener();

    public static MoveListener getMoveListener(){
        return moveListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Square source = (Square) e.getSource();
        if (source.getPiece() != null) {
            final Piece piece = source.getPiece();
            if (piece.getColor() != getBoard().getGame().getTurn() && Move.getMoveState() != MoveState.PARTIAL) {
                return;
            }
        }
        final MoveState moveState = Move.move(source);
        BoardUtils.resetSquareColors();
        final boolean checkState = getBoard().getGame().isCheckState();
        if (checkState) {
            final Square kingSquare = board.getGame().getKingSquare();
            kingSquare.setBackground(Color.RED);
        }
        if (moveState == MoveState.PARTIAL) {
            final Set<Square> possibleSquares = MoveCalculator.getPossibleSquares(source);
            if (AppUtils.isNotEmpty(possibleSquares)) {
                for (Square possibleSquare : possibleSquares) {
                    possibleSquare.setBackground(Color.pink);
                }
            }
        } else if (moveState == MoveState.COMPLETE){
            //Move is complete
            board.getGame().changeTurn();
        }
    }

    private Board getBoard() {
        if (board == null) {
            board = BoardUtils.getInitialBoard();
        }
        return board;
    }
}
