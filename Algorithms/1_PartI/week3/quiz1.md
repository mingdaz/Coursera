1. 
Merging with smaller auxiliary array. Suppose that the subarray 𝚊[𝟶] to 𝚊[𝚗−𝟷] is sorted and the subarray 𝚊[𝚗] to 𝚊[𝟸∗𝚗−𝟷] is sorted. How can you merge the two subarrays so that 𝚊[𝟶] to 𝚊[𝟸∗𝚗−𝟷] is sorted using an auxiliary array of length n (instead of 2n)?

Note: these interview questions are ungraded and purely for your own enrichment. To get a hint, submit a solution.

move first half to the aux arrary merge aux and second half to a
Thank you for your response. 
Hint: copy only the left half into the auxiliary array.
Correct
1 / 1 points
2. 
Counting inversions. An inversion in an array a[] is a pair of entries a[i] and a[j] such that i<j but a[i]>a[j]. Given an array, design a linearithmic algorithm to count the number of inversions.

hmm
Thank you for your response. 
Hint: count while mergesorting.
Correct
1 / 1 points
3. 
Shuffling a linked list. Given a singly-linked list containing n items, rearrange the items uniformly at random. Your algorithm should consume a logarithmic (or constant) amount of extra memory and run in time proportional to nlogn in the worst case.

hmm
Thank you for your response. 
Hint: design a linear-time subroutine that can take two uniformly shuffled linked lists of sizes n1 and n2 and combined them into a uniformly shuffled linked lists of size n1+n2.
