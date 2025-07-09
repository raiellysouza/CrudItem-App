package com.example.cruditemapp.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.firebase.firestore.FirebaseFirestore
import com.example.cruditemapp.data.Note

@Composable
fun FetchNotesFromFirestore (modifier: Modifier = Modifier) {
    val notes  = remember { mutableStateListOf<Note>() }
    val notesCollection = FirebaseFirestore.getInstance().collection("notes")

    LaunchedEffect (Unit) {
        notesCollection.get()
            .addOnSuccessListener { result ->
                notes.clear()
                for (document in result) {
                    val note = document.toObject(Note::class.java)
                    notes.add(note)
                }
            }
            .addOnFailureListener { exception ->
                println("Erro ao buscar notas: $exception")
            }
    }
    NoteList (notes = notes, modifier = modifier)
}

@Composable
fun NoteList (notes: List<Note>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items (notes) { note ->
            Text (text = "Title: ${note.title}")
            Text (text = "Content: ${note.content}")
        }
    }
}