

import util.{
  Try,
  Success,
  Failure
}

import spire.implicits._
import spire.math._
import spire.algebra._
import spire.syntax.literals._




//------------------------ item frequency -------------------------//


/**
	* (polymorphic) frequency of each unique item
  * a container
	* folds over a Seq, creating a key in a Map
	* for each unique value found in Seq passed in
	* for items for which there is already a key,
	* just increments the key by +1
	*
	*	@param q Seq of items
	*	@tparam A
	* @return a Map whose keys are unique items in q, values are counts

	*
  */
def f[A](q:Seq[A]):Map[A,Int] = {
  q.foldLeft(Map[A,Int]()) {
    case (u,v) => u.updated(v, u.getOrElse(v, 0) + 1)
  }
}



//-------------------------- more -------------------- //

/**
*	a list of tuples, (qty, price_paid) is folded
*	over to calculate profit
*
*/
def f(q:List[(Int,Double)], vf:Double):Double = {
	q.foldLeft(0.0) {
		case (acc, (u, v)) => acc + (vf - v) * u
	}
}


//-------------------- cumulative sum -------------------- //





//------------------------- fizzbuzz ---------------------------//


def f[Int](q:Seq[Int]=(1 to 100)):Seq[String] = {
  q.foldLeft(Seq[String]())( (u, v) =>
    v match {
      case v if ((v % 3 == 0) && (v % 5 == 0)) => u :+ "fizzbuzz"
      case v if (v % 3 == 0) => u :+ "fizz"
      case v if (v % 5 == 0) => u :+ "buzz"
      case v => u :+ v.toString
    }
  )
}



//------------- longest increasing subsequence -------------//

/**
	*	finds longest increasing subsequence
	*	w/in a Seq of Ints
	*/
def longIncrSubseq(q:Seq[Int]):Seq[Int] = {
	q.foldLeft(Seq[Int]()) {
		(u,v) => if (v > u.headOption.getOrElse(0)) v +: u else 0 +: u
	}
	.reverse
	.mkString(" ")
	.split("0")
	.map(_.trim)
	.filterNot(_ == "")
	.map(_.split(" "))
	.sortWith(_.length > _.length).head
	.map(_.toInt)
	.toSeq
}

/**
*	supports longIncrSubseq
*	method injected in Scala's String class
*	only purpose is to allow String replacement
*	via the chained call syntax in LongIncrSubseq
*	using the newly injected method 'doubleToInt'
*	import Numeric.Implicits._
*
*/
import Numeric.Implicits._

implicit class StringEnhancement(s:String) {
  def doubleToInt = {
    val p = new Regex("[.][0-9]+")
    p.replaceAllIn(s, "")
  }
}
