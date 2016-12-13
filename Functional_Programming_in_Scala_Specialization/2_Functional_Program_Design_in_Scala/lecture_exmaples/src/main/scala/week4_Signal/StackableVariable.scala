package week4_Signal

/**
  * Created by mingdzhang on 12/12/16.
  */
class StackableVariable[T](init: T) {
  private var values: List[T] = List(init)
  def value: T = values.head
  def withValue[R](newValue: T)(op: => R): R = {
    values = newValue :: values
    try op finally values= values.tail
  }
}
