package com.chrisdjames1.pythagonacci;

public interface Triplet <T> {

    T getX();

    T getY();

    T getZ();

    T[] toArray();

    String toStringRecursive();

    boolean validate();

    boolean validateRecursive();

}
