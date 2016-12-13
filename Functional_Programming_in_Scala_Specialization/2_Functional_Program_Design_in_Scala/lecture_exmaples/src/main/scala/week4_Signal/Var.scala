package week4_Signal

/**
  * Created by mingdzhang on 12/12/16.
  */
class Var[T](expr: => T) extends Signal[T](expr) {
 override def update(expr: => T): Unit = super.update(expr)
}

object Var {
  def apply[T](expr: => T) = new Var(expr)
}