package com.gp.room.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gp.framework.helper.VDHelper
import com.gp.room.dao.MusicListCacheDao
import com.gp.room.dao.PersonListCacheDao
import com.gp.room.dao.SceneryListCacheDao
import com.gp.room.dao.WordListCacheDao
import com.gp.room.entity.MusicInfo
import com.gp.room.entity.PersonInfo
import com.gp.room.entity.SceneryInfo
import com.gp.room.entity.WordBeanInfo


@Database(entities = [PersonInfo::class, SceneryInfo::class, MusicInfo::class, WordBeanInfo::class], version = 2, exportSchema = false )
abstract class VDDatabase : RoomDatabase() {

    // 抽象方法或者抽象类标记
    abstract fun personListDao() : PersonListCacheDao
    abstract fun sceneryListDao() : SceneryListCacheDao
    abstract fun musicListDao() : MusicListCacheDao
    abstract fun wordListDao() : WordListCacheDao

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
                .addMigrations(MIGRATION_1_2)
                //设置数据库工厂，用来链接room和SQLite，可以利用自行创建SupportSQLiteOpenHelper，来实现数据库加密
                //.openHelperFactory()
                .build()
        }

        /**
         * 版本升级迁移到6 在数据库中新增一个笔记表
         */
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //创建笔记表
                database.execSQL(
                    "CREATE TABLE `word_info_list` " +
                            "(id INTEGER NOT NULL, " +
                            "frequency TEXT, " +
                            "isProofread TEXT, " +
                            "lexicon TEXT, " +
                            "pos TEXT, " +
                            "pronounceEn TEXT, " +
                            "pronounceVi TEXT, " +
                            "pronounceZh TEXT, " +
                            "source TEXT, " +
                            "wordEn TEXT, " +
                            "wordVi TEXT, " +
                            "wordZh TEXT, " +
                            "PRIMARY KEY(`uid`))"
                )
            }
        }

    }

}