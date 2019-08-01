package com.example.counter

typealias PartialViewState<ViewState> = (ViewState) -> ViewState

object PartialViewStates {

    fun <ViewState> apply(): (ViewState, PartialViewState<ViewState>) -> ViewState =
            { viewState, partial -> partial.invoke(viewState) }
}