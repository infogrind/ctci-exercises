public class StringRotation {

    public static boolean isRotation(String s1, String s2) {

        if (s1.length() != s2.length())
            return false;
        else
            return (s2+s2).contains(s1);
    }

    public static void main(String[] args) {

        String[][] pairs = {{"Marius", "iusMar"},
            {"Marius", "ariusM"},
            {"Marius", "sMariu"},
            {"Marius", "MariusMarius"},
            {"Marius", "Marius"},
            {"Marius", "Mairus"},
            {"Mr Smith", "SmithMr "}};

        for (int i = 0; i < pairs.length; i++) {
            String s1 = pairs[i][0];
            String s2 = pairs[i][1];

            System.out.println(s1 + " / " + s2 + " -> " + isRotation(s1, s2));
        }
    }
}
