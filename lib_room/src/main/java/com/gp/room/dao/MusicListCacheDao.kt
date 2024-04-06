package com.gp.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gp.common.constant.TABLE_MUSIC_LIST
import com.gp.common.constant.TABLE_PERSON_LIST
import com.gp.room.entity.MusicInfo
import com.gp.room.entity.PersonInfo


@Dao
interface MusicListCacheDao {

    /**
     * 插入单个数据
     *
     * @param personInfo
     */
    @Insert(entity = MusicInfo::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(musicInfo: MusicInfo)


    /**
     * 插入多个数据
     *
     * @param personList
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(musicList : MutableList<MusicInfo>)


    /**
     * 删除所有数据
     */
    @Query("DELETE FROM $TABLE_MUSIC_LIST")
    suspend fun deleteAll(): Int


    /**
     * 查询所有数据
     */
    @Query("SELECT * FROM $TABLE_MUSIC_LIST")
    fun queryAll() : MutableList<MusicInfo>?


    @Query("SELECT * FROM $TABLE_MUSIC_LIST")
    fun queryAllLiveData(): LiveData<List<MusicInfo>>


    /**
     * 根据id查询某个数据
     */
    @Query("SELECT * FROM $TABLE_MUSIC_LIST WHERE id=:id")
    fun query(id: Long): MusicInfo?


    @Update
    fun update(musicInfo: MusicInfo) : Int
}