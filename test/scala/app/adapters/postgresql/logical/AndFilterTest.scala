package scala.app.adapters.postgresql.logical

import adapters.postgresql.logical.AndFilter
import org.scalatest.funsuite.AnyFunSuite

class AndFilterTest extends AnyFunSuite {
  test("It should separate the arguments with the AND operator") {
    assert(AndFilter.process("AND(LESS_THAN(views,100),EQUAL(title,\"first\"))") === "LESS_THAN(views,100) AND EQUAL(title,\'first\')")
  }

  test("It should separate the arguments with the AND operator after the query has already been processed by another filter") {
    assert(AndFilter.process("AND(views = 1,title = \"first\")") === "views = 1 AND title = \'first\'")
  }
}
