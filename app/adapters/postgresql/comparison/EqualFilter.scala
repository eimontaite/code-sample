package adapters.postgresql.comparison

import adapters.postgresql.{Filter, Operator}

object EqualFilter extends AbstractComparisonFilter with Filter {
  def process(query: String): String = {
    val contentsRegex = ("(?<=EQUAL\\()([^\\)]+)")
    val replacementRegex = "EQUAL\\(([^\\)]+)\\)"

    toSql(contentsRegex, replacementRegex, query, operator = Operator.Equal.toString)
  }
}
