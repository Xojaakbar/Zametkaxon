package uz.gita.luis.qoraroyxat.data.source.local

import androidx.room.*
import uz.gita.luis.qoraroyxat.app.App
import uz.gita.luis.qoraroyxat.data.source.local.converter.DataConverter
import uz.gita.luis.qoraroyxat.data.source.local.dao.NoteDao
import uz.gita.luis.qoraroyxat.data.source.local.entity.NoteData

@Database(entities = [NoteData::class], version = 1)
@TypeConverters(DataConverter::class)

abstract class NoteDataBase : RoomDatabase() {

    abstract fun getDao(): NoteDao

    companion object {
       private lateinit var instance: NoteDataBase
        @JvmName("getInstance1")
        fun getInstance(): NoteDataBase {
            if (!(::instance).isInitialized) {
                instance = Room.databaseBuilder(
                    App.instance.applicationContext,
                    NoteDataBase::class.java,
                    "Notes.db").allowMainThreadQueries().build()
            }
            return instance
        }
    }
}