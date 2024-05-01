package com.dharmacode.pythagonacci;

public class FiboNode {

    private static final int IX_A = 0;
    private static final int IX_B = 1;
    private static final int IX_C = 2;
    private static final int IX_D = 3;

    /**
     * How far is this node from the bottom of the tree.
     */
    private final Integer altitude;

    private static final boolean PRIME_ANALYSIS = false;

    /**
     * The id is an address in the node tree. The root node is 0. Child nodes are X, Y or Z.
     * e.g. id == "0XYYXZ" means that the node is located at a depth of 5 and can be found by following the path:
     * root to its child X, to its child Y, to its child Y, to its child X and finally to its child Z.
     */
    private final String id;

    /**
     * A set of four integers {a, b, c, d} that always combine to a Pythagorean triple as follows:
     * F1 + F2 = F3
     * (a * d)^2 + (2 * b * c)^2 = (a * c + b * d)^2
     * Visualise them as a clockwise matrix, from the top-left:
     * a  b
     * d  c
     */
    private final Long[] values;

    private FiboNodeTriplet children;

    /**
     * Constructor for root node.
     */
    public FiboNode(Integer altitude, Long[] values) {
        this(altitude, values, null, "0");
    }

    public FiboNode(Integer altitude, Long[] values, FiboNode parent, String relativeId) {
        // Descend towards the bottom of the tree by one step:
        this.altitude = altitude - 1;
        if (values.length != 4) {
            throw new IllegalArgumentException("values must be length 4");
        }
        this.values = values;
        this.id = parent != null ? parent.getId() + relativeId : relativeId;
    }

    public String getId() {
        return id;
    }

    /**
     * Each node has 3 child nodes.
     */
    public FiboNodeTriplet getChildren() {
        if (children == null) {
            // Calculate children:
            // X: Take values b and d, move d to the top row, then Fibonacci clockwise.
            // .  b -> d  b -> d     b
            // d  . -> .  . -> d+b+b d+b
            FiboNode x = new FiboNode(altitude, fibonacci2x2(values[IX_D], values[IX_B]), this, "X");

            // Y: Take values d and c, move them to the top row, then Fibonacci clockwise.
            // .  . -> d  c -> d     c
            // d  c -> .  . -> d+c+c d+c
            FiboNode y = new FiboNode(altitude, fibonacci2x2(values[IX_D], values[IX_C]), this, "Y");

            // Z: Take values a and c, move c to the top row, then Fibonacci clockwise.
            // a  . -> a  c -> a     c
            // .  c -> .  . -> a+c+c a+c
            FiboNode z = new FiboNode(altitude, fibonacci2x2(values[IX_A], values[IX_C]), this, "Z");

            this.children = new FiboNodeTriplet(x, y, z);
        }
        return children;
    }

    private boolean isEndRecursion() {
        return altitude < 0;
    }

    public String toStringRecursive() {
        return toString() + (isEndRecursion() ? "" : getChildren().toStringRecursive());
    }

    @Override
    public String toString() {
        return String.format("%s: %s\n%d\t%d\n%d\t%d\n%s",
                id, getPythagoreanTripleString(),
                values[IX_A], values[IX_B],
                values[IX_D], values[IX_C],
                getPrimeAnalysis());
    }

    private String getPrimeAnalysis() {
        if (!PRIME_ANALYSIS) {
            return "";
        }
        // Only the hypotenuse has the potential to be prime
        boolean cIsPrime = isPrime(getPythagoreanTripleHypotenuse());
        int val0IsPrime = isPrime(values[IX_A]) ? 1 : 0;
        int val1IsPrime = isPrime(values[IX_B]) ? 1 : 0;
        int val2IsPrime = isPrime(values[IX_C]) ? 1 : 0;
        int val3IsPrime = isPrime(values[IX_D]) ? 1 : 0;

        // Clockwise from top-left
        return String.format("Prime analysis:\nh: %b\n%d\t%d\n%d\t%d\n",
                cIsPrime,
                val0IsPrime, val1IsPrime,
                val3IsPrime, val2IsPrime);
    }

    /**
     * F1 = (a * d)^2
     * "Side A" = a * d
     */
    public long getPythagoreanTripleSideA() {
        return values[IX_A] * values[IX_D];
    }

    /**
     * F2 = (2 * b * c)^2
     * "Side B" = 2 * b * c
     */
    public long getPythagoreanTripleSideB() {
        return 2 * values[IX_B] * values[IX_C];
    }

    /**
     * F3 = (a * c + b * d)^2
     * "Hypotenuse" = a * c + b * d
     */
    public long getPythagoreanTripleHypotenuse() {
        return values[IX_A] * values[IX_C] + values[IX_B] * values[IX_D];
    }

    public String getPythagoreanTripleString() {
        return String.format("%d^2 + %d^2 ?= %d^2", getPythagoreanTripleSideA(), getPythagoreanTripleSideB(),
                getPythagoreanTripleHypotenuse());
    }

    public boolean validateIsPythagoreanTriple() {
        long a = getPythagoreanTripleSideA();
        long b = getPythagoreanTripleSideB();
        long c = getPythagoreanTripleHypotenuse();
        boolean isValid = a * a + b * b == c * c;
        if (!isValid) {
            throw new IllegalStateException(id + " is invalid!\n" + this);
        }
        return true;
    }

    public boolean validateIsPythagoreanTripleRecursive() {
        return validateIsPythagoreanTriple() &&
                (isEndRecursion() || getChildren().validateIsPythagoreanTripleRecursive());
    }

    private static Long[] fibonacci2x2(long a, long b) {
        long c = a + b;
        Long[] newValues = new Long[4];
        newValues[IX_A] = a;
        newValues[IX_B] = b;
        newValues[IX_C] = c;
        newValues[IX_D] = c + b;
        return newValues;
    }

    private static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        long sqrtN = (long) Math.sqrt(n) + 1;
        for (long i = 6L; i <= sqrtN; i += 6) {
            if (n % (i - 1) == 0 || n % (i + 1) == 0) return false;
        }
        return true;
    }
}
