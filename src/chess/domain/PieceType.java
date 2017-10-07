package chess.domain;

/**
 * @author akashMaurya
 * @Date 08/10/17.
 */
public enum PieceType {
    KING(Integer.MAX_VALUE),
    QUEEN(10),
    ROOK(5),
    BISHOPH(3),
    KNIGHT(3),
    PAWN(1);

    private int value;

    PieceType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
