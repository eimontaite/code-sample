package adapters.postgresql.logical

import adapters.postgresql.{Filter, Operator}

object OrFilter extends AbstractLogicalFilter with Filter {
  def process(query: String): String = {
    val contentsRegex = "^OR\\((.+[\\(|=].+[\\)]*),(.+[\\(|=].+[\\)]*)\\)"
    toSql(regex = contentsRegex, query, operator = Operator.Or.toString)
  }
}
