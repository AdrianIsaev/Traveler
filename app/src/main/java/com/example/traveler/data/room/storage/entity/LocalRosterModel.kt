package com.example.traveler.data.room.storage.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rosterModel")
class LocalRosterModel() : Parcelable{
    @PrimaryKey (autoGenerate = true) @ColumnInfo(name = "p_id")
    var rosterElementId: Int = 0
    @ColumnInfo (name = "p_title")
    var title: String = ""
    @ColumnInfo (name = "p_name")
    var authorName: String = ""
    @ColumnInfo (name = "p_description")
    var description: String = ""
    @ColumnInfo (name = "p_likeflag")
    var likeFlag: Int = 0
    @ColumnInfo (name = "p_commentaryflag")
    var commentaryFlag: Int = 0
    constructor(parcel: Parcel) : this() {
        rosterElementId = parcel.readInt()
        title = parcel.readString() ?: ""
        authorName = parcel.readString() ?: ""
        description = parcel.readString() ?: ""
        likeFlag = parcel.readInt()
        commentaryFlag = parcel.readInt()
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(rosterElementId)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeInt(commentaryFlag)
        parcel.writeInt(likeFlag)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LocalRosterModel> {
        override fun createFromParcel(parcel: Parcel): LocalRosterModel {
            return LocalRosterModel(parcel)
        }

        override fun newArray(size: Int): Array<LocalRosterModel?> {
            return arrayOfNulls(size)
        }
    }
}