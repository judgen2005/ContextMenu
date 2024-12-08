package com.example.contextmenu

import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var editTextGrade: EditText
    companion object{
        const val MENU_ITEM_COLOR = 101
        const val MENU_ITEM_EXIT = 102
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextGrade = findViewById(R.id.editTextGrade)
        val buttonRandom: View = findViewById(R.id.buttonRandom)
        registerForContextMenu(editTextGrade)
        buttonRandom.setOnClickListener {
            val randomNumber = Random.nextInt(1, 51)
            editTextGrade.setText(randomNumber.run { toString() })
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.add(Menu.NONE, MENU_ITEM_COLOR, Menu.NONE, "Цветовое качество")
        menu?.add(Menu.NONE, MENU_ITEM_EXIT, Menu.NONE, "Выход из приложения")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            MENU_ITEM_COLOR -> {
                val gradeText = editTextGrade.text.toString()
                if (gradeText.isNotEmpty()) {
                    val grade = gradeText.toIntOrNull()
                    if (grade != null) {
                        val color = when (grade) {
                            1 -> ContextCompat.getColor(this, R.color.orange)
                            2 -> Color.YELLOW
                            3 -> Color.GREEN
                            4 -> Color.BLUE
                            5 -> Color.RED
                            in 6..10 -> Color.RED
                            in 11..20 -> ContextCompat.getColor(this, R.color.orange)
                            in 21..30 -> Color.YELLOW
                            in 31..40 -> Color.GREEN
                            in 41..50 -> Color.BLUE
                            else -> Color.WHITE
                        }
                        editTextGrade.setBackgroundColor(color)
                    } else {
                        Toast.makeText(this, "Неверный ввод", Toast.LENGTH_SHORT).show()
                    }
                }

            }

            MENU_ITEM_EXIT -> {
                finish()
                Toast.makeText(applicationContext, "Приложение закрыто", Toast.LENGTH_LONG).show()
            }

            else -> super.onContextItemSelected(item)
        }
        return true
    }
}