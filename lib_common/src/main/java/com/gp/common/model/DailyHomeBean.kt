package com.gp.common.model
import com.google.gson.annotations.SerializedName


data class DailyHomeBean(
    @SerializedName("character")
    val character: Character,
    @SerializedName("music")
    val music: Music,
    @SerializedName("poem")
    val poem: Poem,
    @SerializedName("scenery")
    val scenery: Scenery,
    @SerializedName("sentence")
    val sentence: Sentence
)

data class Character(
    @SerializedName("descVi")
    val descVi: String,
    @SerializedName("descZh")
    val descZh: String,
    @SerializedName("field")
    val field: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nameVi")
    val nameVi: String,
    @SerializedName("nameZh")
    val nameZh: String,
    @SerializedName("pic")
    val pic: String
)

data class Music(
    @SerializedName("id")
    val id: Int,
    @SerializedName("lyric")
    val lyric: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)

data class Poem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nameEn")
    val nameEn: String,
    @SerializedName("nameVi")
    val nameVi: String,
    @SerializedName("nameZh")
    val nameZh: String,
    @SerializedName("poemEn")
    val poemEn: String,
    @SerializedName("poemVi")
    val poemVi: String,
    @SerializedName("poemZh")
    val poemZh: String
)

data class Scenery(
    @SerializedName("descVi")
    val descVi: String,
    @SerializedName("descZh")
    val descZh: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("nameVi")
    val nameVi: String,
    @SerializedName("nameZh")
    val nameZh: String
)

data class Sentence(
    @SerializedName("id")
    val id: Int,
    @SerializedName("sentenceEn")
    val sentenceEn: String,
    @SerializedName("sentenceVi")
    val sentenceVi: String,
    @SerializedName("sentenceZh")
    val sentenceZh: String
)