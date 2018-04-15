import java.util.LinkedList;

public class ListWeaver {

    public static <T> LinkedList<LinkedList<T>> allWeaves(LinkedList<T> first, LinkedList<T> second) {

        LinkedList<LinkedList<T>> result = new LinkedList<LinkedList<T>>();
        if (first.isEmpty()) {
            LinkedList<T> cloned = new LinkedList<T>();
            for (T t: second)
                cloned.add(t);
            result.add(cloned);
        }
        else if (second.isEmpty()) {
            LinkedList<T> cloned = new LinkedList<T>();
            for (T t: first)
                cloned.add(t);
            result.add(cloned);
        }
        else {
            T firstHead = first.removeFirst();
            LinkedList<LinkedList<T>> subWeavesFirst = allWeaves(first, second);
            for (LinkedList<T> l: subWeavesFirst) {
                l.addFirst(firstHead);
                result.add(l);
            }
            first.addFirst(firstHead);

            T secondHead = second.removeFirst();
            LinkedList<LinkedList<T>> subWeavesSecond = allWeaves(first, second);
            for (LinkedList<T> l: subWeavesSecond) {
                l.addFirst(secondHead);
                result.add(l);
            }
            second.addFirst(secondHead);
        }

        return result;
    }

    public static void main(String[] args) {
        LinkedList<String> l1 = new LinkedList<String>();
        l1.add("a");
        l1.add("b");
        l1.add("c");

        LinkedList<String> l2 = new LinkedList<String>();
        l2.add("1");
        l2.add("2");
        l2.add("3");

        LinkedList<LinkedList<String>> weaves = allWeaves(l1, l2);

        for (LinkedList<String> w: weaves) {
            System.out.println(w);
        }
    }
}
