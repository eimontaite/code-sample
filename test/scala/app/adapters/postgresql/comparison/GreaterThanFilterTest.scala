package scala.app.adapters.postgresql.comparison

import adapters.postgresql.comparison.GreaterThanFilter
import exceptions.InvalidArgumentException
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class GreaterThanFilterTest extends AnyFunSuite with Matchers {
  test("It should process the query when a parameter is an integer") {
    assert(GreaterThanFilter.process("GREATER_THAN(views,1)") === "views > 1")
  }

  test("It should throw an exception when a parameter is not an integer") {
    an[InvalidArgumentException] should be thrownBy GreaterThanFilter.process("GREATER_THAN(views,\"first\")")
  }
}
