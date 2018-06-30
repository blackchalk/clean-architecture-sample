package com.blackchalk.alraygon.domain.interactor

import com.blackchalk.alraygon.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


//returns observable use case for data different type of data T
abstract class ObservableUseCase<T, in Params> constructor(
        //instance of postexecutionthread, abstraction of scheduler
        //when instance of this observableusecase is use
        private val postExecutionThread: PostExecutionThread) {

    private val disposables = CompositeDisposable()
            //abstract method
            protected abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>

    open fun execute(observer: DisposableObserver<T>, params: Params? = null){

        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose(){
        disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable){
        disposables.add(disposable)
    }
}