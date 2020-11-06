//ExpenseMemStore is used to keep temporary inputs from the user
package org.wit.expense.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ExpenseMemStore : ExpenseStore {

    val expenses = ArrayList<ExpenseModel>()

    override fun findAll(): List<ExpenseModel> {
        return expenses
    }

    override fun findOne(id: Long) : ExpenseModel? {
        var foundExpense: ExpenseModel? = expenses.find { p -> p.id == id }
        return foundExpense
    }

    override fun create(expense: ExpenseModel) {
        expense.id = getId()
        expenses.add(expense)
        logAll()
    }

    override fun update(expense: ExpenseModel) {
        var foundExpense = findOne(expense.id!!)
        if (foundExpense != null) {
            foundExpense.orderno = expense.orderno
            foundExpense.amount = expense.amount
            foundExpense.description = expense.description
            foundExpense.date = expense.date

        }
    }

    override fun delete(expense: ExpenseModel) {
        expenses.remove(expense)
    }

    internal fun logAll() {
        expenses.forEach { logger.info("${it}") }
    }
}
