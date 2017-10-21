public class GreatestCommonDivisor {

    public static void main(String[] args) {
        // Use the recursive it is more compact and makes sense to me!
        System.out.println(gcdRecursive(1000, 100));
        System.out.println(gcdIterative(1000, 100));
    }

    public static long gcdRecursive(long a, long b) {
        return b == 0 ? a : gcdRecursive(b, a % b);
    }

    public static long gcdIterative(long a, long b) {
        if (a == 0 || b == 0) return 0;

        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }

        return a;
    }
}
