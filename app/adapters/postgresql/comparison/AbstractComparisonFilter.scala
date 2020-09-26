package adapters.postgresql.comparison

import adapters.postgresql.Operator
import exceptions.InvalidArgumentException

trait AbstractComparisonFilter {
  def toSql(contentsRegex: String, replacementRegex: String, query: String, operator: String): String = {
    val argumentList = contentsRegex.r.findAllIn(query).toList
    validate(argumentList, operator)

    var newQuery = query.replaceAll(replacementRegex, "{placeholder}")

    argumentList.foreach { argument =>
      newQuery = newQuery.replaceFirst("\\{placeholder\\}", argument.replace(",", s" $operator "))
    }
    newQuery.replaceAll("\"", "\'")
  }

  def validate(argumentList: List[String], operator: String) = {
    val validationRegex = "\\w+,(\\d+)$"

    argumentList.map { argument =>
      operator match {
        case Operator.GreaterThan.toString | Operator.LessThan.toString if !argument.matches(validationRegex) =>
          throw new InvalidArgumentException(s"Failed to process a non-number value with a comparison operator, argument: $argument")
        case _ => None
      }
    }

  }
}
