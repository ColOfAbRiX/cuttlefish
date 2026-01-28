package com.colofabrix.scala.cuttlefish.api

import io.circe.parser.*
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scala.io.Source

class ConsumptionResponseSpec extends AnyWordSpec with Matchers {

  private def loadTestResource(name: String): String =
    Source.fromResource(name).mkString

  "MeterConsumptionResponse" should {

    "decode electricity consumption JSON" in {
      val json   = loadTestResource("electricity-meter-points_meter_consumption.json")
      val result = decode[MeterConsumptionResponse](json)

      result.isRight shouldBe true
      val response = result.toOption.get

      response.count shouldBe 1406
      response.next shouldBe Some(
        "https://api.octopus.energy/v1/electricity-meter-points/1012806962585/meters/Z15N213823/consumption/?page=2&page_size=100&period_from=2024-11-30T17%3A51%3A27.408332200%2B01%3A00&period_to=2024-12-30T17%3A51%3A27.418868%2B01%3A00",
      )
      response.previous shouldBe None
      response.results should not be empty
    }

    "decode gas consumption JSON" in {
      val json   = loadTestResource("gas-meter-points_meter_consumption.json")
      val result = decode[MeterConsumptionResponse](json)

      result.isRight shouldBe true
      val response = result.toOption.get

      response.results should not be empty
    }

    "parse consumption results correctly" in {
      val json   = loadTestResource("electricity-meter-points_meter_consumption.json")
      val result = decode[MeterConsumptionResponse](json)

      val response    = result.toOption.get
      val firstResult = response.results.head

      firstResult.consumption shouldBe 0.028
      firstResult.interval_start should not be null
      firstResult.interval_end should not be null
    }

    "parse multiple consumption intervals" in {
      val json   = loadTestResource("electricity-meter-points_meter_consumption.json")
      val result = decode[MeterConsumptionResponse](json)

      val response = result.toOption.get

      response.results.length shouldBe 100
      response.results.foreach { r =>
        r.consumption should be >= 0.0
      }
    }

  }

  "ConsumptionResults" should {

    "decode single consumption result" in {
      val json =
        """{"consumption": 0.028, "interval_start": "2024-12-29T23:30:00Z", "interval_end": "2024-12-30T00:00:00Z"}"""
      val result = decode[ConsumptionResults](json)

      result.isRight shouldBe true
      val consumption = result.toOption.get

      consumption.consumption shouldBe 0.028
    }

    "handle various consumption values" in {
      val json =
        """{"consumption": 1.234, "interval_start": "2024-12-29T00:00:00Z", "interval_end": "2024-12-29T00:30:00Z"}"""
      val result = decode[ConsumptionResults](json)

      result.isRight shouldBe true
      result.toOption.get.consumption shouldBe 1.234
    }

  }

}
