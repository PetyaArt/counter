package com.example.counter

private typealias CounterViewPartialViewState = (CounterViewState) -> CounterViewState

object CounterViewPartialViewStates {

    fun increment() : CounterViewPartialViewState = { previuosViewState ->
        previuosViewState.copy(
                counterValue = previuosViewState.counterValue + 1,
                totalClick = previuosViewState.totalClick + 1
        )
    }

    fun decrement() : CounterViewPartialViewState = { previuosViewState ->
        previuosViewState.copy(
                counterValue = previuosViewState.counterValue - 1,
                totalClick = previuosViewState.totalClick - 1
        )
    }
}