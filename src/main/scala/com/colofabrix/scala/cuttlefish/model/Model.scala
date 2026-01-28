package com.colofabrix.scala.cuttlefish.model

/** Throttle value for rate limiting. */
opaque type Throttle = Double

object Throttle {
  extension (self: Throttle)
    def value: Double = self
  def apply(value: Double): Throttle = value
}

/** Meter Point Number (MPAN for electricity, MPRN for gas). */
opaque type MeterPointNumber = String

object MeterPointNumber {
  extension (self: MeterPointNumber)
    def value: String = self
  def apply(value: String): MeterPointNumber = value
}

/** Serial number of a meter. */
opaque type SerialNumber = String

object SerialNumber {
  extension (self: SerialNumber)
    def value: String = self
  def apply(value: String): SerialNumber = value
}

/** Octopus Energy account number (e.g., "A-1234ABCD"). */
opaque type AccountNumber = String

object AccountNumber {
  extension (self: AccountNumber)
    def value: String = self
  def apply(value: String): AccountNumber = value
}

/** Octopus Energy product code (e.g., "AGILE-FLEX-22-11-25"). */
opaque type ProductCode = String

object ProductCode {
  extension (self: ProductCode)
    def value: String = self
  def apply(value: String): ProductCode = value
}

/** Tariff code for a specific product and region (e.g., "E-1R-AGILE-FLEX-22-11-25-C"). */
opaque type TariffCode = String

object TariffCode {
  extension (self: TariffCode)
    def value: String = self
  def apply(value: String): TariffCode = value
}

/** UK postcode. */
opaque type Postcode = String

object Postcode {
  extension (self: Postcode)
    def value: String = self
  def apply(value: String): Postcode = value
}

/** Meter Point Administration Number for electricity meters. */
opaque type Mpan = String

object Mpan {
  extension (self: Mpan)
    def value: String = self
  def apply(value: String): Mpan = value
}
