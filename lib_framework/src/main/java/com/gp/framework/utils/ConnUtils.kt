package com.gp.framework.utils

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.UnsupportedEncodingException
import java.net.HttpURLConnection
import java.net.URLEncoder


/**
 * 与连接相关的Util类
 */
object ConnUtil {
    /**
     * UrlEncode， UTF-8 编码
     *
     * @param str 原始字符串
     * @return
     */
    fun urlEncode(str: String?): String? {
        var result: String? = null
        try {
            result = URLEncoder.encode(str, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return result
    }

    /**
     * 从HttpURLConnection 获取返回的字符串
     *
     * @param conn
     * @return
     * @throws IOException
     * @throws DemoException
     */
    @Throws(IOException::class)
    fun getResponseString(conn: HttpURLConnection): String {
        return String(getResponseBytes(conn))
    }

    /**
     * 从HttpURLConnection 获取返回的bytes
     * 注意 HttpURLConnection自身问题， 400类错误，会直接抛出异常。不能获取conn.getInputStream();
     *
     * @param conn
     * @return
     * @throws IOException   http请求错误
     * @throws DemoException http 的状态码不是 200
     */
    @Throws(IOException::class)
    fun getResponseBytes(conn: HttpURLConnection): ByteArray {
        val responseCode = conn.responseCode
        var inputStream = conn.inputStream
        if (responseCode != 200) {
            System.err.println("http 请求返回的状态码错误，期望200， 当前是 $responseCode")
            if (responseCode == 401) {
                System.err.println("可能是appkey appSecret 填错")
            }
            System.err.println("response headers" + conn.headerFields)
            if (inputStream == null) {
                inputStream = conn.errorStream
            }
            val result = getInputStreamContent(inputStream)
            System.err.println(String(result))
            LogUtil.e("http response code is$responseCode")
        }
        return getInputStreamContent(inputStream)
    }

    /**
     * 将InputStream内的内容全部读取，作为bytes返回
     *
     * @param is
     * @return
     * @throws IOException @see InputStream.read()
     */
    @Throws(IOException::class)
    fun getInputStreamContent(`is`: InputStream?): ByteArray {
        val b = ByteArray(1024)
        // 定义一个输出流存储接收到的数据
        val byteArrayOutputStream = ByteArrayOutputStream()
        // 开始接收数据
        var len = 0
        while (true) {
            len = `is`!!.read(b)
            if (len == -1) {
                // 数据读完
                break
            }
            byteArrayOutputStream.write(b, 0, len)
        }
        return byteArrayOutputStream.toByteArray()
    }
}