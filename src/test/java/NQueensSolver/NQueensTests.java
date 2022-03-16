package NQueensSolver;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class NQueensTests {

    private static void test(int n, int expected) {
        int solutions = NQueens.getNQueensSolution(n);
        assertThat(solutions).isEqualTo(expected);
    }
    
    @Test
    public void test() {
        test(1, 1);
        test(2, 0);
        test(3, 0);
        test(4, 2);
        test(5, 10);
        test(6, 4);
        test(7, 40);
        test(8, 92);
        test(9, 352);
        test(10, 724);
        test(11, 2680);
        test(12, 14200);
        test(13, 73712);
        test(14, 365596);
        test(15, 2279184);
    }

    @Test
    public void rotationTest() {
        List<Integer> toRotate = new ArrayList<>(4);
        toRotate.add(2);
        toRotate.add(3);
        toRotate.add(0);
        toRotate.add(1);
        List<Integer> result = NQueens.rotate(toRotate);
        List<Integer> expected = new ArrayList<>(4);
        expected.add(1);
        expected.add(0);
        expected.add(3);
        expected.add(2);
        assertThat(result).isEqualTo(expected);
        assertThat(NQueens.rotate(result)).isEqualTo(toRotate);
    }

    @Test
    public void rotationTest2() {
        List<Integer> toRotate = new ArrayList<>(8);
        toRotate.add(4);
        toRotate.add(2);
        toRotate.add(7);
        toRotate.add(3);
        toRotate.add(6);
        toRotate.add(0);
        toRotate.add(5);
        toRotate.add(1);
        List<Integer> result = NQueens.rotate(toRotate);
        List<Integer> expected = new ArrayList<>(8);
        expected.add(2);
        expected.add(4);
        expected.add(6);
        expected.add(0);
        expected.add(3);
        expected.add(1);
        expected.add(7);
        expected.add(5);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void mirrorHorizTest() {
        List<Integer> toMirror = new ArrayList<>(8);
        toMirror.add(4);
        toMirror.add(2);
        toMirror.add(7);
        toMirror.add(3);
        toMirror.add(6);
        toMirror.add(0);
        toMirror.add(5);
        toMirror.add(1);
        List<Integer> result = NQueens.mirrorHorizontally(toMirror);
        List<Integer> expected = new ArrayList<>(8);
        expected.add(3);
        expected.add(5);
        expected.add(0);
        expected.add(4);
        expected.add(1);
        expected.add(7);
        expected.add(2);
        expected.add(6);
        assertThat(result).isEqualTo(expected);
    }
    
    @Test
    public void mirrorVertTest() {
        List<Integer> toMirror = new ArrayList<>(8);
        toMirror.add(4);
        toMirror.add(2);
        toMirror.add(7);
        toMirror.add(3);
        toMirror.add(6);
        toMirror.add(0);
        toMirror.add(5);
        toMirror.add(1);
        List<Integer> result = NQueens.mirrorVertically(toMirror);
        List<Integer> expected = new ArrayList<>(8);
        expected.add(1);
        expected.add(5);
        expected.add(0);
        expected.add(6);
        expected.add(3);
        expected.add(7);
        expected.add(2);
        expected.add(4);
        assertThat(result).isEqualTo(expected);
    }

}
