package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = if(c==0||r==c) 1 else pascal(c-1,r-1) + pascal(c,r-1)

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    def Parentheses(left: Int, sub: List[Char]): Boolean = {
      if (sub.isEmpty) left == 0
      else if(sub.head == '(') Parentheses(left+1,sub.tail)
      else if(sub.head == ')') {
        if (left<1) false
        else Parentheses(left-1,sub.tail)
      }
      else Parentheses(left,sub.tail)
    }
    Parentheses(0,chars)
  }


  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    var ret = 0
    var remain = money
    if(!coins.isEmpty&&remain>0){
      ret = ret + countChange(remain,coins.tail)
      while(remain > coins.head){
        remain = remain - coins.head
        ret = ret + countChange(remain,coins.tail)
      }
      if(remain==coins.head) ret = ret + 1
    }
    return ret
  }

}
