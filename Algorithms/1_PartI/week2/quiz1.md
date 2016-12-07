1. Queue with two stacks. Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.
Hint: If you push elements onto a stack and then pop them all, they appear in reverse order. If you repeat this process, they're now back in order.

2. Stack with max. Create a data structure that efficiently supports the stack operations (push and pop) and also a return-the-maximum operation. Assume the elements are reals numbers so that you can compare them.
Hint: Use two stacks, one to store all of the items and a second stack to store the maximums.

3. Java generics. Explain why Java prohibits generic array creation.
Thank you for your response. 
Hint: to start, you need to understand that Java arrays are covariant but Java generics are not: that is, `String[]` is a subtype of `Object[]`, but `Stack<String>` is not a subtype of `Stack<Object>`.
