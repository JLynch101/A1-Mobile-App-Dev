package org.wit.expense.console.views


import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import org.wit.expense.console.controllers.ExpenseUIController
import org.wit.expense.console.models.ExpenseModel
import tornadofx.*

class ListExpenseScreen : View("List Expenses") {

    val expenseUIController: ExpenseUIController by inject()
    val tableContent = expenseUIController.expenses.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", ExpenseModel::id)
            readonlyColumn("Order Number", ExpenseModel::orderno)
            readonlyColumn("Amount", ExpenseModel::amount)
            readonlyColumn("Description", ExpenseModel::description)
            readonlyColumn("Date", ExpenseModel::date)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    expenseUIController.closeList()
                }
            }
        }
    }

}
