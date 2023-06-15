package com.semdelion.presentation.viewmodels.extentions

sealed class ListViewState {
    object Loading : ListViewState()
    object Success : ListViewState()
    data class Error(val error: Exception) : ListViewState()
}
