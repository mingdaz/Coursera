package week4

import java.time.temporal.TemporalAmount

import scala.collection.mutable

/**
  * Created by mingdzhang on 12/12/16.
  */


class BankAccount extends Publisher {
  private var balance = 0
  def currentBalance: Int = balance
  def deposit(amount: Int): Unit =
    if(amount>0) {
      balance = balance + amount
      publish()
    }
  def withdraw(amount: Int): Unit =
    if(0< amount && amount <= balance){
      balance = balance - amount
      publish()
    } else throw new Error("insufficient funds")
}
