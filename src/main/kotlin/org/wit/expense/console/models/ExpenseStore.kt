package org.wit.expense.console.models

interface ExpenseStore {
    fun findAll(): List<ExpenseModel>
    fun findOne(id: Long): ExpenseModel?
    fun create(expense: ExpenseModel)
    fun update(expense: ExpenseModel)
    fun delete(expense: ExpenseModel)
}