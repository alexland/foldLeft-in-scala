

#### fold _versus_ reduce _versus_ scan

in order ascending order of generality:

reduce -> scan -> fold


to _reduce_, you pass in an operator that describes the successive computation performed on the members of the sequence reduce is called on




fold, which is a curried methods takes two argument lists:

  - a container to hold the result

  - an operator, or function that describes the successive computation performed on the members of the sequence fold is called on


suppose the operator is addition--ie, you just want to traverse some collection summing the items as you do so

using fold, you can


#### fold _versus_ foldLeft _versus_ foldRight

fold: order of operations is unspecified and "may be nondeterministic" according to the Scaladoc

whether any of the three will give a result that differ from the other depends on the operator passed in--ie, is it _commutative_
