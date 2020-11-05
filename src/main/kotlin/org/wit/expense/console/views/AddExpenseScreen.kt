package org.wit.expense.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.expense.console.controllers.ExpenseUIController
import tornadofx.*
import kotlin.reflect.KClass

class AddExpenseScreen : View("Add Expense") {
    val model = ViewModel()
    val _orderno = model.bind { SimpleStringProperty() }
    val _amount = model.bind { SimpleStringProperty() }
    val _description = model.bind { SimpleStringProperty() }
    val _date = model.bind { SimpleStringProperty() }
    val expenseUIController: ExpenseUIController by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Order Number") {
                textfield(_orderno).required()
            }
            field("Amount") {
                textfield(_amount).required()
            }
            field("Description") {
                textarea(_description).required()
            }
            field("Date") {
                textfield(_date).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        expenseUIController.add(_orderno.value,_amount.value,_description.value,_date.value)

                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        expenseUIController.closeAdd()
                    }
                }
            }
        }
    }

    override fun onDock() {
        _orderno.value = ""
        _amount.value = ""
        _description.value = ""
        _date.value = ""
        model.clearDecorators()
    }
}

