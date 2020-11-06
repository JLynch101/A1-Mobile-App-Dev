//ExpenseModel
package org.wit.expense.console.models

data class ExpenseModel(var id: Long? = 0,
                        var orderno: String = "",
                        var amount: String = "",
                        var description: String = "",
                        var date: String = "")
