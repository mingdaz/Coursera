3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes time proportional to n2 in the worst case. You may assume that you can sort the n integers in time proportional to n2 or better.

Hint: given an integer 𝚡 and a sorted array 𝚊[] of n distinct integers, design a linear-time algorithm to determine if there exists two distinct indices 𝚒 and 𝚓 such that 𝚊[𝚒]+𝚊[𝚓]==𝚡.

Search in a bitonic array. An array is bitonic if it is comprised of an increasing sequence of integers followed immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of n distinct integer values, determines whether a given integer is in the array.

Standard version: Use ∼3lgn compares in the worst case.
Signing bonus: Use ∼2lgn compares in the worst case (and prove that no algorithm can guarantee to perform fewer than ∼2lgn compares in the worst case).

Hints: Standard version. First, find the maximum integer using ∼1lgn compares—this divides the array into the increasing and decreasing pieces.

Signing bonus. Do it without finding the maximum integer.

Egg drop. Suppose that you have an n-story building (with floors 1 through n) and plenty of eggs. An egg breaks if it is dropped from floor T or higher and does not break otherwise. Your goal is to devise a strategy to determine the value of T given the following limitations on the number of eggs and tosses:

Version 0: 1 egg, ≤T tosses.
Version 1: ∼1lgn eggs and ∼1lgn tosses.
Version 2: ∼lgT eggs and ∼2lgT tosses.
Version 3: 2 eggs and ∼2n√ tosses.
Version 4: 2 eggs and ≤cT‾‾√ tosses for some fixed constant c.
Hints:

Version 0: sequential search.
Version 1: binary search.
Version 2: find an interval containing T of size ≤2T, then do binary search.
Version 3: find an interval of size n‾‾√, then do sequential search. Note: can be improved to ∼2n‾‾‾√ tosses.
Version 4: 1+2+3+…+t∼12t2. Aim for c=22‾‾√.
