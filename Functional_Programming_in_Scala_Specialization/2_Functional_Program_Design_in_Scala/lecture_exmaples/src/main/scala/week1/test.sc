/**
  * Created by mingdzhang on 11/21/16.
  */
//package week8

object test{
  println("Welcome to the Scala worksheet")

  val f: PartialFunction[String,String] = {case "ping" => "pang"}

  f("ping")
  //  f("abc")

  f.isDefinedAt("abc")
  f.isDefinedAt("ping")

}