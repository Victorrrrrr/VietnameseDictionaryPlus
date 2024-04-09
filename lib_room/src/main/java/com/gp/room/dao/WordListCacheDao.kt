package com.gp.room.dao

import androidx.room.Query
import com.gp.common.constant.TABLE_WORD_INFO_LIST
import com.gp.room.entity.WordBeanInfo

interface WordListCacheDao {

    /**
     * 查询所有数据
     */
    @Query("SELECT * FROM $TABLE_WORD_INFO_LIST")
    fun queryAll() : WordBeanInfo?
}