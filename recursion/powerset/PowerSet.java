import java.util.Set;
import java.util.HashSet;

public class PowerSet {

    public static <T> Set<Set<T>> powerSet(Set<T> set) {

        if (set.isEmpty()) {
            Set<Set<T>> empty = new HashSet<Set<T>>();
            empty.add(new HashSet<T>());
            return empty;
        }
        else {
            T first = set.iterator().next();
            set.remove(first);
            Set<Set<T>> subPowerSet = powerSet(set);

            Set<Set<T>> result = new HashSet<Set<T>>();
            for (Set<T> subset: subPowerSet) {
                Set<T> subsetPlusFirst = new HashSet<T>(subset);
                subsetPlusFirst.add(first);

                result.add(subset);
                result.add(subsetPlusFirst);
            }

            return result;
        }
    }

    public static void main(String[] args) {

        Set<Integer> set = new HashSet<Integer>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);

        Set<Set<Integer>> power = powerSet(set);

        System.out.println("Power set: " + power);
    }
}
