package NQueensSolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class NQueens {
    public static int getNQueensSolution(int n) {
        unique = new HashSet<>();
        excludedPositions = new HashMap<>();
        int solutions = _solve(0, n, new ArrayList<>());
        return solutions;
    }

    private static Set<List<Integer>> unique;

    private static int _solve(int row, int n, List<Integer> placedQueens) {
        if (row == n) {
            // solution found
            // adding solution to found solutions so no duplicates get found (also add rotated solutions + mirrored)
            int newFound = 0;
            if (addSolution(placedQueens)) newFound++;
            // test mirrored
            List<Integer> mirrored = mirrorHorizontally(placedQueens);
            if (addSolution(mirrored)) newFound++;
            mirrored = mirrorVertically(placedQueens);
            if (addSolution(mirrored)) newFound++;

            // List<Integer> mirrored = null;
            List<Integer> rotated = placedQueens;
            for (int i = 1; i <= 3; i++) {  // we can rotate 3 times, 90 180 270
                rotated = rotate(rotated);
                if (addSolution(rotated)) newFound++;

                mirrored = mirrorHorizontally(rotated);
                if (addSolution(placedQueens)) newFound++;
                mirrored = mirrorVertically(rotated);
                if (addSolution(placedQueens)) newFound++;
            }
            
            return newFound;
        } else {
            Set<Integer> positions = findPositions(row, n, placedQueens);
            int solutions = 0;
            for (int pos : positions) {
                placedQueens.add(pos);
                solutions += _solve(row+1, n, placedQueens);
                placedQueens.remove(placedQueens.size()-1); // remove previously added
            }
            return solutions;
        }
    }

    private static HashMap<List<Integer>, Set<Integer>> excludedPositions;
    private static Set<Integer> findPositions(int row, int n, List<Integer> placedQueens) {
        Set<Integer> positions = new HashSet<>();
        for (int i = 0; i < n; i++) positions.add(i);
        if (placedQueens == null) return positions;

        for (int qr = 0; qr < placedQueens.size(); qr++) {
            int qc = placedQueens.get(qr);
            int d = row - qr;
            positions.remove(qc);
            positions.remove(qc-d);
            positions.remove(qc+d);
        }

        // if a partial solution gets encountered that is already entirely explored, remove it from the positions list (no need to reexplore), 
        // the reason why it is already explored is because we encountered a mirror or rotation of said solution
        if (excludedPositions.containsKey(placedQueens)) {
            for (int p : excludedPositions.get(placedQueens)) {
                positions.remove(p);
            }
        }

        return positions;
    }

    private static boolean addSolution(List<Integer> solution) {
        if (!unique.add(new ArrayList<>(solution))) return false;

        excludeSolution(solution, solution.size());
        return true;
    }
    private static void excludeSolution(List<Integer> solution, int n) {
        int row = solution.size()-1;
        if (row < 0) return;

        LinkedList<Integer> key = new LinkedList<>(solution);
        key.removeLast();
        
        if (!excludedPositions.containsKey(key)) {
            excludedPositions.put(key, new HashSet<>());
        }
        excludedPositions.get(key).add(solution.get(row));

        if (excludedPositions.get(key).size() == n) {
            excludeSolution(key, n);
        }
    }

    public static List<Integer> rotate(List<Integer> solution) {
        HashMap<Integer, Integer> pos = new HashMap<>();
        for (int y = 0; y < solution.size(); y++) {
            int x = solution.get(y);
            int newX = y;
            int newY = -x;
            newY += solution.size()-1;
            pos.put(newY, newX);
        }

        List<Integer> result = new ArrayList<>(solution.size());
        for (int i = 0; i < solution.size(); i++) {
            result.add(pos.get(i));
        }

        return result;
    }

    public static List<Integer> mirrorVertically(List<Integer> toMirror) {
        List<Integer> result = new ArrayList<>(toMirror.size());
        for (int i = toMirror.size()-1; i >= 0; i--) {
            result.add(toMirror.get(i));
        }
        return result;
    }
    public static List<Integer> mirrorHorizontally(List<Integer> toMirror) {
        List<Integer> result = new ArrayList<>(toMirror);
        for (int i = 0; i < result.size(); i++) {
            result.set(i, (result.size()-1) - result.get(i));
        }
        return result;
    }

}
