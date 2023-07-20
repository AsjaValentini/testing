import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Test {

    @Test
    public void testSum() {
        int result1 = sum(5, 3);
        assertEquals(8, result1);

        int result2 = sum(-2, 7);
        assertEquals(5, result2);

        int result3 = sum(0, 0);
        assertEquals(0, result3);
    }

    private int sum(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("Test");
    }
}
