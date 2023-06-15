package com.semdelion.presentation.viewmodels.base

import com.semdelion.presentation.viewmodels.extentions.ListViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseListViewModel(): BaseViewModel() {
    protected val _viewState = MutableStateFlow<ListViewState>(ListViewState.Success)
    val viewState = _viewState.asStateFlow()
}