// Copyright (С) ABBYY (BIT Software), 1993 - 2019. All rights reserved.
// Автор: Петя Артамонов

package com.example.counter

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.exceptions.Exceptions
import io.reactivex.subjects.PublishSubject

class CounterPresenter(private val view: CounterView) {

    private val incrementSubject = PublishSubject.create<Unit>()
    private val decrementSubject = PublishSubject.create<Unit>()

    private val disposeable: CompositeDisposable = CompositeDisposable()


    fun onCreate() {
        val incrementObservable = incrementSubject
                .map { CounterViewPartialViewStates.increment() }

        val decrementObservable = decrementSubject
                .map { CounterViewPartialViewStates.decrement() }

        disposeable.add(Observable.merge(incrementObservable, decrementObservable)
                .scan(CounterViewState()) { previousViewState, partialViewState ->
                    partialViewState.invoke(previousViewState) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ viewState ->
                    view.render(viewState)
                }, { throwable ->
                    Exceptions.propagate(throwable)
                }))
    }

    fun increment() = incrementSubject.onNext(Unit)

    fun decrement() = decrementSubject.onNext(Unit)

    fun onDestroy() = disposeable.clear()

}