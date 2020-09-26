package adapters.postgresql.comparison

import adapters.postgresql.{Filter, Operator}

object LessThanFilter extends AbstractComparisonFilter with Filter {
  def process(query: String): String = {
    val contentsRegex = ("(?<=LESS_THAN\\()([^\\)]+)")
    val replacementRegex = "LESS_THAN\\(([^\\)]+)\\)"

    toSql(contentsRegex, replacementRegex, query, operator = Operator.LessThan.toString)
  }
}
