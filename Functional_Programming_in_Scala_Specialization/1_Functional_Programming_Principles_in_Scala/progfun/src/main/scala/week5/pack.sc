object packexe {
  val data = List("a","a","a","b","c","c","a")
  val num = List(1,2,3,4,5,6,7)

  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 => {
      val (first,rest) = xs span (y => y==x )
      first :: pack(rest)
    }
  }

  pack(data)
  def encode[T](xs: List[T]): List[(T,Int)] = {
    pack(xs) map ( ys => (ys.head, ys.length))
  }
  encode(data)

  def mapFun[T,U](xs: List[T], f: T=>U): List[U] =
    (xs foldRight List[U]())( f(_) :: _ )

  mapFun[List[String],(String,Int)](pack(data), c => (c.head,c.length))

  def lengthFun[T](xs: List[T]): Int =
    (xs foldRight 0)((_,y) => 1 + y )

  lengthFun(data)
}