Produces a Fibonacci-Pythagorean tree that (given infinite computational capacity) produces all the primitive
pythagorean triples. It also produces all rational values in the range 0 < v < 1 if you take the left and the right
pairs in the nodes as fractions.

Demonstrates a little-known relationship between the Fibonacci sequence, Pythagorean triples and the rational numbers.

See the YouTube video from Mathologer below for background and more information such as additional insights into the
geometrical properties of the algorithm.
https://www.youtube.com/watch?v=94mV7Fmbx88&t=1978s

Starting with the first four numbers in the Fibonacci sequence, namely {1, 1, 2, 3}, we *curve* them into a 2x2 matrix:
```
1  1
3  2
```
The matrix can be summarised as follows:
```
V-U  U
V+U  V
```
We can apply three functions to these values to produce the first Pythagorean triple. If we represent the matrix as:
```
a b
d c
```
then the following functions will produce the first Pythagorean triple:

`(a*d)^2 + (2*b*c)^2 = (a*c + b*d)^2`

`(1*3)^2 + (2*(1*2))^2 = ((1*2)+(1*3))^2`

`3^2 + 4^2 = 5^2`

If you have watched the video then you may have noticed that the order of variables a, b, c and d has been slightly altered to assist with array indexing.

We then apply three different transformations to the matrix to produce three *child* nodes. Each of the three
transformations begin by selecting two values from the matrix as indicated by the O's shown here:
```
. O | . . | O .
O . | O O | . O
```
Having selected the values from the positions indicated by the O's in the starting matrix, we then have three new nodes
that have 2 out of 4 values populated:
```
. 1 | . . | 1 .
3 . | 3 2 | . 2
```
The values are then shifted to the top row if they are in the bottom row:
```
3 1 | 3 2 | 1 2
. . | . . | . .
```
Using the same rule as the first node, the remaining values can be populated:
```
3 1 | 3 2 | 1 2
5 4 | 7 5 | 5 3
```
Applying the functions to each of these child nodes, we get Pythagorean triples for each one!
`15^2 + 8^2 = 17^2`
`21^2 + 20^2 = 29^2`
`5^2 + 12^2 = 13^2`

Each child node can then be transformed respectively to generate three children of its own, and each child will always
produce a Pythagorean triple! Every single primitive Pythagorean triple will be produced exactly once, given infinite
resources.

Also, if you consider the values in each of the 2x2 matrices as two fractions (the following example showing the first
node as the two fractions 1/3 and 1/2):
```
1 1
- -
3 2
```
then the algorithm will produce every rational number 0 < n < 1 exactly once! (Given infinite resources)