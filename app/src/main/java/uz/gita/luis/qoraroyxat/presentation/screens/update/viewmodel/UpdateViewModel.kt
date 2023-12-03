package uz.gita.luis.qoraroyxat.presentation.screens.update.viewmodel

import androidx.lifecycle.ViewModel
import uz.gita.luis.qoraroyxat.data.source.local.entity.NoteData
import uz.gita.luis.qoraroyxat.domain.repository.Repository

class UpdateViewModel:ViewModel() {
    val repository = Repository.getInstance()
    fun update(noteData: NoteData){
        repository.update(noteData)
    }
}