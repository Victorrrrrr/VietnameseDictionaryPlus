package com.gp.room.dao

import androidx.room.Query
import com.gp.common.constant.TABLE_WORD_LIST
import com.gp.common.model.SearchWordBean
import com.gp.room.entity.WordInfo

interface WordListCacheDao {

    /**
     * 查询所有数据
     */
    @Query("SELECT * FROM $TABLE_WORD_LIST")
    fun queryAll() : SearchWordBean?
}