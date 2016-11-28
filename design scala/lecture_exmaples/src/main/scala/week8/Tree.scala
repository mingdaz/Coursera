package week8

/**
  * Created by mingdzhang on 11/27/16.
  */
trait Tree

case class Inner(left: Tree, right : Tree) extends Tree

case class Leaf(x: Int) extends Tree
