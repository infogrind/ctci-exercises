import java.util.Stack;

public class SortStack {

    private static <T extends Comparable<T>> void sortStack(
            Stack<T> stack, Stack<T> temp) {

        if (stack.isEmpty())
            return;

        T top = stack.pop();
        sortStack(stack, temp);

        while (!stack.isEmpty())
            temp.push(stack.pop());

        while (!temp.isEmpty() && temp.peek().compareTo(top) > 0)
            stack.push(temp.pop());

        stack.push(top);

        while (!temp.isEmpty())
            stack.push(temp.pop());
    }

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> temp = new Stack<Integer>();

        stack.push(2);
        stack.push(7);
        stack.push(8);
        stack.push(5);
        stack.push(2);

        sortStack(stack, temp);

        System.out.println("Sorted stack: " + stack);
    }
}
