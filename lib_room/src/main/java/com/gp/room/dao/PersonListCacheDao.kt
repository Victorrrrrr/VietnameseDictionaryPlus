package com.gp.room.dao

import androidx.lifecycle.LiveData
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
     * 通过LiveData以观察者的形式获取数据库数据，可以避免不必要的NPE，
     * 可以监听数据库表中的数据的变化，也可以和RXJava的Observer使用
     * 一旦发生了insert，update，delete，room会自动读取表中最新的数据，发送给UI层，刷新页面
     * 不要使用MutableLiveData和suspend 会报错
     */
    @Query("SELECT * FROM $TABLE_PERSON_LIST")
    fun queryAllLiveData(): LiveData<List<PersonInfo>>


    /**
     * 根据id查询某个数据
     */
    @Query("SELECT * FROM $TABLE_PERSON_LIST WHERE id=:id")
    fun query(id: Long): PersonInfo?


    @Update
    fun update(personInfo: PersonInfo) : Int

}