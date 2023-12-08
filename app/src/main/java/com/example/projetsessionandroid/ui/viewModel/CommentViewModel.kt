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

    //Initialisation des variables qui vont nous permettrent de stocker les comments recuperer de l'api
    val comments = MutableStateFlow<List<Comment>>(listOf())
    val _comment = MutableLiveData<Comment>()
    val comment: LiveData<Comment> get() = _comment

    //Creation du lien entre l'app android et l'api
    val service = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/api/comment/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(CommentService::class.java)

    //Fonction pour recuperer tous les Comments
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

    //Fonction pour recuperer un comment en fonction de son id
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

    //Fonction pour ajouter un comment dans la base de donnee
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

    //Fonction pour modifier un comment stocker dans la base de donnee
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

    //Fonction pour supprimer un comment de la base de donnee
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