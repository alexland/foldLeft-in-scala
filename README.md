

### fold _versus_ reduce _versus_ scan _versus_ aggregate

in ascending order of generality:

reduce -> scan -> fold -> aggregate


to _reduce_, you pass in

conceptually, each of these three methods requires _three_ objects to generate a result:

  - an _accumulator_, "container" that holds each intermediate result;

  - an _operator_ a binary operator that describes the successive computation performed on the members of the sequence that the method is called on

  - an _initiator_, a "primer" value that is passed, along with the first item in the sequence, to the very first operator call (without which the operator couldn't be called given that it requires two parameters)



among reduce, scan, and fold, there is no distinction with respect to the operator, the differences reside in the accumulator and the initiator

first _reduce_: neither its accumulator or initiator are in the method's parameter list; instead both are hard-coded into the method.

the _type_ of accumulator (in _reduce_) is a member of some Scala Value class (ie, not a Reference class) and that class is determined by the common type of the elements comprising the sequence to be reduced.

eg

```scala

@> val q:Seq[Int] = Seq(7, 4, 9, 8, 3, 5)

```

the accumulator type is Int; again, this is determined solely by the parameter type of the sequence to be reduced

the _initiator's_ type, in _reduce_, is identical to the _accumulator_ type

the _initiator's_ value is _zero_ and cannot be changed


its accumulator is 0; that value is not a default argument, it's not even in the parameter list--it's hard-coded into the method.

```scala

@> val q = List(4, 7, 5)

@> val res = q.reduce(_ + _)

@> println(res)
16  

```

this is equivalent to

```scala

@> var acc = 0

@> for (elem <- q) acc += elem

@> print(acc)
16

```

reduce, scan, and fold, each operate by one-at-a-time traversal over a sequence, so the first calculation in the example above _cannot_ be 4 + 7, because that requires the first two items in the sequence--again, only one element at a time.







fold, which is a curried methods takes two argument lists:

  - a container to hold the result

  - an operator, or function that describes the successive computation performed on the members of the sequence fold is called on


suppose the operator is addition--ie, you just want to traverse some collection summing the items as you do so

using fold, you can




_reduce_

  * accumulator
    - method parameter? no
    - type: Value class, determined by sequence elements' common type

  * initiator
    - method parameter? no
    - type: Value class, determined by sequence elements' common type


_scan_

  * accumulator
    - method parameter list? no
    - type: Reference class, determined by sequence type

  * initiator
    - in method parameter list? yes
    - type: Value class, must be sequence elements' common type


_fold_

  * accumulator
    - method parameter? yes
    - type: Value class, determined by sequence elements' common type

  * initiator
    - method parameter? yes (acc can be passed in with initial value)
    - type: Value class, specified when parameterized accumulator is passed in


### more generality still via _accumulate_






### fold _versus_ foldLeft _versus_ foldRight

fold: order of operations is unspecified and "may be nondeterministic" according to the Scaladoc

whether any of the three will give a result that differ from the other depends on the operator passed in--ie, is it _commutative_ and is it _associative_

  - if _op_ is both _commutative_ and _associative_, three results are same

  - if _op_ is

  - if _op_
