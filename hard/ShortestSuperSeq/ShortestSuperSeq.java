import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class ShortestSuperSeq {

    public static int[] shortestSupSeq(int[] shortSeq, int[] longSeq) {

        if (shortSeq.length == 0)
            return new int[0];
        if (longSeq.length == 0)
            return new int[0];

        Set<Integer> shortSet = arrayToSet(shortSeq);
        State optState = initialState(longSeq, shortSet);

        if (optState.length() == 1)
            return optState.toStartEnd();

        if (optState == null)
            return new int[0];

        State currentState = optState.clone();

        for (int i = optState.end + 1; i < longSeq.length; i++) {

            int current = longSeq[i];
            currentState = new State(currentState.start, i, currentState.nbCount);

            if (shortSet.contains(current))
                increaseNbCount(currentState.nbCount, current);

            State newState = updateStateLeft(currentState, longSeq);

            if (newState.length() < optState.length()) {
                optState = newState;
            }
            else {
            }
        }

        int[] result = new int[2];
        result[0] = optState.start;
        result[1] = optState.end;

        return result;
    }

    private static Set<Integer> arrayToSet(int[] array) {

        Set<Integer> result = new HashSet<Integer>();
        for (int i: array)
            result.add(i);
        return result;
    }

    private static State initialState(int[] longSeq, Set<Integer> shortSet) {

        Map<Integer, Integer> nbCount = new HashMap<Integer, Integer>();

        int start = firstFoundIndex(longSeq, shortSet);
        if (start == -1)
            return null; // nothing found

        if (nbCount.size() == shortSet.size()) {
            nbCount.put(longSeq[start], 1);
            return new State(start, start, nbCount); // only one number in set, we're done
        }

        int end = start;

        for (; end < longSeq.length; end++) {

            int current = longSeq[end];

            if (shortSet.contains(current))
                increaseNbCount(nbCount, current);

            if (nbCount.size() == shortSet.size())
                return new State(start, end, nbCount);
        }

        // Not all numbers were found
        return null;
    }

    private static int firstFoundIndex(int[] longSeq, Set<Integer> shortSet) {

        for (int i = 0; i < longSeq.length; i++) {
            if (shortSet.contains(longSeq[i]))
                return i;
        }
        return -1;
    }

    private static void increaseNbCount(Map<Integer,Integer> nbCount, int nb) {

        if (nbCount.containsKey(nb))
            nbCount.put(nb, nbCount.get(nb) + 1);
        else
            nbCount.put(nb, 1);
    }

    private static State updateStateLeft(State currentState, int[] longSeq) {

       // Assumption: element at longSeq[currentState.start] is from the
       // shortSet
       int newStart;
       Map<Integer, Integer> newNbCount = new HashMap<Integer,Integer>(currentState.nbCount);
       for (newStart = currentState.start; newStart < longSeq.length; newStart++)
       {
           int current = longSeq[newStart];
           if (!newNbCount.containsKey(current)) {
           }
           else if (newNbCount.get(current) > 1) {
               // We have this number at another place - can drop it from the
               // left side
               newNbCount.put(current, newNbCount.get(current) - 1);
           }
           else {
               // no more possible to advance
               return new State(newStart, currentState.end, newNbCount);
           }
       }

       return new State(newStart, currentState.end, newNbCount);
    }

    public static void main(String[] args) {
        int[][] shortSeqs = {{1, 5, 9}, {2, 1, 0, 25}};
        int[][] longSeqs = {{7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7},
            {1, 2, 8, 0, 7, 8, 3, 25, 100, 0, 1, 3, 2, 77}};

        for (int i = 0; i < shortSeqs.length; i++) {
            int[] shortSeq = shortSeqs[i];
            int[] longSeq = longSeqs[i];

            System.out.println("Short seq: " + Arrays.toString(shortSeq));
            System.out.println("Long seq: " + Arrays.toString(longSeq));
            System.out.println("Indices: " + Arrays.toString(shortestSupSeq(shortSeq, longSeq)));
        }
    }
}
