package chap7;

/**
 * Created by hjy on 18-2-26.
 */
public final class PBestMsg {
    final PsoValue value;

    public PBestMsg(PsoValue value) {
        this.value = value;
    }

    public PsoValue getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
