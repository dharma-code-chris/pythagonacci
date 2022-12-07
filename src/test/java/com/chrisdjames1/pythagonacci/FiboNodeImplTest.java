package com.chrisdjames1.pythagonacci;

import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class FiboNodeImplTest {

    @Test
    public void testDepth1() {
        String fiboNodeString = new FiboNodeImpl(1).toStringRecursive();
        System.out.println(fiboNodeString);
    }

    @Test
    public void testDepth2() {
        String out = new FiboNodeImpl(2).toStringRecursive();
        System.out.println(out);
    }

    @Test
    public void testDepth3() {
        String out = new FiboNodeImpl(3).toStringRecursive();
        System.out.println(out);
    }

    @Test
    public void testDepth4() {
        String out = new FiboNodeImpl(4).toStringRecursive();
        System.out.println(out);
    }

    @Test
    public void testDepthDefault() {
        String out = new FiboNodeImpl().toStringRecursive();
        System.out.println(out);
    }

    @Test
    public void testStartWithFirst4Primes() {
        // This doesn't produce pythagorean triples but could be interesting
        String out = new FiboNodeImpl(new long[]{2, 3, 5, 7}).toStringRecursive();
        System.out.println(out);
    }

    @Test
    public void testValidateIsPythagoreanTripleRecursive() {
        boolean isValid = new FiboNodeImpl().validateIsPythagoreanTripleRecursive();
        assertTrue(isValid);
    }

    @Test
    public void testValidateIsPythagoreanTripleRecursiveStartWithFirst4Primes() {
        // I think it only generates pythagorean triples if the starting sequence is "Fibonacci-like".
        assertThrows(IllegalStateException.class,
                () -> new FiboNodeImpl(new long[]{2, 3, 5, 7}).validateIsPythagoreanTripleRecursive());
    }

    @Test
    public void testValidateIsPythagoreanTripleRecursiveStartWithDoubleDefault() {
        // If you double the initial input from the default 1, 1, 2, 3, you also get pythagorean triples.
        boolean isValid = new FiboNodeImpl(new long[]{2, 2, 4, 6}).validateIsPythagoreanTripleRecursive();
        assertTrue(isValid);
    }

    @Test
    public void testValidateIsPythagoreanTripleRecursiveStartWithArbitraryFiboLikeSequence() {
        // Start with an arbitrary sequence that satisfies V-U, U, V, V+U.
        // This gives you pythagorean triples.
        boolean isValid = new FiboNodeImpl(new long[]{29, 29, 58, 87}).validateIsPythagoreanTripleRecursive();
        assertTrue(isValid);
    }

}