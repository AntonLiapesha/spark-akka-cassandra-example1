package com.aliapesha.testtask.akka

import java.util.UUID

import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.stream.Materializer
import com.aliapesha.testtask.akka.service.HouseStatsService
import com.aliapesha.testtask.core.entity.HouseStat
import com.typesafe.config.Config
import org.json4s.DefaultJsonFormats
import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, JsonFormat}

import scala.concurrent.ExecutionContextExecutor
import scala.util.Success

trait Protocols extends DefaultJsonProtocol with DefaultJsonFormats {
  implicit object UUIDFormat extends JsonFormat[UUID] {
    def write(uuid: UUID) = JsString(uuid.toString)

    def read(value: JsValue) = {
      value match {
        case JsString(uuid) => UUID.fromString(uuid)
        case _ => throw new DeserializationException("Expected hexadecimal UUID string")
      }
    }
  }

  implicit val houseStatFormat = jsonFormat15(HouseStat.apply)

}

trait Service extends Protocols {
  implicit val system: ActorSystem
  implicit val materializer: Materializer
  val logger: LoggingAdapter
  val routes = {
    get {
      pathPrefix("stat" / "sample") {
        val statF = HouseStatsService.sample()
        onComplete(statF) {
          case Success(item) if item.isDefined => complete(item.get)
          case _ => complete(StatusCodes.NotFound -> "Not found")
        }
      }
    }
  }

  implicit def executor: ExecutionContextExecutor

  def config: Config

}
