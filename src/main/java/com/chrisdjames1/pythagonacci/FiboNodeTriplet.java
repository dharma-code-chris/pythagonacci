package com.chrisdjames1.pythagonacci;

public class FiboNodeTriplet implements Triplet<FiboNode> {

    private final FiboNode x;
    private final FiboNode y;
    private final FiboNode z;

    public FiboNodeTriplet(FiboNode x, FiboNode y, FiboNode z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public FiboNode getX() {
        return x;
    }

    @Override
    public FiboNode getY() {
        return y;
    }

    @Override
    public FiboNode getZ() {
        return z;
    }

    @Override
    public FiboNode[] toArray() {
        return new FiboNode[]{x, y, z};
    }

    @Override
    public String toString() {
        return x.toString() + y.toString() + z.toString();
    }

    @Override
    public String toStringRecursive() {
        return x.toStringRecursive() + y.toStringRecursive() + z.toStringRecursive();
    }

    @Override
    public boolean validate() {
        return x.validate() && y.validate() && z.validate();
    }

    @Override
    public boolean validateRecursive() {
        return x.validateRecursive() && y.validateRecursive() && z.validateRecursive();
    }
}
