package com.dharmacode.pythagonacci;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PythagonacciBoxTest {

    @Test
    public void testDepth0() {
        PythagonacciBox box = PythagonacciBoxFactory.create(0);
        System.out.println(box.toStringRecursive());
    }

    @Test
    public void testDepth1() {
        PythagonacciBox box = PythagonacciBoxFactory.create(1);
        System.out.println(box.toStringRecursive());
    }

    @Test
    public void testDepth2() {
        PythagonacciBox box = PythagonacciBoxFactory.create(2);
        System.out.println(box.toStringRecursive());
    }

    @Test
    public void testDepth3() {
        PythagonacciBox box = PythagonacciBoxFactory.create(3);
        System.out.println(box.toStringRecursive());
    }

    @Test
    public void testDepth4() {
        PythagonacciBox box = PythagonacciBoxFactory.create(4);
        System.out.println(box.toStringRecursive());
    }

    @Test
    public void testDepthDefault() {
        PythagonacciBox box = PythagonacciBoxFactory.create();
        System.out.println(box.toStringRecursive());
    }

    @Test
    public void testValidateIsPythagoreanTripleRecursive() {
        PythagonacciBox box = PythagonacciBoxFactory.create();
        assertTrue(box.validateIsPythagoreanTripleRecursive());
    }

    @Test
    public void testValidateIsPythagoreanTripleRecursiveStartWithDoubleDefault() {
        // If you double the initial input from the default 1, 1, 2, 3, you also get Pythagorean triples.
        PythagonacciBox box = PythagonacciBoxFactory.create(new Long[]{2L, 2L, 4L, 6L});
        assertTrue(box.validateIsPythagoreanTripleRecursive());
    }

    @Test
    public void testValidateIsPythagoreanTripleRecursiveStartWithArbitraryFiboLikeSequence() {
        // Start with an arbitrary sequence that satisfies V-U, U, V, V+U.
        // This also gives you Pythagorean triples.
        PythagonacciBox box = PythagonacciBoxFactory.create(new Long[]{29L, 29L, 58L, 87L});
        assertTrue(box.validateIsPythagoreanTripleRecursive());
    }

    @Test
    public void testValidateIsPythagoreanTripleRecursiveStartWithFirst4Primes() {
        // It only generates Pythagorean triples if the starting sequence is "Fibonacci-like".
        PythagonacciBox box = PythagonacciBoxFactory.create(new Long[]{2L, 3L, 5L, 7L});
        assertThrows(IllegalStateException.class, box::validateIsPythagoreanTripleRecursive);
    }

}