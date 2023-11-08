package com.example.crime

import android.content.Context
import androidx.room.Room
import database.CrimeDatabase
import kotlinx.coroutines.flow.Flow
import java.util.UUID

private const val DATABASE_NAME = "crime-database"

// needs to be a singleton
class CrimeRepository private constructor(context: Context) {
    private val database: CrimeDatabase = Room.databaseBuilder(
        context.applicationContext, CrimeDatabase::class.java, DATABASE_NAME
    ).createFromAsset(DATABASE_NAME).build()

    fun getCrimes(): Flow<List<Crime>> = database.crimeDao().getCrimes()
    suspend fun getCrime(id: UUID): Crime = database.crimeDao().getCrime(id)

    // allows access without having an instance of that class
    companion object {
        private var INSTANCE: CrimeRepository? = null

        // constructor for singleton
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        // initialize at application level
        fun get(): CrimeRepository {
            return INSTANCE
                ?: throw java.lang.IllegalStateException("CrimeRepository must be initialized.")
        }
    }
}
