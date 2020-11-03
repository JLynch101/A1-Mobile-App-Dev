package org.wit.expense.console.views

import org.wit.expense.console.models.ExpenseJSONStore
import org.wit.expense.console.models.ExpenseMemStore
import org.wit.expense.console.models.ExpenseModel
import com.github.mm.coloredconsole.ColoredConsole.Companion.BLACK
import com.github.mm.coloredconsole.ColoredConsole.Companion.BRIGHT_BLACK
import com.github.mm.coloredconsole.ColoredConsole.Companion.BRIGHT_WHITE
import com.github.mm.coloredconsole.ColoredConsole.Companion.RESET
import com.github.mm.coloredconsole.ColoredConsole.Companion.WHITE
import com.github.mm.coloredconsole.ColoredConsole.Style
import com.github.mm.coloredconsole.ColoredConsole.Style.NotApplied
import com.github.mm.coloredconsole.colored
import java.util.regex.Pattern

class ExpenseView {

    fun menu() : Int {

            var option: Int
            var input: String?
        colored {
            println("MAIN MENU".blue.bold)
            println(" 1. Add Expense".red.bold)
            println(" 2. Update Expense".green.bold)
            println(" 3. List All Expenses".green.bold)
            println(" 4. Search Expenses".green.bold)
            println(" 5. Delete Expense".blue.bold)
            println("-1. Exit".red.bold)
            println()
            print("Enter Option : ".yellow.bold)
        }
            input = readLine()!!
            option = if (input.toIntOrNull() != null && !input.isEmpty())
                input.toInt()
            else
                -9
            return option
        }

        fun listExpenses(expenses: ExpenseJSONStore) {
            colored {
            println("List All Expenses".blue.bold)}
            println()
            expenses.logAll()
            println()
        }

        fun showExpense(expense: ExpenseModel) {
            if (expense != null)
                println("Expense Details [ $expense ]")
            else
                println("Expense Not Found...")
        }

        fun addExpenseData(expense: ExpenseModel): Boolean {
            colored {
                println()
                print("Enter a Amount (€) : ".blue.bold)
                expense.amount = readLine()!!
                print("Enter a Description of Expense : ".blue.bold)
                expense.description = readLine()!!
            }
                return expense.amount.isNotEmpty() && expense.description.isNotEmpty()

        }

        fun updateExpenseData(expense: ExpenseModel): Boolean {

            var tempAmount: String?
            var tempDescription: String?

            if (expense != null) {
                colored {
                print("Enter a new amount for [ € ".blue.bold + expense.amount + " ] : ".blue.bold)}
                tempAmount = readLine()!!
                colored {
                print("Enter a new Description for [ ".blue.bold + expense.description + " ] : ".blue.bold)}
                tempDescription = readLine()!!

                if (!tempAmount.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                    expense.amount = tempAmount
                    expense.description = tempDescription
                    return true
                }
            }
            return false
        }

        fun getId(): Long {
            var strId: String? // String to hold user input
            var searchId: Long // Long to hold converted id
            colored {
                print("Enter id to Search/Update/Delete : ".green.bold)
            }
            strId = readLine()!!
            searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
                strId.toLong()
            else
                -9

            return searchId
        }

}
