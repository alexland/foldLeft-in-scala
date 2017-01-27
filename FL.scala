

import util.{
  Try,
  Success,
  Failure
}

import spire.implicits._
import spire.math._
import spire.algebra._
import spire.syntax.literals._


/**
 * -----------------item frequency ------------------
 * (polymorphic) frequency of each unique item
 * a container
 * folds over a Seq, creating a key in a Map
 * for each unique value found in Seq passed in
 * for items for which there is already a key,
 * just increments the key by +1
 *
 * @param     q     Seq of items
 * @tparam    A
 * @return    Map whose keys are unique items in q,
 *            values are counts
 *
 */
def f[A](q:Seq[A]):Map[A,Int] = {
  q.foldLeft(Map[A,Int]()) {
    case (u,v) => u.updated(v, u.getOrElse(v, 0) + 1)
  }
}



/**
 *----------------- membership ----------------
 * for instance, given a list of tags a user follows (q)
 * scan a given message (s), and for each word that is in the
 * user's tag list, push that message to user's feed
 *
 * @param   q
 *              a sequence of Strings,
 * @param   s   a single string, eg, a message comprised
 *              of words
 * @return      Boolean, ie, is any word from the message s
 *              in the word list, q?
 *
 */
def isMember(q:Seq[String], s:String):Boolean = {
  q.foldLeft(false)( (u, v) => {
    u || s.contains(v)
  })
}


/**
 * selects one value per offset from among
 * three (right, left, or middle) streams
 * ie, three items in a tuple
 * @param     t     sequence of Ints
 * @param     u     sequence of Ints
 * @param     v     sequence of Ints
 *
 * @returns   list of values--at each offset,
 *            one value selected from one of
 *            of three sequences at the given offset
 *
 *
 */
def select(t:Seq[Int], u:Seq[Int], v:Seq[Int]):List[Int] = {
  def f[T, U, V](t:((T, U), V)) = {
    (t._1._1, t._1._2, t._2)
  }
  val q = t.view.zip(u).zip(v).map(f)
  q.foldLeft(List[Int]()) { (u,v) =>
    v match {
      case v1 if ((v._1 > v._2) && (v._1 > v._3)) => u :+ v._1
      case v2 if ((v._2 > v._1) && (v._2 > v._3)) => u :+ v._2
      case _ => u :+ v._3
    }
  }
}



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
 * 	using the newly injected method 'doubleToInt'
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
