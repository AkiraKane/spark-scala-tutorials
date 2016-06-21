package com.pinecone.tutorials.scala4basic.jsonparse

import play.api.libs.json.Json._
import play.api.libs.json._

/**
  * Created by ivan on 2016/6/10.
  */
object JsonParse {
  def main(args: Array[String]) {

    val json01: JsValue = parse(
      """
{
  "name" : "Watership Down",
  "location" : {
    "lat" : 51.235685,
    "long" : -1.309197
  },
  "residents" : [ {
    "name" : "Fiver",
    "age" : 4,
    "role" : null
  }, {
    "name" : "Bigwig",
    "age" : 6,
    "role" : "Owsla"
  } ]
}
      """)
    val json02: JsValue = JsObject(Seq(
      "name" -> JsString("Watership Down"),
      "location" -> JsObject(Seq("lat" -> JsNumber(51.235685), "long" -> JsNumber(-1.309197))),
      "residents" -> JsArray(Seq(
        JsObject(Seq(
          "name" -> JsString("Fiver"),
          "age" -> JsNumber(4),
          "role" -> JsNull
        )),
        JsObject(Seq(
          "name" -> JsString("Bigwig"),
          "age" -> JsNumber(6),
          "role" -> JsString("Owsla")
        ))
      ))
    ))
    val json03: JsValue = obj(
      "name" -> "Watership Down",
      "location" -> obj("lat" -> 51.235685, "long" -> -1.309197),
      "residents" -> arr(
        obj(
          "name" -> "Fiver",
          "age" -> 4,
          "role" -> JsNull
        ),
        obj(
          "name" -> "Bigwig",
          "age" -> 6,
          "role" -> "Owsla"
        )
      )
    )
    // basic types
    val jsonString = Json.toJson("Fiver")
    val jsonNumber = Json.toJson(4)
    val jsonBoolean = Json.toJson(false)

    // collections of basic types
    val jsonArrayOfInts = Json.toJson(Seq(1, 2, 3, 4))
    val jsonArrayOfStrings = Json.toJson(List("Fiver", "Bigwig"))
    case class Location(lat: Double, long: Double)
    case class Resident(name: String, age: Int, role: Option[String])
    case class Place(name: String, location: Location, residents: Seq[Resident])
    import play.api.libs.json._

    implicit val locationWrites = new Writes[Location] {
      def writes(location: Location) = Json.obj(
        "lat" -> location.lat,
        "long" -> location.long
      )
    }

    implicit val residentWrites = new Writes[Resident] {
      def writes(resident: Resident) = Json.obj(
        "name" -> resident.name,
        "age" -> resident.age,
        "role" -> resident.role
      )
    }

    implicit val placeWrites = new Writes[Place] {
      def writes(place: Place) = Json.obj(
        "name" -> place.name,
        "location" -> place.location,
        "residents" -> place.residents)
    }

    val place = Place(
      "Watership Down",
      Location(51.235685, -1.309197),
      Seq(
        Resident("Fiver", 4, None),
        Resident("Bigwig", 6, Some("Owsla"))
      )
    )

    val json = Json.toJson(place)
  }
}
