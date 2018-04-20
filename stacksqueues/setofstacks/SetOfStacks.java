public class SetOfStacks<T> {

    private static final int MAX_SIZE = 2; // testing
    private CountStack<CountStack<T>> stacks = new CountStack<CountStack<T>>();

    public T push(T element) {

        if (stacks.isEmpty() || stacks.peek().size() == MAX_SIZE)
            stacks.push(new CountStack<T>());

        stacks.peek().push(element);
        return element;
    }

    public T pop() {

        if (stacks.isEmpty())
            throw new RuntimeException("popping empty stack");

        if (stacks.peek().isEmpty())
            throw new RuntimeException("Bug: invariant violated, empty stack");

        T result = stacks.peek().pop();

        if (stacks.peek().isEmpty())
            stacks.pop();

        return result;
    }

    public T peek() {

        if (stacks.isEmpty())
            throw new RuntimeException("peeking in empty stack");

        return stacks.peek().peek();
    }

    public int nbOfStacks() {
        return stacks.size();
    }

    public static void main(String[] args) {
        SetOfStacks<Integer> stack = new SetOfStacks<Integer>();

        if (stack.nbOfStacks() != 0)
            throw new RuntimeException("Set of stack sound be empty.");

        stack.push(3);
        if (stack.peek() != 3)
            throw new RuntimeException("stack.peek returned wrong value");
        if (stack.nbOfStacks() != 1)
            throw new RuntimeException("Wrong number of stack");

        stack.push(2);
        if (stack.nbOfStacks() != 1)
            throw new RuntimeException("Wrong number of stack after adding 2");

        stack.push(44);
        if (stack.nbOfStacks() != 2)
            throw new RuntimeException("Wrong number of stack after adding 44");

        stack.push(27);
        stack.push(88);
        stack.push(-232);
        stack.push(22);
        stack.push(33);
        if (stack.peek() != 33)
            throw new RuntimeException("peek value should be 33");

        if (stack.nbOfStacks() != 4)
            throw new RuntimeException("there should be 4 stack now");

        stack.pop();
        stack.pop();
        stack.pop();
        if (stack.pop() != 88)
            throw new RuntimeException("popped value should be 88!");

        System.out.println("All OK :-)");
    }
}

