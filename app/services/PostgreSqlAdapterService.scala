package services

import adapters.postgresql.Filter
import adapters.postgresql.comparison.{EqualFilter, GreaterThanFilter, LessThanFilter}
import adapters.postgresql.logical.{AndFilter, OrFilter}

object PostgreSqlAdapterService extends Filter {
  def process(query: String): String = {
    val chainedFilters = OrFilter.process _ andThen
      GreaterThanFilter.process _ andThen
      LessThanFilter.process _ andThen
      AndFilter.process _ andThen
      EqualFilter.process _

    chainedFilters(query)
  }
}
