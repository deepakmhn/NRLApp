package com.foxsports.nrlapp

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Global scheduler for the whole application.
 */
@Singleton
class AppSchedulers(
    private val ioScheduler: Scheduler,
    private val computationScheduler: Scheduler,
    private val mainThreadScheduler: Scheduler
) {

    @Inject
    constructor() : this(
        Schedulers.io(),
        Schedulers.computation(),
        AndroidSchedulers.mainThread()
    )

    fun io(): Scheduler {
        return ioScheduler
    }

    fun computation(): Scheduler {
        return computationScheduler
    }

    fun mainThread(): Scheduler {
        return mainThreadScheduler
    }

}