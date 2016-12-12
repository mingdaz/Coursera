package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._
import Math._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = {
    for {
      a <- arbitrary[A]
      h <- oneOf(Gen.const(empty), genHeap)
    } yield insert(a,h)

  }
  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

//  property("gen1") = forAll { (h: H) =>
//    val m = if (isEmpty(h)) 0 else findMin(h)
//    findMin(insert(m, h)) == m
//  }

  property("hint1") = forAll { (a1: A, a2: A) =>
    val h = insert(a1,insert(a2, empty))
    findMin(h) == min(a1, a2)
  }

  property("hint2") = forAll { (a: A) =>
    isEmpty(deleteMin(insert(a,empty)))
  }

  property ("hint3") = forAll { (h: H) =>
    def isSorted(hr: H): Boolean = {
      if( isEmpty(hr)) true
      else {
        val head = findMin(hr)
        val tail = deleteMin(hr)
        isEmpty(tail) || (isSorted(tail) && head <= findMin(tail))
      }
    }
    isSorted(h)
  }

  property("hint4") = forAll { (h1: H, h2: H) =>
    findMin(meld(h1,h2)) == min(findMin(h1),findMin(h2))
  }

  property("meld") = forAll { (h1: H, h2: H) =>
    def heapEqual(h1: H, h2: H): Boolean =
      if (isEmpty(h1) && isEmpty(h2)) true
      else {
        val m1 = findMin(h1)
        val m2 = findMin(h2)
        m1 == m2 && heapEqual(deleteMin(h1), deleteMin(h2))
      }
    heapEqual(meld(h1, h2),
      meld(deleteMin(h1), insert(findMin(h1), h2)))
  }

}
