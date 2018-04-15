import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class BuildOrder {

    public static List<String> buildOrder(String[] projects, String[][] deps)
    {
        Map<String,Set<String>> depMap = dependencyMap(projects, deps);
        Map<String,Set<String>> userMap = userMap(projects, deps);
        
        System.out.println("depMap: " + depMap);
        System.out.println("userMap: " + userMap);

        List<String> result = new LinkedList<String>();

        while (!depMap.isEmpty()) {
            List<String> noDepProjects = projectsWithoutDeps(depMap);
            System.out.println("No dep projects: " + noDepProjects);
            if (noDepProjects.isEmpty()) {
                // Error - cycle detected
                System.out.println("Cycle detected!");
                return null;
            }
            else {
                for (String p: noDepProjects) {
                    result.add(p);
                    depMap.remove(p);
                    Set<String> usersOfP = userMap.get(p);
                    for (String u: usersOfP) {
                        System.out.println("Removing project " + p + " from deps of " + u);
                        depMap.get(u).remove(p);
                    }
                }
            }
        }
        return result;
    }

    static Map<String, Set<String>> dependencyMap(String[] projects, String[][] deps) {

        Map<String, Set<String>> result = new HashMap<String, Set<String>>();
        for (String p: projects) {
            result.put(p, new HashSet<String>());
        }

        for (int i = 0; i < deps.length; i++) {
            String[] pair = deps[i];
            result.get(pair[1]).add(pair[0]);
        }
        return result;
    }

    static Map<String, Set<String>> userMap(String[] projects, String[][] deps) {

        Map<String,Set<String>> result = new HashMap<String, Set<String>>();
        for (String p: projects)
            result.put(p, new HashSet<String>());

        for (int i = 0; i < deps.length; i++) {
            String[] pair = deps[i];
            result.get(pair[0]).add(pair[1]);
        }
        return result;
    }

    static List<String> projectsWithoutDeps(Map<String, Set<String>> depMap) {
        List<String> result = new LinkedList<String>();
        for (String p: depMap.keySet()) {
            if (depMap.get(p).isEmpty())
                result.add(p);
        }
        return result;
    }

    public static void main(String[] args) {

        String[] projects = {"a","b","c","d","e","f"};
        String[][] deps = {{"f","a"}, {"f","d"}, {"f","b"}, {"a","d"}, {"b","d"}, {"d","c"}};

        String[][] cycleDeps = {{"f","a"}, {"f","d"}, {"f","b"}, {"a","d"}, {"b","d"},
            {"d","c"}, {"c", "b"}};

        List<String> order = buildOrder(projects, deps);

        System.out.println("Order:");
        System.out.println(order);

        System.out.println("Order with cycle:");
        System.out.println(buildOrder(projects, cycleDeps));
    }
}
