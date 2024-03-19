package com.gp.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.gp.common.constant.TABLE_PERSON_LIST
import com.gp.common.constant.TABLE_SCENERY_LIST
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_SCENERY_LIST)
data class SceneryInfo(
    @PrimaryKey(autoGenerate = false)
    var id : Long = 0,

    @ColumnInfo(name = "nameVi", defaultValue = "")
    var nameVi : String?,

    @ColumnInfo(name = "nameZh", defaultValue = "")
    var nameZh : String?,

    @ColumnInfo(name = "descVi", defaultValue = "")
    var descVi : String?,

    @ColumnInfo(name = "descZh", defaultValue = "")
    var descZh : String?,

    @ColumnInfo(name = "location", defaultValue = "")
    var location : String?,

    @ColumnInfo(name = "img", defaultValue = "")
    var img : String?

): Parcelable {
    @Ignore
    constructor() : this(0, "", "","","","","")
}