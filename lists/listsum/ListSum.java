public class ListSum {

    public static Node sumLists(Node n1, Node n2) {
        return sumLists(n1, n2, 0);
    }

    private static Node sumLists(Node n1, Node n2, int carry) {

        if (n1 == null && n2 == null)
            if (carry == 0)
                return null; // base case
            else
                return new Node(carry, null);
        else {
            int sum = carry;
            if (n1 != null) {
                sum += n1.data;
                n1 = n1.next;
            }
            if (n2 != null) {
                sum += n2.data;
                n2 = n2.next;
            }

            System.out.println("Sum = " + sum);
            final int digitSum;
            final int newCarry;
            if (sum >= 10) {
                digitSum = sum - 10;
                newCarry = 1;
            } else {
                digitSum = sum;
                newCarry = 0;
            }
            System.out.println("digitSum = " + digitSum + ", newCarry = " + newCarry);

            final Node sumRest = sumLists(n1, n2, newCarry);
            return new Node(digitSum, sumRest);
        }
    }

    private static Node listFromArray(int[] array) {
        return listFromArray(array, 0);
    }

    private static Node listFromArray(int[] array, int start) {
        if (start == array.length)
            return null;
        else
            return new Node(array[start], listFromArray(array, start + 1));
    }

    public static void main(String[] args) {

        int[] a1 = {7, 1, 6, 9};
        int[] a2 = {5, 9, 4};

        final Node n1 = listFromArray(a1);
        final Node n2 = listFromArray(a2);
        final Node sum = sumLists(n1, n2);

        System.out.println("n1  = " + n1);
        System.out.println("n2  = " + n2);
        System.out.println("sum = " + sum);
    }
}
