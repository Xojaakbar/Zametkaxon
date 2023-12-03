package uz.gita.luis.qoraroyxat.data.source.local.entity

import androidx.core.content.ContextCompat
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.luis.qoraroyxat.R
import uz.gita.luis.qoraroyxat.app.App

@Entity(tableName = "Notes")
data class NoteData (
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0,
    val title:String,
    val content:String,
    val date:String,
    var pin:Int = 0,
    val bg:Int = ContextCompat.getColor(App.instance, R.color.default_bg_item),
    var trash:Int
) : java.io.Serializable