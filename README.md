# CSC-454 HW-3B

Implement and simulate a network model of the function 
y(n+1) = y(n) XOR (x1(n) XOR x2(n))
where x1(n) and x2(n) are the two one-bit inputs to your network at tick n, and y(n) is the one-bit output of your network at tick n.

The model has the following characteristics:
The input to the network is coupled with the input to an XOR atomic model, XOR1.
The output of XOR1 is coupled with the input to another XOR atomic model, XOR2. 
The output of XOR2 is coupled with the output of the network. 
The output of XOR2 is also coupled with the input to a memory atomic model, M. 
The output of M is coupled with the input to XOR2. 
The output and state values of an XOR model are a single bit.
The input set of an XOR model contains two bits {b1, b2}.
The output function of an XOR model is lambda(s) = {s}
The state transition function of an XOR model is delta(s, {b1, b2}) = b1 XOR b2
The input and output of a memory model M are a single bit.
The state of M is a pair of bits (q1, q2).
The output function of M is lambda((q1, q2)) = {q1}
The state transition function of M is delta((q1, q2), {x}) = (q2, x)
Use the knowledge from the above and the previous homework to abstract away the common parts into a discrete time simulation framework. The framework should be able to operate on atomic and network models. Clients should be able to 1) control the rate at which time in the simulation advances; 2) be notified when any component, atomic or network, produces an output and when any atomic component changes its state; 3) inject input into a running simulation to support interactive simulations.

Remember to have the framework deal with the peculiarities of a network model correctly. I.e., for one input to the network you should be getting precisely one output after one machine cycle, no matter how many internal atomic cycles it takes to produce it.
