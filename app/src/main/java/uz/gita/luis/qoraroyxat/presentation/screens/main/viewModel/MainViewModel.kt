package uz.gita.luis.qoraroyxat.presentation.screens.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.luis.qoraroyxat.data.source.local.entity.NoteData
import uz.gita.luis.qoraroyxat.domain.repository.Repository

class MainViewModel:ViewModel() {
    private val repository = Repository.getInstance()
    val buttonAddLiveData = MutableLiveData<Unit>()
    val buttonTrashLiveData = MutableLiveData<Unit>()
    val searchLiveData = MutableLiveData<List<NoteData>>()
    val getAllLiveData = MutableLiveData<List<NoteData>>()
    val openUpdateLiveData = MutableLiveData<NoteData>()
    val openBottomSheetLiveData = MutableLiveData<NoteData>()

    fun openBottomSheet(noteData: NoteData){
        openBottomSheetLiveData.value = noteData
    }

    fun openAddScreen(){
    buttonAddLiveData.value = Unit
    }

    fun openUpdate(noteData: NoteData){
        openUpdateLiveData.value = noteData
    }

    fun delete(noteData: NoteData){
        noteData.trash = 1
        repository.update(noteData)
        getAllLiveData.value = repository.getAllNotes()
    }

    fun openTrash(){
        buttonTrashLiveData.value = Unit
    }

    fun getAllNotes() {
        getAllLiveData.value = repository.getAllNotes()
    }

    fun search(text: String) {
        searchLiveData.value = repository.search(text)
    }

    fun update(noteData: NoteData){
        repository.update(noteData)
        getAllLiveData.value = repository.getAllNotes()
    }
}