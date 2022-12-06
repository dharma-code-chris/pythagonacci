package com.chrisdjames1.pythagonacci;

public interface FiboNode {

    // Values are referenced from top-left clockwise, for example,
    // for the RootFiboNode:
    //
    // 1  1
    // 3  2
    //
    // 0: 1
    // 1: 1
    // 2: 2
    // 3: 3

    long[] getValues();

    FiboNode getParent();

    Triplet<FiboNode> getChildren();

    String getId();

    String toStringRecursive();

    String getPythagoreanTripleString();

    long getPythagoreanTripleSideA();
    long getPythagoreanTripleSideB();
    long getPythagoreanTripleHypotenuse();

    boolean validate();

    boolean validateRecursive();

}
