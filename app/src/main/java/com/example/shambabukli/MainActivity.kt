package com.example.shambabukli

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shambabukli.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //Адаптер:
    private val adapter = CellsAdapter()

    //Список клеток:
    val cells = mutableListOf<Int>()

    //Счётчики мёртвых и живых клеток:
    private var aliveCount = 0
    private var deadCount = 0

    //Список с картинками:
    private val imageIdList = listOf(R.drawable.deadcell, R.drawable.livelycell, R.drawable.life)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Клеточное наполнение"

        //Настройка RecyclerView:
        binding.rcView.adapter = adapter
        binding.rcView.layoutManager = LinearLayoutManager(this)

        //Слушатель нажатий:
        binding.button.setOnClickListener {
            val random = (0..1).random()

            //Создание клеток, и добавление их в список:
            if (random == 0) {
                aliveCount = 0
                deadCount++
                cells.add(random)
                val cell = Cells(imageIdList[random], "Мёртвая", "или прикидывается")
                adapter.addCells(cell)
            } else {
                aliveCount++
                deadCount = 0
                cells.add(random)
                val cell = Cells(imageIdList[random], "Живая", "и шевелится")
                adapter.addCells(cell)
            }

            //Проверка на 3 подряд живых или мёртвых клетки:

            //Если 3 подряд мёртвые клетки, то жизнь рядом пропадает из списка:
            if (deadCount >= 3) {
                for (i in cells.indices.reversed()) {
                    if (cells[i] == 2) {
                        cells.removeAt(i)
                        adapter.remoteCells(i)
                        break
                    }
                }
                deadCount = 0
            }
            //Если 3 подряд живые клетки, то жизнь появляется в списке:
            else if (aliveCount >= 3) {
                cells.add(2)
                aliveCount = 0
                val life = Cells(imageIdList[2], "Жизнь", "Ку-ку!")
                adapter.addCells(life)
            }
        }
    }
}