package chap7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hjy on 18-2-24.
 */
public final class ImmutableMessage {

    private final int sequenceNumber;

    private final List<String> values;

    public ImmutableMessage(int sequenceNumber, List<String> values) {
        this.sequenceNumber = sequenceNumber;
        this.values = Collections.unmodifiableList(new ArrayList<String>(values));
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public List<String> getValues() {
        return values;
    }
}
