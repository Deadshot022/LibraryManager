package com.android.librarymanager.models

data class students(
    val name: String,
    val email: String,
    val password: String,
    val year: String,
    val branch: String,
    val profilePhotoUrl: String? = null
)
