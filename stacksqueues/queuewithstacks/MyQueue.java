import java.util.Stack;

public class MyQueue<T> {

    private Stack<T> addStack = new Stack<T>();
    private Stack<T> removeStack = new Stack<T>();

    public void add(T elem) {
        addStack.add(elem);
    }

    public T remove() {
        if (addStack.isEmpty() && removeStack.isEmpty())
            throw new RuntimeException("removing from empty queue");
        else if (removeStack.isEmpty())
            unloadAddStack();

        return removeStack.pop();
    }

    private void unloadAddStack() {
        
        while (!addStack.isEmpty())
            removeStack.push(addStack.pop());
    }


    public static void main(String[] args) {

        MyQueue<Integer> queue = new MyQueue<Integer>();

        queue.add(1);
        queue.add(2);
        queue.add(3);
        if (queue.remove() != 1)
            throw new RuntimeException("Queue value should be 1");

        queue.add(4);
        queue.remove(); // 2
        queue.remove(); // 3
        if (queue.remove() != 4)
            throw new RuntimeException("Queue value should be 4");

        System.out.println("All OK :-)");
    }
}
