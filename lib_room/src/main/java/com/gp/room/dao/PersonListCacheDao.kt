package com.gp.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gp.common.constant.TABLE_PERSON_LIST
import com.gp.room.entity.PersonInfo

@Dao
interface PersonListCacheDao {

    /**
     * 插入单个数据
     *
     * @param personInfo
     */
    @Insert(entity = PersonInfo::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(personInfo: PersonInfo)


    /**
     * 插入多个数据
     *
     * @param personList
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(personList: MutableList<PersonInfo>)


    /**
     * 删除所有数据
     */
    @Query("DELETE FROM $TABLE_PERSON_LIST")
    suspend fun deleteAll() : Int


    /**
     * 查询所有数据
     */
    @Query("SELECT * FROM $TABLE_PERSON_LIST")
    fun queryAll() : MutableList<PersonInfo>?


    /**
     * 根据id查询某个数据
     */
    @Query("SELECT * FROM $TABLE_PERSON_LIST WHERE id=:id")
    fun query(id: Long): PersonInfo?


    @Update
    fun update(personInfo: PersonInfo) : Int

}