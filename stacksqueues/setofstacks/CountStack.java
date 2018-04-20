import java.util.Stack;

public class CountStack<T> {

    private int count = 0;
    private Stack<T> stack = new Stack<T>();

    public T push(T element) {
        stack.push(element);
        count++;

        return element;
    }

    public T pop() {
        count--;
        if (stack.isEmpty())
            throw new RuntimeException("trying to pop empty CountStack!!");
        return stack.pop();
    }

    public T peek() {
        if (stack.isEmpty())
            throw new RuntimeException("Peeking in empty stack");

        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return stack.toString();
    }

    public int size() {
        return count;
    }
}
