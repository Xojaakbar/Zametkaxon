package uz.gita.luis.qoraroyxat.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.luis.qoraroyxat.data.source.local.entity.NoteData
import uz.gita.luis.qoraroyxat.databinding.NoteItemBinding

class TrashAdapter:ListAdapter<NoteData,TrashAdapter.ViewHolderTrash>(difutil) {

    private var delete: ((NoteData)->Unit)? = null
    fun setDelete(block:(NoteData)->Unit){
        delete = block
    }

    object difutil: DiffUtil.ItemCallback<NoteData>(){
        override fun areItemsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolderTrash(private val binding: NoteItemBinding): ViewHolder(binding.root){
        fun bind(noteData: NoteData){
            binding.root.setOnLongClickListener {
                delete?.invoke(noteData)
                return@setOnLongClickListener true
            }
            binding.data.text = noteData.date
            binding.title.text = noteData.title
            binding.content.text = noteData.content.parseAsHtml()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTrash {
        return ViewHolderTrash(NoteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolderTrash, position: Int) {
        holder.bind(getItem(position))
    }

}