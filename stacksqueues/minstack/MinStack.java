import java.util.Stack;
import java.util.EmptyStackException;

public class MinStack<T extends Comparable<T>> {

    private class StackNode<T> {
        final T element;
        final T min;

        public StackNode(T element, T min) {

            this.element = element;
            this.min = min;
        }
    }

    private final Stack<StackNode<T>> stack;

    public MinStack() {

        stack = new Stack<StackNode<T>>();
    }

    public void push(T element) {
        if (stack.isEmpty() || element.compareTo(stack.peek().min) < 0) 
            stack.push(new StackNode<T>(element, element));
        else
            stack.push(new StackNode<T>(element, stack.peek().min));
    }

    public T pop() {
        if  (stack.isEmpty())
            throw new EmptyStackException();
        else {
            return stack.pop().element;
        }
    }

    public T min() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        else
            return stack.peek().min;
    }

    public static void main(String[] args) {
        MinStack<Integer> minStack = new MinStack<Integer>();
        minStack.push(4);
        if (minStack.min() != 4)
            throw new RuntimeException("Expected stack min to be 4");

        minStack.push(5);
        if (minStack.min() != 4)
            throw new RuntimeException("Expected stack min to be still 4");

        minStack.push(2);
        if (minStack.min() != 2)
            throw new RuntimeException("New minimum should be 2");

        if (minStack.pop() != 2)
            throw new RuntimeException("Wrong popped element");

        if (minStack.min() != 4)
            throw new RuntimeException("Wrong minimum after popping");

        System.out.println("No errors :-)");
    }
}

