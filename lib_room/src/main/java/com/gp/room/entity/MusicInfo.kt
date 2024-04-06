package com.gp.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.gp.common.constant.TABLE_MUSIC_LIST
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = TABLE_MUSIC_LIST)
data class MusicInfo(
    @PrimaryKey(autoGenerate = false)
    var id : Int = 0,

    @ColumnInfo(name = "url", defaultValue = "")
    var soundUrl : String?,

    @ColumnInfo(name = "name", defaultValue = "")
    var name : String?,

    @ColumnInfo(name = "type", defaultValue = "")
    var type : String?,

    @ColumnInfo(name = "lyric", defaultValue = "")
    var lyric : String?,

): Parcelable {
    @Ignore
    constructor() : this(0, "", "","","")
}