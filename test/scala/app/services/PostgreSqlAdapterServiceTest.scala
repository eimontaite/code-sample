package scala.app.services

import org.scalatest.funsuite.AnyFunSuite
import services.PostgreSqlAdapterService

class PostgreSqlAdapterServiceTest extends AnyFunSuite {
  test("It should process the query by placing logical and comparison operators") {
    assert(PostgreSqlAdapterService.process("OR(EQUAL(views,1),EQUAL(title,\"first\"))") === "views = 1 OR title = \'first\'")
  }

  test("It should process the query by placing logical and comparison operators when a NOT operator is provided") {
    assert(PostgreSqlAdapterService.process("AND(NOT(GREATER_THAN(views,1)),EQUAL(title,\"first\"))") === "NOT(views > 1) AND title = \'first\'")
  }
}
