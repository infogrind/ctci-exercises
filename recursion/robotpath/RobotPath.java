import java.util.List;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class RobotPath {

    public static List<Position> solvingPath(boolean[][] maze, Position start) {

        Stack<Position> startingPath = new Stack<Position>();
        Set<Position> seen = new HashSet<Position>();
        startingPath.add(start);
        seen.add(start);

        return solvingPath(maze, startingPath, seen);
    }

    private static List<Position> solvingPath(
            boolean[][] maze,
            Stack<Position> pathUpToHere,
            Set<Position> seen)
    {
        if (isGoalPosition(maze, pathUpToHere.peek()))
                return pathToList(pathUpToHere);

        List<Position> availableSteps = availableNextSteps(maze, pathUpToHere, seen);
        if (availableSteps == null)
            return null; // Dead end

        for (Position step: availableSteps) {
            pathUpToHere.push(step);
            seen.add(step);

            List<Position> path = solvingPath(maze, pathUpToHere, seen);
            if (path != null)
                return path; // Pass on solution

            pathUpToHere.pop();
            seen.remove(step);
        }

        // Starting point did not leave to any solving path
        return null;
    }

    private static List<Position> availableNextSteps(
            boolean[][] maze,
            Stack<Position> pathUpToHere,
            Set<Position> seen)
    {
        Position pos = pathUpToHere.peek();
        List<Position> steps = new LinkedList<Position>();
        List<Position> tentativeSteps = new LinkedList<Position>();

        if (pos.x - 1 >= 0)
            tentativeSteps.add(new Position(pos.x - 1, pos.y));
        if (pos.x + 1 < maze[0].length)
            tentativeSteps.add(new Position(pos.x + 1, pos.y));
        if (pos.y - 1 >= 0)
            tentativeSteps.add(new Position(pos.x, pos.y - 1));
        if (pos.y + 1 < maze.length)
            tentativeSteps.add(new Position(pos.x, pos.y + 1));
        
        for (Position step: tentativeSteps)
            if (!seen.contains(step) && maze[step.y][step.x])
                steps.add(step);

        return steps;
    }

    private static List<Position> pathToList(Stack<Position> path) {

        List<Position> result = new LinkedList<Position>();
        while (!path.isEmpty())
            result.add(path.pop());

        Collections.reverse(result);

        return result;
    }

    private static boolean isGoalPosition(boolean[][] maze, Position pos) {
        
        return pos.x == maze[0].length - 1 && pos.y == maze.length - 1;
    }

    public static void main(String[] args) {

        boolean[][] maze1 = {{true, false, true, true},
            {true, true, true, true},
            {false, false, true, false},
            {true, true, true, false},
            {true, true, true, true}};


        List<Position> path = solvingPath(maze1, new Position(0,0));

        System.out.println("The solving path is: " + path);
    }
}
