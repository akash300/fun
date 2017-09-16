package chess.interfaces;

/**
 * @author akashMaurya
 * @Date 16/09/17.
 */
public interface Moves {

    boolean multiSteps();

    boolean straight();

    boolean diagonal();

    //knight
    boolean canJump();

    boolean castle();

}
