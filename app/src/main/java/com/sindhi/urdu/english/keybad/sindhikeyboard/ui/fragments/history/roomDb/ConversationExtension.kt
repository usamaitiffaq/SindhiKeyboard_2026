package com.sindhi.urdu.english.keybad.sindhikeyboard.ui.fragments.history.roomDb

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "convo_ext_tb")
class ConversationExtension(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var conversationName: String = "",
    var source: String = "",
    var translation: String = "",
    var from: String = "",
    var fromLang: String = "",
    var toLang: String = "",
    var isFavorite: Boolean = false,
    var time: Long = 0,
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(conversationName)
        parcel.writeString(source)
        parcel.writeString(translation)
        parcel.writeString(from)
        parcel.writeString(fromLang)
        parcel.writeString(toLang)
        parcel.writeByte(if (isFavorite) 1 else 0)
        parcel.writeLong(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ConversationExtension> {
        override fun createFromParcel(parcel: Parcel): ConversationExtension {
            return ConversationExtension(parcel)
        }

        override fun newArray(size: Int): Array<ConversationExtension?> {
            return arrayOfNulls(size)
        }
    }
}