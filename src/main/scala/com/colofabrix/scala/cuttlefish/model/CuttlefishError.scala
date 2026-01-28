package com.colofabrix.scala.cuttlefish.model

import io.circe.*
import io.circe.derivation.*

/**
 * Base exception class for all Cuttlefish errors.
 *
 * @param message The error message describing what went wrong
 * @param inner Optional underlying cause of the error
 */
class CuttlefishError(message: String, inner: Option[Throwable] = None) extends Throwable(message):
  inner.foreach(super.addSuppressed)

/**
 * Error returned by the Octopus Energy API.
 *
 * @param detail The error detail message from the API response
 */
final case class CuttlefishRequestError(detail: String) extends CuttlefishError(detail) derives Decoder
