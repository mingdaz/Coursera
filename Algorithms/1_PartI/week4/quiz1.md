Correct
1 / 1 points
1. 
Dynamic median. Design a data type that supports insert in logarithmic time, find-the-median in constant time, and remove-the-median in logarithmic time.
Note: these interview questions are ungraded and purely for your own enrichment. To get a hint, submit a solution.
Thank you for your response. 
Hint: maintain two binary heaps, one that is max-oriented and one that is min-oriented.
Correct
1 / 1 points
2. 
Randomized priority queue. Describe how to add the methods 𝚜𝚊𝚖𝚙𝚕𝚎() and 𝚍𝚎𝚕𝚁𝚊𝚗𝚍𝚘𝚖() to our binary heap implementation. The two methods return a key that is chosen uniformly at random among the remaining keys, with the latter method also removing that key. The 𝚜𝚊𝚖𝚙𝚕𝚎() method should take constant time; the 𝚍𝚎𝚕𝚁𝚊𝚗𝚍𝚘𝚖() method should take logarithmic time. Do not worry about resizing the underlying array.
Thank you for your response. 
Correct
1 / 1 points
3. 
Taxicab numbers. A taxicab number is an integer that can be expressed as the sum of two cubes of integers in two different ways: a3+b3=c3+d3. For example, 1729=93+103=13+123. Design an algorithm to find all taxicab numbers with a, b, c, and d less than n.
Version 1: Use time proportional to n2logn and space proportional to n2.
Version 2: Use time proportional to n2logn and space proportional to n.
Thank you for your response. 
Hints:
Version 1: Form the sums a3+b3 and sort.
Version 2: Use a min-oriented priority queue with n items.


