package com.gp.main.repository

import com.gp.common.model.AuthClientBean
import com.gp.common.model.DailyHomeBean
import com.gp.common.model.MusicDaily
import com.gp.common.model.PersonDaily
import com.gp.common.model.SceneryDaily
import com.gp.common.model.WordRandomBean
import com.gp.framework.utils.LogUtil
import com.gp.network.manager.ApiManager
import com.gp.network.repository.BaseRepository

class MainRepository : BaseRepository() {

    suspend fun authClient(grantType : String, clientId : String, clientSecret : String) : AuthClientBean? {
        return requestAuthResponse {
            ApiManager.api.appAuthClient(grantType, clientId, clientSecret)
        }
    }



    suspend fun getHomeDailyData() : DailyHomeBean? {
        return requestResponse {
            ApiManager.api.getDailyHomeData()
        }
    }


    suspend fun getWordRandom() : WordRandomBean? {
        return requestResponse {
            ApiManager.api.getWordRandom()
        }
    }


    suspend fun getMusicDaily() : ArrayList<MusicDaily?> {
        val musicBean = requestResponse {
            ApiManager.api.getMusicDaily()
        }
        LogUtil.d(tag = "111", message = "${musicBean}")
        val musicList = ArrayList<MusicDaily?>()
        musicBean?.let {
            musicList.add(it.daily)
            musicList.addAll(it.recommendList)
        }
        return musicList
    }


    suspend fun getSceneryDaily() : ArrayList<SceneryDaily?> {
        val sceneryBean = requestResponse {
            ApiManager.api.getSceneryDaily()
        }
        val sceneryList = ArrayList<SceneryDaily?>()
        sceneryBean?.let {
            sceneryList.add(it.daily)
            sceneryList.addAll(it.recommendList)
        }
        return sceneryList
    }

    suspend fun getPersonDaily() : ArrayList<PersonDaily?> {
        val personBean = requestResponse {
            ApiManager.api.getPersonDaily()
        }
        val personList = ArrayList<PersonDaily?>()
        personBean?.let {
            personList.add(it.daily)
            personList.addAll(it.recommendList)
        }
        return personList

        // 存入本地数据库
//        var localList = mutableListOf<PersonInfo>()
//        personList.forEach { person ->
//            val personInfo = PersonInfo()
//            person?.let {
//                personInfo.id = it.id
//                personInfo.descVi = it.descVi
//                personInfo.descZh = it.descZh
//                personInfo.field = it.field
//                personInfo.nameVi = it.nameVi
//                personInfo.nameZh = it.nameZh
//            }
//            localList.add(personInfo)
//
//
//        }
//        DailyManager.savePersonList(localList)
    }






}