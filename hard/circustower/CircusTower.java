import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;

public class CircusTower {

    private static class Param {
        final int weight;
        final int i;
        public Param(int weight, int i) {
            this.weight = weight;
            this.i = i;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            else if (!(other instanceof Param))
                return false;
            else {
                Param that = (Param) other;
                return this.weight == that.weight && this.i == that.i;
            }
        }

        @Override
        public int hashCode() {
            return weight * 31 + i;
        }
    }

    private static ArrayList<Person> sortPeople(List<Person> people) {

        ArrayList<Person> result = new ArrayList<Person>(people);
        Collections.sort(result, new Comparator<Person>() {
            public int compare(Person p1, Person p2) {
                if (p1.height < p2.height)
                    return -1;
                else if (p1.height > p2.height)
                    return 1;
                else if (p1.weight < p2.weight)
                    return -1;
                else if (p1.weight > p2.weight)
                    return 1;
                else
                    return 0;
            }
        });

        return result;
    }

    public static List<Person> maxTower(List<Person> people) {

        Map<Param, LinkedList<Person>> memo;
        memo = new HashMap<Param, LinkedList<Person>>();

        ArrayList<Person> sorted = sortPeople(people);
        Param initParam = new Param(0, 0);
        return maxTower(initParam, sorted, memo);
    }

    private static LinkedList<Person> maxTower(
            Param p,
            ArrayList<Person> sorted,
            Map<Param, LinkedList<Person>> memo)
    {

        if (!memo.containsKey(p)) {
            final LinkedList<Person> result;

            if (p.i == sorted.size())
                result = new LinkedList<Person>(); // base case
            else {
                int j = p.i;
                while (j < sorted.size() && sorted.get(j).weight <= p.weight)
                    j++;

                if (j == sorted.size())
                    result = new LinkedList<Person>(); // another base case
                else {
                    // Case 1: use current person
                    Person current = sorted.get(j);
                    LinkedList<Person> l1 = maxTower(
                            new Param(current.weight, j+1),
                            sorted,
                            memo);
                    l1.addFirst(current);

                    // Case 2: do not use current person
                    LinkedList<Person> l2 = maxTower(
                            new Param(p.weight, j+1),
                            sorted,
                            memo);

                    if (l1.size() >= l2.size())
                        result = l1;
                    else
                        result = l2;
                }

            }

            memo.put(p, result);
        }

        return new LinkedList<Person>(memo.get(p));
    }

    public static void main(String[] args) {
        List<Person> p1 = new LinkedList<Person>() {{
            add(new Person(170, 65));
            add(new Person(190, 40));
            add(new Person(120, 40));
            add(new Person(120, 80));
            add(new Person(180, 85));
            add(new Person(190, 100));
        }};

        List<Person> p2 = new LinkedList<Person>() {{
            add(new Person(65, 100));
            add(new Person(70, 150));
            add(new Person(56, 90));
            add(new Person(75, 190));
            add(new Person(60, 95));
            add(new Person(68, 110));
        }};

        List<Person> maxTower1 = maxTower(p1);
        List<Person> maxTower2 = maxTower(p2);

        System.out.println("Max tower 1: " + maxTower1);
        System.out.println("Max tower 2: " + maxTower2);
    }
} 
