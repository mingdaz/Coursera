package week4_Signal

import scala.util.DynamicVariable

/**
  * Created by mingdzhang on 12/12/16.
  */
class Signal[T](expr: => T){
  import Signal._
  private var myExpr: () => T = _
  private var myValue: T = _
  private var observers: Set[Signal[_]] = Set()
  update(expr)
//    def apply(): T = ???
  protected def update(expr: => T): Unit = {
    myExpr = () => expr
    computeValue()
  }
  protected def computeValue(): Unit = {
    val newValue = caller.withValue(this)(myExpr())
    if (myValue != newValue) {
      myValue = newValue
      val obs = observers
      observers = Set()
      obs.foreach(_.computeValue())
    }
  }

  def apply() = {
    observers += caller.value
    assert(!caller.value.observers.contains(this), "cyclic signal definition")
    myValue
  }


}

object NoSignal extends Signal[Nothing](???){
  override def computeValue() = ()
}

object Signal {
//  val caller = new StackableVariable[Signal[_]](NoSignal)
  val caller = new DynamicVariable[Signal[_]](NoSignal)

  def apply[T](expr: => T) = new Signal(expr)
}
