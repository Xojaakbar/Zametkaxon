package uz.gita.luis.qoraroyxat.data.source.local.dao

import androidx.room.*
import uz.gita.luis.qoraroyxat.data.source.local.entity.NoteData

@Dao
interface NoteDao {

    @Insert
    fun addNote(noteData: NoteData)

    @Update
    fun update(noteData: NoteData)

    @Delete
    fun delete(noteData: NoteData)

    @Query("DELETE FROM Notes WHERE trash = 1")
    fun deleteAll()

    @Query("SELECT * FROM Notes where trash = 0 order by pin desc, id desc")
    fun getAllNotes(): List<NoteData>

    @Query("SELECT * FROM Notes WHERE trash = 0 AND title LIKE '%' || :text || '%' ORDER BY pin DESC")
    fun getByTitle(text: String): List<NoteData>

    @Query("SELECT * FROM Notes WHERE trash = 1")
    fun getTrash(): List<NoteData>

}