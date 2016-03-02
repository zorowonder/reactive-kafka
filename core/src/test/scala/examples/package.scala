import akka.actor.ActorSystem
import akka.stream.scaladsl.Sink

import scala.util.Failure

// Tools available for the examples
//
package object examples {
  def shutdownAsOnComplete[T](implicit as: ActorSystem) = Sink.onComplete[T] {
    case Failure(ex) =>
      println("Stream finished with error")
      ex.printStackTrace()
      as.terminate()
      println("Terminate AS.")
    case _ =>
      println("Stream finished successfully")
      as.terminate()
      println("Terminate AS.")
  }
}
