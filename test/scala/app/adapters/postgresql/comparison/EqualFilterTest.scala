package scala.app.adapters.postgresql.comparison
import adapters.postgresql.comparison.EqualFilter
import org.scalatest.funsuite.AnyFunSuite

class EqualFilterTest extends AnyFunSuite {
  test("It should process the query when a parameter is a string") {
    assert(EqualFilter.process("EQUAL(title,\"first\")") === "title = \'first\'")
  }

  test("It should process the query when a parameter is an integer") {
    assert(EqualFilter.process("EQUAL(views,2)") === "views = 2")
  }

  test("It should process the query when a NOT operator is provided") {
    assert(EqualFilter.process("NOT(EQUAL(views,1))") === "NOT(views = 1)")
  }
}
