package com.example.shambabukli

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CellsViewModel: ViewModel(){


    //Список клеток:
    val cells = mutableListOf<Int>()

    val liveData = MutableLiveData<ArrayList<Cells>>()

    //Счётчики мёртвых и живых клеток:
    private var aliveCount = 0
    private var deadCount = 0

    //Список с картинками:
    private val imageIdList = listOf(R.drawable.deadcell, R.drawable.livelycell, R.drawable.life)

    // Инициализация liveData
    init {
        liveData.value = ArrayList()
    }

    fun addCells () {
        val random = (0..1).random()

        //Создание клеток, и добавление их в список:
        if (random == 0) {
            aliveCount = 0
            deadCount++
            cells.add(random)
            val cell = Cells(imageIdList[random], "Мёртвая", "или прикидывается")
            liveData.value?.add(cell)
        } else {
            aliveCount++
            deadCount = 0
            cells.add(random)
            val cell = Cells(imageIdList[random], "Живая", "и шевелится")
            liveData.value?.add(cell)
        }

        //Проверка на 3 подряд живых или мёртвых клетки:

        //Если 3 подряд мёртвые клетки, то жизнь рядом пропадает из списка:
        if (deadCount >= 3) {
            for (i in cells.indices.reversed()) {
                if (cells[i] == 2) {
                    cells.removeAt(i)
                    liveData.value?.removeAt(i)
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
            liveData.value?.add(life)
        }
    }
}