package adapters.postgresql.logical

trait AbstractLogicalFilter {
  def toSql(regex: String, query: String, operator: String): String = {
    if (query.matches(regex)) {
      val argumentList = regex.r.findAllIn(query).subgroups
      (argumentList(0) + s" $operator " + argumentList(1)).replaceAll("\"", "\'")
    } else {
      query
    }
  }
}
