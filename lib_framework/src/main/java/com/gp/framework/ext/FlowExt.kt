package com.gp.framework.ext

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

/**
 * 倒计时
 *
 * @param total
 * @param scope
 * @param onTick
 * @param onStart
 * @param onFinish
 * @return
 */
fun countDownCoroutines(
    total: Int,
    scope: CoroutineScope,
    onTick: (Int) -> Unit,
    onStart: (() -> Unit)? = null,
    onFinish: (() -> Unit)? = null
) : Job {
    return flow {
        for (i in total downTo 0) {
            emit(i)
            delay(1000)
        }
    }
        .flowOn(Dispatchers.IO)
        .onStart { onStart?.invoke() }
        .onCompletion{ onFinish?.invoke() }
        .onEach { onTick.invoke(it) }
        .launchIn(scope)

}