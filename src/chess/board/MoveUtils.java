package chess.board;

/**
 * @author akashMaurya
 * @Date 14/10/17.
 */
public class MoveUtils {

    public static MoveState move(Game game, Move move) {
        final MoveState partial = GameMove.move(game, move.getFirstSquare());
        if (partial == MoveState.PARTIAL) {
            return GameMove.move(game, move.getSecondSquare());
        }
        return MoveState.INCORRECT;
    }

    public static void undo(Game game, Move move) {
        GameMove.undoMove(move);
    }
}
