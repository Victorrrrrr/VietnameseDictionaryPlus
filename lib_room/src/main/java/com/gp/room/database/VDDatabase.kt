package com.gp.room.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gp.framework.helper.VDHelper

//@Database(entities = [], version = 1, exportSchema = false )
abstract class VDDatabase : RoomDatabase() {

    // 抽象方法或者抽象类标记


    companion object {
        private var dataBase: VDDatabase? = null

        // 同步锁，可能在多个线程中同时调用
        @Synchronized
        fun getInstance(): VDDatabase {
            return dataBase ?: Room.databaseBuilder(VDHelper.getApplication(), VDDatabase::class.java, "VD_DB")
                // 是否允许在主线程查询，默认是false
                .allowMainThreadQueries()
                //.addCallback(callBack)
                //指定数据查询的线程池，不指定会有个默认的
                //.setQueryExecutor {  }
                //任何数据库有变更版本都需要升级，升级的同时需要指定migration，如果不指定则会报错
                //数据库升级 1-->2， 怎么升级，以什么规则升级
                .addMigrations()
                //设置数据库工厂，用来链接room和SQLite，可以利用自行创建SupportSQLiteOpenHelper，来实现数据库加密
                //.openHelperFactory()
                .build()
        }

    }

}