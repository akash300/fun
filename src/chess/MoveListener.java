package chess;

import chess.ai.BestMoveCalcultor;
import chess.board.*;
import chess.domain.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

/**
 * @author akashMaurya
 * @Date 09/10/17.
 */

public class MoveListener implements ActionListener {

    private Board board;

    private MoveListener() {
    }

    private static final MoveListener moveListener = new MoveListener();

    public static MoveListener getMoveListener() {
        return moveListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Square source = (Square) e.getSource();
        performMove(source);
    }

    public void performMove(Square source) {
        if (source.getPiece() != null) {
            final Piece piece = source.getPiece();
            if (piece.getColor() != getBoard().getGame().getTurn() && GameMove.getMoveState() != MoveState.PARTIAL) {
                return;
            }
        }
        final MoveState moveState = GameMove.move(getBoard().getGame(), source);
        BoardUtils.resetSquareColors();
        final boolean checkStateBlack = getBoard().getGame().isCheckState(Color.BLACK);
        if (checkStateBlack) {
            final Square kingSquare = getBoard().getGame().getKingSquare(Color.BLACK);
            kingSquare.setBackground(Color.RED);
            kingSquare.getPiece().setHasMoved(true);
            if (getBoard().getGame().isCheckMate(Color.BLACK, kingSquare)) {
                JOptionPane.showMessageDialog(getBoard(), "Game over, White Won");
            }

        }

        final boolean checkStateWhite = getBoard().getGame().isCheckState(Color.WHITE);
        if (checkStateWhite) {
            final Square kingSquare = getBoard().getGame().getKingSquare(Color.WHITE);
            kingSquare.setBackground(Color.RED);
            kingSquare.getPiece().setHasMoved(true);
            if (getBoard().getGame().isCheckMate(Color.WHITE, kingSquare)) {
                JOptionPane.showMessageDialog(getBoard(), "Game over, Black Won");
            }
        }
        if (moveState == MoveState.PARTIAL) {
            final Set<Square> possibleSquares = MoveCalculator.getPossibleSquares(source);
            if (AppUtils.isNotEmpty(possibleSquares)) {
                for (Square possibleSquare : possibleSquares) {
                    possibleSquare.setBackground(Color.YELLOW);
                }
            }
        } else if (moveState == MoveState.COMPLETE) {
            //Move is complete
            getBoard().getScoreSquare().setText(String.valueOf(board.getGame().gameValue()));
            getBoard().getGame().changeTurn();

            getBoard().paint();
            if (getBoard().getGame().getTurn() == Color.WHITE) {
                BestMoveCalcultor.play(getBoard(), Color.WHITE, BestMoveCalcultor.MAX_DEPTH);
                getBoard().paint();
            }

        }


    }

    private Board getBoard() {
        if (board == null) {
            board = BoardUtils.getInitialBoard();
        }
        return board;
    }
}
