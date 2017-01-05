package reductions

import scala.annotation._
import org.scalameter._
import common._

object ParallelParenthesesBalancingRunner {

  @volatile var seqResult = false

  @volatile var parResult = false

  val standardConfig = config(
    Key.exec.minWarmupRuns -> 40,
    Key.exec.maxWarmupRuns -> 80,
    Key.exec.benchRuns -> 120,
    Key.verbose -> true
  ) withWarmer(new Warmer.Default)

  def main(args: Array[String]): Unit = {
    val length = 100000000
    val chars = new Array[Char](length)
    val threshold = 10000
    val seqtime = standardConfig measure {
      seqResult = ParallelParenthesesBalancing.balance(chars)
    }
    println(s"sequential result = $seqResult")
    println(s"sequential balancing time: $seqtime ms")

    val fjtime = standardConfig measure {
      parResult = ParallelParenthesesBalancing.parBalance(chars, threshold)
    }
    println(s"parallel result = $parResult")
    println(s"parallel balancing time: $fjtime ms")
    println(s"speedup: ${seqtime / fjtime}")
  }
}

object ParallelParenthesesBalancing {

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
   */
  def balance(chars: Array[Char]): Boolean = {
    var left = 0
    var idx = 0
    while(idx < chars.length && left >= 0){
      if(chars.apply(idx)=='(') left = left +1
      else if(chars.apply(idx)==')') left = left -1
      idx = idx + 1
    }
    left == 0
  }

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
   */
  def parBalance(chars: Array[Char], threshold: Int): Boolean = {

    def seqBalance(from:Int, until: Int, arg1: Int, arg2: Int): (Int,Int) = {
      var left = arg1
      var idx = from
      var min = arg2
      while(idx < until){
        if(chars.apply(idx)=='(') left = left +1
        else if(chars.apply(idx)==')') left = left -1
        idx = idx + 1
        if(left < min) min = left
      }
      (left,min)
    }

    def traverse(idx: Int, until: Int, arg1: Int, arg2: Int): (Int,Int) = {
      if(until-idx <= threshold )  seqBalance(idx,until,arg1,arg2)
      else {
        val mid = (idx + until) /2
        val (p1,p2) = parallel(traverse(idx,mid,arg1,0),traverse(mid,until,0,0))
        (p1._1 + p2._1,Integer.min(Integer.min(p1._1+p2._2,p1._2),arg2))
      }
    }

    def reduce(from: Int, until: Int) : Boolean = {
      val mid = (from + until) /2
      val (p1,p2) = parallel(traverse(from,mid,0,0),traverse(mid,until,0,0))
      (p1._1 + p2._1 == 0) && p1._2 == 0 && p1._1 + p2._2 >= 0
    }

    reduce(0, chars.length)
  }

  // For those who want more:
  // Prove that your reduction operator is associative!

}
