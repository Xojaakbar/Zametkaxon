package uz.gita.luis.qoraroyxat.domain.repository

import uz.gita.luis.qoraroyxat.data.source.local.NoteDataBase
import uz.gita.luis.qoraroyxat.data.source.local.entity.NoteData

class Repository private constructor(){

    val database = NoteDataBase.getInstance().getDao()

    companion object{
        private lateinit var instance:Repository
        fun getInstance(): Repository{
            if (!(::instance.isInitialized)){
                instance = Repository()
            }
            return instance
        }
    }

    fun addNote(noteData: NoteData){
        database.addNote(noteData)
    }

    fun delete(noteData: NoteData){
        database.delete(noteData)
    }

    fun getAllNotes(): List<NoteData>{
        return database.getAllNotes()
    }

    fun update(noteData: NoteData){
        database.update(noteData)
    }

    fun getAllTrashes() : List<NoteData>{
        return database.getTrash()
    }

    fun search(text:String): List<NoteData>{
        return database.getByTitle(text)
    }

    fun deleteAll(){
    database.deleteAll()
    }

}

