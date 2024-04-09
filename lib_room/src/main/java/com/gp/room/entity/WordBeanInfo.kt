package com.gp.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gp.common.constant.TABLE_WORD_INFO_LIST
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_WORD_INFO_LIST)
data class WordBeanInfo(
    @ColumnInfo(name = "frequency", defaultValue = "")
    val frequency: Int,

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "isProofread", defaultValue = "")
    val isProofread: String,

    @ColumnInfo(name = "lexicon", defaultValue = "")
    val lexicon: String,

    @ColumnInfo(name = "pos", defaultValue = "")
    val pos: String,

    @ColumnInfo(name = "pronounceEn", defaultValue = "")
    val pronounceEn: String,

    @ColumnInfo(name = "pronounceVi", defaultValue = "")
    val pronounceVi: String,

    @ColumnInfo(name = "pronounceZh", defaultValue = "")
    val pronounceZh: String,

    @ColumnInfo(name = "source", defaultValue = "")
    val source: String,

    @ColumnInfo(name = "wordEn", defaultValue = "")
    val wordEn: String,
    @ColumnInfo(name = "wordVi", defaultValue = "")
    val wordVi: String,
    @ColumnInfo(name = "wordZh", defaultValue = "")
    val wordZh: String
) : Parcelable {
}