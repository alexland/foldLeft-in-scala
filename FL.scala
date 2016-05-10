

//------------------------ item frequency -------------------------//


/**
	* (polymorphic) frequency of each unique item
  * a container
	* folds over a list, creating a key in a Map
	* for each unique value found in List passed in
	* for items for which there is already a key,
	* just increments the key by +1
	*
	*	@param q list of items
	*	@tparam A
	* @return a Map whose keys are unique items in q, values are counts

	*
  */
def f[A](q:List[A]):Map[A,Int] = {
  q.foldLeft(Map[A,Int]()) {
    case (u,v) => u.updated(v, u.getOrElse(v, 0) + 1)
  }
}


//------------- longest increasing subsequence -------------//

/**
	*	finds longest increasing subsequence
	*	w/in a list of Ints
	*/
def longIncrSubseq(q:List[Int]):List[Int] = {
	q.foldLeft(List[Int]()) {
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
	.toList
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
