package com.dharmacode.pythagonacci;

public record PythagonacciBoxTriplet(PythagonacciBox x, PythagonacciBox y, PythagonacciBox z) {

    @Override
    public String toString() {
        return x.toString() + y.toString() + z.toString();
    }

    public String toStringRecursive() {
        return x.toStringRecursive() + y.toStringRecursive() + z.toStringRecursive();
    }

    public boolean validateIsPythagoreanTripleRecursive() {
        return x.validateIsPythagoreanTripleRecursive() && y.validateIsPythagoreanTripleRecursive() &&
                z.validateIsPythagoreanTripleRecursive();
    }
}
