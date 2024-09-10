package com.example.shambabukli

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shambabukli.databinding.CellItemBinding

class CellsAdapter: RecyclerView.Adapter<CellsAdapter.CellsHolder>() {

    var cellList = ArrayList<Cells>()

    class CellsHolder(item: View):RecyclerView.ViewHolder(item) {

        val binding = CellItemBinding.bind(item)

        fun bind (cells: Cells) = with(binding){
            im.setImageResource(cells.imageId)
            tvtitle.text = cells.title
            tvcomment.text = cells.comment
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_item, parent, false)
        return CellsHolder(view)
    }

    override fun getItemCount(): Int {
        return cellList.size
    }

    override fun onBindViewHolder(holder: CellsHolder, position: Int) {
        holder.bind(cellList[position])
    }

    //Функциия для обновления списка:
    fun update(){
        notifyDataSetChanged()
    }
}