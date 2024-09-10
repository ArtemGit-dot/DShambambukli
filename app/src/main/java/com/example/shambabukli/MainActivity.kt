package com.example.shambabukli

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shambabukli.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //Адаптер:
    private val adapter = CellsAdapter()
    lateinit var mViewModel: CellsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Клеточное наполнение"

        mViewModel = ViewModelProvider(this).get(CellsViewModel::class.java)

        //Настройка RecyclerView:
        binding.rcView.adapter = adapter
        binding.rcView.layoutManager = LinearLayoutManager(this)


        //Слушатель нажатий:
        binding.button.setOnClickListener {
            mViewModel.addCells()
            mViewModel.liveData.observe(this, Observer {
                adapter.cellList = it
                adapter.update()
})
        }
    }
}