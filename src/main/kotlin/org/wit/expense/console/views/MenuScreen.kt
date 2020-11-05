package org.wit.expense.console.views

import javafx.application.Platform
import javafx.geometry.Orientation
import org.wit.expense.console.controllers.ExpenseUIController
import tornadofx.*

class MenuScreen : View("Expense Main Menu") {

    val expenseUIController: ExpenseUIController by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Expense") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        expenseUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List Expenses") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        expenseUIController.loadListScreen()
                    }
                }
            }
            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }


}
