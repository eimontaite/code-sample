package adapters.postgresql.logical

import adapters.postgresql.{Filter, Operator}

object AndFilter extends AbstractLogicalFilter with Filter {
  def process(query: String): String = {
    val contentsRegex = "^AND\\((.+[\\(|=].+[\\)]*),(.+[\\(|=].+[\\)]*)\\)"
    toSql(regex = contentsRegex, query, operator = Operator.And.toString)
  }
}
