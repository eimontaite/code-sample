package scala.app.adapters.postgresql.comparison

import adapters.postgresql.comparison.LessThanFilter
import exceptions.InvalidArgumentException
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

class LessThanFilterTest extends AnyFunSuite with Matchers {
  test("It should process the query when a parameter is an integer") {
    assert(LessThanFilter.process("LESS_THAN(views,1)") === "views < 1")
  }

  test("It should throw an exception when a parameter is not an integer") {
    an[InvalidArgumentException] should be thrownBy LessThanFilter.process("LESS_THAN(views,\"first\")")
  }
}
