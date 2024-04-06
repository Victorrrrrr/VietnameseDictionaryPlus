package com.gp.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gp.common.constant.TABLE_PERSON_LIST
import com.gp.common.constant.TABLE_SCENERY_LIST
import com.gp.room.entity.PersonInfo
import com.gp.room.entity.SceneryInfo

@Dao
interface SceneryListCacheDao {


    /**
     * 插入单个数据
     *
     * @param sceneryInfo
     */
    @Insert(entity = SceneryInfo::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sceneryInfo: SceneryInfo)


    /**
     * 插入多个数据
     *
     * @param sceneryList
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(sceneryList: MutableList<SceneryInfo>)


    /**
     * 删除所有数据
     */
    @Query("DELETE FROM $TABLE_SCENERY_LIST")
    suspend fun deleteAll(): Int


    /**
     * 查询所有数据
     */
    @Query("SELECT * FROM $TABLE_SCENERY_LIST")
    fun queryAll() : MutableList<SceneryInfo>?


    @Query("SELECT * FROM $TABLE_SCENERY_LIST")
    fun queryAllLiveData(): LiveData<List<SceneryInfo>>


    /**
     * 根据id查询某个数据
     */
    @Query("SELECT * FROM $TABLE_SCENERY_LIST WHERE id=:id")
    fun query(id: Long): SceneryInfo?


    @Update
    fun update(sceneryInfo: SceneryInfo) : Int

}