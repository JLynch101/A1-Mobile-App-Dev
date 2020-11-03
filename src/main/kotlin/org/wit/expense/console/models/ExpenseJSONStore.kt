package org.wit.expense.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.expense.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "expenses.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<ExpenseModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class ExpenseJSONStore : ExpenseStore {

    var expenses = mutableListOf<ExpenseModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<ExpenseModel> {
        return expenses
    }

    override fun findOne(id: Long) : ExpenseModel? {
        var foundExpense: ExpenseModel? = expenses.find { p -> p.id == id }
        return foundExpense
    }

    override fun create(expense: ExpenseModel) {
        expense.id = generateRandomId()
        expenses.add(expense)
        serialize()
    }

    override fun update(expense: ExpenseModel) {
        var foundExpense = findOne(expense.id!!)
        if (foundExpense != null) {
            foundExpense.amount = expense.amount
            foundExpense.description = expense.description
        }
        serialize()
    }

    override fun delete(expense: ExpenseModel) {
        expenses.remove(expense)
        serialize()
    }

    internal fun logAll() {
        expenses.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(expenses, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        expenses = Gson().fromJson(jsonString, listType)
    }
}
