package com.gp.room.manager

import android.transition.Scene
import androidx.lifecycle.LiveData
import com.gp.common.model.PersonDaily
import com.gp.room.database.VDDatabase
import com.gp.room.entity.MusicInfo
import com.gp.room.entity.PersonInfo
import com.gp.room.entity.SceneryInfo
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.security.auth.callback.Callback

object DailyManager {

    private val musicDao by lazy { VDDatabase.getInstance().musicListDao() }
    private val personDao by lazy { VDDatabase.getInstance().personListDao() }
    private val sceneryDao by lazy { VDDatabase.getInstance().sceneryListDao() }



    // =================== 人物部分 ========================
    suspend fun savePersonList(list: MutableList<PersonInfo>) {
        personDao.insertAll(list)
    }

    fun getPersonoItem(id: Long): PersonInfo? {
        return personDao.query(id)
    }

    fun getPersonList() : MutableList<PersonInfo>? {
        return personDao.queryAll()
    }




    fun getPersonListData() : LiveData<List<PersonInfo>> {
        return personDao.queryAllLiveData()
    }

    fun clearPersonList(callback: (String) -> Unit) {
        MainScope().launch {
            personDao.deleteAll()
            callback("删除成功")
        }
    }


    // =================== 风景部分 ========================
    suspend fun saveSceneryList(list: MutableList<SceneryInfo>) {
        sceneryDao.insertAll(list)
    }

    fun getSceneryItem(id: Long): SceneryInfo? {
        return sceneryDao.query(id)
    }

    fun getSceneryList() : MutableList<SceneryInfo>? {
        return sceneryDao.queryAll()
    }




    fun getSceneryListData() : LiveData<List<SceneryInfo>> {
        return sceneryDao.queryAllLiveData()
    }

    fun clearSceneryList(callback: (String) -> Unit) {
        MainScope().launch {
            sceneryDao.deleteAll()
            callback("删除成功")
        }
    }



    // =================== 音乐部分 ========================
    suspend fun saveMusicList(list: MutableList<MusicInfo>) {
        musicDao.insertAll(list)
    }

    fun getMusicItem(id: Long): MusicInfo? {
        return musicDao.query(id)
    }

    fun getMusicList() : MutableList<MusicInfo>? {
        return musicDao.queryAll()
    }


    fun getMusicListData() : LiveData<List<MusicInfo>> {
        return musicDao.queryAllLiveData()
    }

    fun clearMusicList(callback: (String) -> Unit) {
        MainScope().launch {
            musicDao.deleteAll()
            callback("删除成功")
        }
    }





}