package uz.gita.luis.qoraroyxat.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.luis.qoraroyxat.data.source.local.entity.NoteData
import uz.gita.luis.qoraroyxat.databinding.NoteItemBinding

class HomeAdapter:ListAdapter<NoteData,HomeAdapter.HomeViewHolder>(Mydifutil) {

    private var update: ((NoteData)->Unit)? = null
    fun setUpdateListener(block:(NoteData)->Unit){
        update = block
    }
    private var bottomSheet: ((NoteData)->Unit)? = null
    fun setBottomSheetListener(block:(NoteData)->Unit){
        bottomSheet = block
    }

    object Mydifutil:DiffUtil.ItemCallback<NoteData>(){
        override fun areItemsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
            return oldItem == newItem
        }
    }

    inner class HomeViewHolder(private val binding: NoteItemBinding):ViewHolder(binding.root){
        fun bind(noteData: NoteData){
            binding.data.text = noteData.date
            binding.title.text = noteData.title
            binding.content.text = noteData.content.parseAsHtml()

           if (noteData.pin == 1) binding.pin.visibility = View.VISIBLE
           else binding.pin.visibility = View.INVISIBLE

            binding.root.setOnClickListener {
                update?.invoke(noteData)
            }
            binding.root.setOnLongClickListener {
                bottomSheet?.invoke(noteData)
                return@setOnLongClickListener true
            }

//            if (noteData.pin == 1){
//            pin qiladigan qilishim kerak
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(NoteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}