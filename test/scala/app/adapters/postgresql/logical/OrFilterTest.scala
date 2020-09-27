package scala.app.adapters.postgresql.logical

import adapters.postgresql.logical.OrFilter
import org.scalatest.funsuite.AnyFunSuite

class OrFilterTest extends AnyFunSuite {
  test("It should separate the arguments with the OR operator") {
    assert(OrFilter.process("OR(EQUAL(views,1),EQUAL(title,2))") === "EQUAL(views,1) OR EQUAL(title,2)")
  }

  test("It should separate the arguments with the OR operator after the query has already been processed by another filter") {
    assert(OrFilter.process("OR(views = 1,title = 2)") === "views = 1 OR title = 2")
  }

  test("It should separate the arguments with the OR operator when a NOT operator is provided") {
    assert(OrFilter.process("OR(NOT(views = 1),title = 2)") === "NOT(views = 1) OR title = 2")
  }
}
