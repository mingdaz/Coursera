package week4

/**
  * Created by mingdzhang on 12/12/16.
  */
trait Subscriber {
  def handler(pub:Publisher)
}
