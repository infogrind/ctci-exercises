import java.util.Map;
import java.util.HashMap;

public class State {
    public final int start;
    public final int end;
    public final Map<Integer, Integer> nbCount;

    public State(int start, int end, Map<Integer, Integer> nbCount) {
        this.start = start;
        this.end = end;
        this.nbCount = nbCount;
    }

    public int length() {
        return end - start + 1;
    }

    @Override
    public State clone() {
        Map<Integer,Integer> clonedNbCount = new HashMap<Integer,Integer>(nbCount);
        return new State(start, end, clonedNbCount);
    }

    int[] toStartEnd() {
        int[] result = new int[2];
        result[0] = start;
        result[1] = end;
        return result;
    }

    @Override
    public String toString() {
        return "State(" + start + " -> " + end + " | nbCount: " + nbCount + "| length: " + length() + ")";
    }
}
