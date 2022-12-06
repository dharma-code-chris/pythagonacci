package com.chrisdjames1.pythagonacci;

public class FiboNodeImpl implements FiboNode {

    // Exit condition control - important because calling toStringRecursive() does what it says on the tin.
    private static int maxDepth = 5;

    // The id is an address in the node tree. The root node is 0. Child nodes are X, Y or Z.
    // e.g. id == "0XYYXZ" means that the node is located at a depth of 5 and can be found by following the path:
    // root to its child X, to its child Y, to its child Y, to its child X and finally to its child Z.
    // It could be interesting to research the patterns in the ID when treated as a base-3 number vs. the node values.
    // TODO: How, if at all, does the base-3 number relate to the node values?
    private final String id;

    // A set of four integers {a, b, c, d} that always combine to a Pythagorean triple as follows:
    // (a*d)^2 + (2*b*c)^2 = (a*c + b*d)^2
    // Visualise them as a clockwise matrix, from the top-left:
    // a  b
    // d  c
    private final long[] values;

    private FiboNode parent;

    private Triplet<FiboNode> children;

    public FiboNodeImpl() {
        // Root
        this.id = "0";
        this.values = new long[]{1, 1, 2, 3};
    }

    public FiboNodeImpl(int maxDepth) {
        this();
        FiboNodeImpl.maxDepth = maxDepth;
    }

    public FiboNodeImpl(long[] values, FiboNode parent, String relativeId) {
        this.values = values;
        this.parent = parent;
        this.id = parent.getId() + relativeId;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public long[] getValues() {
        return values;
    }

    /**
     * TODO: From the Mathologer video there is actually a way to calculate the parent using some geometry.
     */
    @Override
    public FiboNode getParent() {
        return parent;
    }

    /**
     * Each node has 3 child nodes.
     */
    @Override
    public Triplet<FiboNode> getChildren() {
        if (children == null) {
            // Calculate children (comment diagram numbers are indices of this.values).
            // X: Take index 1 and 3, move 3 to the top row, then Fibonacci clockwise.
            // .  1 -> 3  1 -> 3     1
            // 3  . -> .  . -> 3+2*1 3+1
            FiboNodeImpl x = new FiboNodeImpl(valuesFromFirst2(values[3], values[1]), this, "X");

            // Y: Take index 2 and 3, move them to the top row, then Fibonacci clockwise.
            // .  . -> 3  2 -> 3     2
            // 3  2 -> .  . -> 3+2*2 3+2
            FiboNodeImpl y = new FiboNodeImpl(valuesFromFirst2(values[3], values[2]), this, "Y");

            // Z: Take index 0 and 2, move 2 to the top row, then Fibonacci clockwise.
            // 0  . -> 0  2 -> 0     2
            // .  2 -> .  . -> 0+2*2 0+2
            FiboNodeImpl z = new FiboNodeImpl(valuesFromFirst2(values[0], values[2]), this, "Z");

            this.children = new FiboNodeTriplet(x, y, z);
        }
        return children;
    }

    private boolean isEndRecursion() {
        return this.id.length() > maxDepth;
    }

    @Override
    public String toStringRecursive() {
        String node = toString();
        // Exit condition: check ID length
        return node + (isEndRecursion() ? "" : getChildren().toStringRecursive());
    }

    @Override
    public String toString() {
        // Clockwise matrix from top-left
        return String.format("%s:\n%s\n%d\t%d\n%d\t%d\n", id, getPythagoreanTripleString(), values[0], values[1],
                values[3], values[2]);
    }

    @Override
    public long getPythagoreanTripleSideA() {
        return values[0] * values[3];
    }

    @Override
    public long getPythagoreanTripleSideB() {
        return 2 * values[1] * values[2];
    }

    @Override
    public long getPythagoreanTripleHypotenuse() {
        return values[0] * values[2] + values[1] * values[3];
    }

    @Override
    public String getPythagoreanTripleString() {
        return String.format("%d^2 + %d^2 = %d^2", getPythagoreanTripleSideA(), getPythagoreanTripleSideB(),
                getPythagoreanTripleHypotenuse());
    }

    @Override
    public boolean validate() {
        long a = getPythagoreanTripleSideA();
        long b = getPythagoreanTripleSideB();
        long c = getPythagoreanTripleHypotenuse();
        boolean isValid = a * a + b * b == c * c;
        if (!isValid) {
            throw new IllegalStateException(id + " is invalid!\n" + this);
        }
        return true;
    }

    @Override
    public boolean validateRecursive() {
        return validate() && (isEndRecursion() || getChildren().validateRecursive());
    }

    // val0        val1
    // val0+2*val1 val0+val1
    private static long[] valuesFromFirst2(long val0, long val1) {
        long val2 = val0 + val1;
        return new long[]{val0, val1, val2, val2 + val1};
    }
}
