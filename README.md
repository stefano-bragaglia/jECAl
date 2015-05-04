jECAl
=====

**jECAl** is a minimalistic Java framework to enable *ECA rules* (http://en.wikipedia.org/wiki/Event_condition_action) within Java code.
*Event-Condition-Action* (**ECA**) rules are *active rules* as used in *Event-driven* architectures (http://en.wikipedia.org/wiki/Event_driven_architecture) and *Active Databases* systems (http://en.wikipedia.org/wiki/Active_database).
They typically consists of three parts:
* the ***event***, which identifies the signal that triggers the invocation of the rule,
* the ***condition***, which is a logical test that, if satisfied, causes the execution of the rule,
* and the ***action***, which consists in the sequence of operations to be applied on the local data.

Please have a look at the use case provided as an example to get an intuition of the usefulness and the power of such kind of rules.
As you will see, this implementation takes advantage of `Annotation`s and dynamic `InvocationHandler`s, much like many well known *Aspect-oriented Programming* (**AOP**) frameworks (Spring AOP, Google Guice, etc.).
It was primarily intended for educational purposes so there is plenty of space for improvements; in particular, if you have constructive comments please reach out and let me know (rather than *it would have been better if you had used feature X and Y of framework Z*, because the idea since the beginning was to learn how these frameworks works). 

