package com.semdelion.presentation.viewmodels.base

import com.semdelion.presentation.viewmodels.extentions.ListViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseListViewModel() : BaseViewModel() {
    protected val _viewState = MutableStateFlow<ListViewState>(ListViewState.Success)
    val viewState = _viewState.asStateFlow()
    var isLoading: Boolean = false

    protected suspend fun getItemsWithState(getItems: () -> Unit) {
        try {
            isLoading = true
            _viewState.emit(ListViewState.Loading)
            getItems.invoke()
            _viewState.emit(ListViewState.Success)
        } catch (ex: Exception) {
            _viewState.emit(ListViewState.Error(ex))
        } finally {
            isLoading = false
        }
    }
}