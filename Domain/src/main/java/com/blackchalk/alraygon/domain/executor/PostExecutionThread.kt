package com.blackchalk.alraygon.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler : Scheduler
}