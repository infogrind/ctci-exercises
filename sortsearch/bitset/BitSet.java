public class BitSet {

    int[] vector;
    final int size;

    public BitSet(int size) {
        this.size = size;
        vector = new int[size];
    }

    public void set(int i) {

        if (i >= size)
            throw new IndexOutOfBoundsException();

        int wordidx = i >> 5;
        int bitidx = i & 31;
        int setmask = 1 << bitidx;

        vector[wordidx] |= setmask;
    }

    public void clear(int i) {
        if (i >= size)
            throw new IndexOutOfBoundsException();

        int wordidx = i >> 5;
        int bitidx = i & 31;
        int clearmask = ~(1 << bitidx);

        vector[wordidx] &= clearmask;
    }

    public int get(int i) {
        if (i >= size)
            throw new IndexOutOfBoundsException();

        int wordidx = i >> 5;
        int bitidx = i & 31;

        return (vector[wordidx] >> bitidx) & 1;
    }

    public static void main(String[] args) {

        BitSet bs = new BitSet(32000);
        for (int i = 0; i < 32000; i++)
            bs.clear(i);

        int pos = 31998;
        bs.set(pos);

        System.out.println("Bit at pos " + pos + ": " + bs.get(pos));
        System.out.println("Bit at pos " + (pos + 1) + ": " + bs.get(pos+1));
    }
}
