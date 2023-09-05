package com.example.advancedcalculatornotworking

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextResult: EditText
    private var currentNumber: Double? = null
    private var previousNumber: Double? = null
    private var currentOperation: String? = null
    private var memory: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextResult = findViewById(R.id.editTextResult)
        setupNumberButtons()
        setupOperationButtons()
    }

    private fun setupNumberButtons() {
        val numberButtons = listOf(
            R.id.Zero, R.id.One, R.id.Two, R.id.Three,
            R.id.Four, R.id.Five, R.id.Six, R.id.Seven,
            R.id.Eight, R.id.Nine, R.id.Dot
        )

        for (id in numberButtons) {
            findViewById<Button>(id).setOnClickListener { view ->
                val button = view as Button
                editTextResult.append(button.text)
            }
        }
    }

    private fun setupOperationButtons() {
        findViewById<Button>(R.id.Plus).setOnClickListener { calculate("+") }
        findViewById<Button>(R.id.Plus2).setOnClickListener { calculate("+") }
        findViewById<Button>(R.id.Minus).setOnClickListener { calculate("-") }
        findViewById<Button>(R.id.Minus2).setOnClickListener { calculate("-") }
        findViewById<Button>(R.id.X).setOnClickListener { calculate("x") }
        findViewById<Button>(R.id.Equals).setOnClickListener { calculate("=") }
        findViewById<Button>(R.id.C).setOnClickListener {
            editTextResult.setText("")
            currentNumber = null
            previousNumber = null
            currentOperation = null
        }


    }

    private fun calculate(operation: String) {
        if (currentOperation != null && currentNumber != null) {
            if (editTextResult.text.isNotEmpty()) {
                val newNumber = editTextResult.text.toString().toDouble()
                when (currentOperation) {
                    "+" -> currentNumber = currentNumber!! + newNumber
                    "-" -> currentNumber = currentNumber!! - newNumber
                    "x" -> currentNumber = currentNumber!! * newNumber
                }

                editTextResult.setText(currentNumber.toString())
                previousNumber = currentNumber
                currentOperation = if (operation == "=") null else operation
            }
        } else {
            currentNumber = editTextResult.text.toString().toDouble()
            currentOperation = operation
            editTextResult.setText("")
        }
    }
}
