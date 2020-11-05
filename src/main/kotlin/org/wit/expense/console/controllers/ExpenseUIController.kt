package org.wit.expense.console.controllers

import mu.KotlinLogging
import org.wit.expense.console.models.ExpenseJSONStore
import org.wit.expense.console.models.ExpenseModel
import org.wit.expense.console.views.AddExpenseScreen
import org.wit.expense.console.views.ListExpenseScreen
import org.wit.expense.console.views.MenuScreen
import tornadofx.*

class ExpenseUIController : Controller() {

    val expenses = ExpenseJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Expense TornadoFX UI App" }
    }
    fun add(_orderno : String, _amount : String, _description : String, _date : String){

        var aExpense = ExpenseModel(orderno = _orderno, amount = _amount, description = _description, date = _date)
        expenses.create(aExpense)
        logger.info("Expense Added")
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListExpenseScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        expenses.logAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddExpenseScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeAdd() {
        runLater {
            find(AddExpenseScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeList() {
        runLater {
            find(ListExpenseScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

}
