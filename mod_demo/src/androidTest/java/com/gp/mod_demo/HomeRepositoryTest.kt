package com.gp.mod_demo

import com.gp.mod_demo.model.repo.HomeRepository

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class HomeRepositoryTest {

    private val repo = HomeRepository()

    @Before
    fun setUp() {

    }

    @Test
    fun requestArticle() = runBlocking {
        val result = repo.getHomeInfoList(0)
        println(result)
        assertEquals(0, result.code)
        assertTrue(result.data?.datas?.size!! > 0)
    }
}