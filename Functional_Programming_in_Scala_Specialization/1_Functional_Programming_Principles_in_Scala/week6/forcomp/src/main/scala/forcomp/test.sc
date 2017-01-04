type Word = String
type Occurrences = List[(Char, Int)]

def wordOccurrences(w: Word): Occurrences =
  w.toLowerCase.groupBy((c:Char)=>c).toList.map{ case (p:Char,s:String)=>(p,s.length)}.sorted

wordOccurrences("abba"):::List(('a',1))


def combinations(occurrences: Occurrences): List[Occurrences] = {

    val b = combinations(occurrences.tail)
    val a = for {
      j<-1 to occurrences.head._2
      com <- b
    } yield com:::List((occurrences.head._1,j))

//    val b = for {
//      j<-1 to occurrences.head._2
//    } yield List((occurrences.head._1,j))

      b:::a.toList
//    :::b.toList

}

combinations(wordOccurrences("aabb"))

for {
  com <- List(wordOccurrences("ab"),wordOccurrences("aabb"))
} yield com