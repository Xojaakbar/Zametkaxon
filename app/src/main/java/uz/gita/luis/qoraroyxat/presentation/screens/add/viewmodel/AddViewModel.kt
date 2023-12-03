package uz.gita.luis.qoraroyxat.presentation.screens.add.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.luis.qoraroyxat.data.source.local.dao.NoteDao
import uz.gita.luis.qoraroyxat.data.source.local.entity.NoteData
import uz.gita.luis.qoraroyxat.domain.repository.Repository

class AddViewModel:ViewModel() {
    val repository = Repository.getInstance()

    val saveVisibilityLiveData = MutableLiveData<Unit>()
    val saveClickLiveData = MutableLiveData<Unit>()

    fun addNote(noteData: NoteData){
        repository.addNote(noteData)
    }

}