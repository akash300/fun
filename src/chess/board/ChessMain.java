package chess.board;

import chess.ai.BestMoveCalcultor;

import java.awt.*;

/**
 * @author akashMaurya
 * @Date 16/09/17.
 */
public class ChessMain {

    public static void main(String[] args) {
        final Board initialBoard = BoardUtils.getInitialBoard();
        BestMoveCalcultor.play(initialBoard, Color.WHITE, BestMoveCalcultor.MAX_DEPTH);
    }
}
