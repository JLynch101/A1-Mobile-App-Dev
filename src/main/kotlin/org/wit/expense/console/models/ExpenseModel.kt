package org.wit.expense.console.models

data class ExpenseModel(var id: Long? = 0,
                          var amount: String = "",
                          var description: String = "")
