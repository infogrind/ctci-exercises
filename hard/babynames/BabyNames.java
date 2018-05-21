import java.util.Map;
import java.util.HashMap;

public class BabyNames {

    public static Map<String, Integer> babyNames(Map<String, Integer> nameToFreq,
            String[][] equivNames) {
        
        Map<String, Integer> result = new HashMap<String, Integer>();
        Map<String, String> nameToClass = getNameClasses(equivNames);

        for (String name: nameToFreq.keySet()) {
            String clazz = nameToClass.get(name);
            if (clazz == null)
                result.put(name, nameToFreq.get(name));
            else if (result.containsKey(clazz))
                result.put(clazz, result.get(clazz) + nameToFreq.get(name));
            else
                result.put(clazz, nameToFreq.get(name));
        }

        return result;
    }

    private static Map<String, String> getNameClasses(String[][] equivNames) {

        Map<String, String> result = new HashMap<String, String>();
        for (String[] pair : equivNames) {
            if (result.containsKey(pair[0])) {
                String clazz = result.get(pair[0]);
                result.put(pair[1], clazz);
            }
            else if (result.containsKey(pair[1])) {
                String clazz = result.get(pair[1]);
                result.put(pair[0], clazz);
            }
            else {
                String clazz = pair[0];
                result.put(pair[0], clazz);
                result.put(pair[1], clazz);
            }
        }
        return result;
    }

    public static void main(String[] args) {

        String[][] equivPairs = {
            {"Jonathan", "John"},
            {"Jon", "Johnny"},
            {"Johnny", "John"},
            {"Kari", "Carrie"},
            {"Carleton", "Carlton"}};

        Map<String, Integer> nameFreqs = new HashMap<String, Integer>() {{
            put("John", 10);
            put("Jon", 3);
            put("Davis", 2);
            put("Kari", 3);
            put("Johnny", 11);
            put("Carlton", 8);
            put("Carleton", 2);
            put("Jonathan", 9);
            put("Carrie", 5);
        }};

        Map<String, Integer> classFreqs = babyNames(nameFreqs, equivPairs);
        for (String clazz: classFreqs.keySet())
            System.out.println(clazz + " (" + classFreqs.get(clazz) + ")");
    }
}
