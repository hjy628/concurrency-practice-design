package chap7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hjy on 18-2-26.
 */
public class PsoValue {
    final double value;
    final List<Double> x;

    public PsoValue(double value, List<Double> x) {
        this.value = value;
        List<Double> b = new ArrayList<Double>(5);
        b.addAll(x);
        this.x = Collections.unmodifiableList(b);
    }

    public double getValue() {
        return value;
    }

    public List<Double> getX() {
        return x;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("value:").append(value).append("\n")
                .append(x.toString());
        return sb.toString();
    }
}
