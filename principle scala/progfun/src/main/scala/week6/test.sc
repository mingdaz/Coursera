//package week6

object test {

  val xs = Array(1,2,3,44)
  xs map (x => x*2)

  val s = "Hello World"
  s filter (c => c.isUpper)

  s exists ( c => c.isUpper)
  s forall ( c => c.isUpper )
}