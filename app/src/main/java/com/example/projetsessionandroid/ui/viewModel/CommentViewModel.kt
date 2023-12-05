package com.example.projetsessionandroid.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetsessionandroid.data.model.Comment
import com.example.projetsessionandroid.data.service.CommentService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CommentViewModel: ViewModel() {

    val comments = MutableStateFlow<List<Comment>>(listOf())
    val _comment = MutableLiveData<Comment>()
    val comment: LiveData<Comment> get() = _comment

    val service = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/api/comment")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(CommentService::class.java)

    fun getAllComment(){
        viewModelScope.launch {
            try {
                comments.value = service.getAllComments()
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }

    fun getCommentById(id : String){
        viewModelScope.launch {
            try {
                _comment.value = service.getCommentById(id)
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }

    fun createComment(comment : Comment){
        viewModelScope.launch {
            try {
                service.createComment(comment)
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }

    fun updateComment(comment : Comment){
        viewModelScope.launch {
            try {
                service.updateComment(comment.id,comment)
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }

    fun deleteComment(comment : Comment){
        viewModelScope.launch {
            try {
                service.deleteComment(comment.id)
            } catch (e: Exception) {
                // Gérer les erreurs
                e.printStackTrace()
            }
        }
    }
}