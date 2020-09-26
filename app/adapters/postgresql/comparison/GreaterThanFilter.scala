package adapters.postgresql.comparison

import adapters.postgresql.{Filter, Operator}


object GreaterThanFilter extends AbstractComparisonFilter with Filter {
  def process(query: String): String = {
    val contentsRegex = ("(?<=GREATER_THAN\\()([^\\)]+)")
    val replacementRegex = "GREATER_THAN\\(([^\\)]+)\\)"

    toSql(contentsRegex, replacementRegex, query, operator = Operator.GreaterThan.toString)
  }
}
