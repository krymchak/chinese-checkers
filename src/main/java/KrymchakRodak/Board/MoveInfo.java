package KrymchakRodak.Board;

public class MoveInfo {
    private int oldI, oldJ;
    private int newI, newJ;

    public MoveInfo() {}

    public MoveInfo(int oldI, int oldJ, int newI, int newJ) {
        this.oldI = oldI;
        this.oldJ = oldJ;

        this.newI = newI;
        this.newJ = newJ;
    }

    public int getOldI() {
        return this.oldI;
    }

    public int getOldJ() {
        return this.oldJ;
    }

    public int getNewI() {
        return this.newI;
    }

    public int getNewJ() {
        return this.newJ;
    }
}
