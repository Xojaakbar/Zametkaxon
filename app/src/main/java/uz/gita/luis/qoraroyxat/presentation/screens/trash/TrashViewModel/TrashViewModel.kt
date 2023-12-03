package uz.gita.luis.qoraroyxat.presentation.screens.trash.TrashViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.luis.qoraroyxat.data.source.local.entity.NoteData
import uz.gita.luis.qoraroyxat.domain.repository.Repository

class TrashViewModel:ViewModel() {

    val repository = Repository.getInstance()

    val trashesLiveData = MutableLiveData<List<NoteData>>()
    val backToMainLiveData = MutableLiveData<Unit>()

    fun getAllTrashes(){
        trashesLiveData.value = repository.getAllTrashes()
    }

    fun deleteAll(){
        repository.deleteAll()
        trashesLiveData.value = repository.getAllTrashes()
    }

    fun deleteFromTrash(noteData: NoteData){
        noteData.trash = 0
        repository.update(noteData)
        trashesLiveData.value = repository.getAllTrashes()
    }
    fun backToMain(){
        backToMainLiveData.value = Unit
    }
}