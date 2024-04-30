package com.dharmacode.pythagonacci;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FiboNodeTest {

    @Test
    public void testDepth0() {
        FiboNode node = FiboNodeFactory.create(0);
        System.out.println(node.toStringRecursive());
    }

    @Test
    public void testDepth1() {
        FiboNode node = FiboNodeFactory.create(1);
        System.out.println(node.toStringRecursive());
    }

    @Test
    public void testDepth2() {
        FiboNode node = FiboNodeFactory.create(2);
        System.out.println(node.toStringRecursive());
    }

    @Test
    public void testDepth3() {
        FiboNode node = FiboNodeFactory.create(3);
        System.out.println(node.toStringRecursive());
    }

    @Test
    public void testDepth4() {
        FiboNode node = FiboNodeFactory.create(4);
        System.out.println(node.toStringRecursive());
    }

    @Test
    public void testDepthDefault() {
        FiboNode node = FiboNodeFactory.create();
        System.out.println(node.toStringRecursive());
    }

    @Test
    public void testValidateIsPythagoreanTripleRecursive() {
        FiboNode node = FiboNodeFactory.create();
        assertTrue(node.validateIsPythagoreanTripleRecursive());
    }

    @Test
    public void testValidateIsPythagoreanTripleRecursiveStartWithDoubleDefault() {
        // If you double the initial input from the default 1, 1, 2, 3, you also get pythagorean triples.
        FiboNode node = FiboNodeFactory.create(new Long[]{2L, 2L, 4L, 6L});
        assertTrue(node.validateIsPythagoreanTripleRecursive());
    }

    @Test
    public void testValidateIsPythagoreanTripleRecursiveStartWithArbitraryFiboLikeSequence() {
        // Start with an arbitrary sequence that satisfies V-U, U, V, V+U.
        // This also gives you pythagorean triples.
        FiboNode node = FiboNodeFactory.create(new Long[]{29L, 29L, 58L, 87L});
        assertTrue(node.validateIsPythagoreanTripleRecursive());
    }

    @Test
    public void testValidateIsPythagoreanTripleRecursiveStartWithFirst4Primes() {
        // It only generates pythagorean triples if the starting sequence is "Fibonacci-like".
        FiboNode node = FiboNodeFactory.create(new Long[]{2L, 3L, 5L, 7L});
        assertThrows(IllegalStateException.class, node::validateIsPythagoreanTripleRecursive);
    }

}