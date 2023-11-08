package database

import androidx.room.Dao
import androidx.room.Query
import com.example.crime.Crime
import kotlinx.coroutines.flow.Flow
import java.util.UUID

// data access object, an interface or essentially the API to run SQLite queries
@Dao
interface CrimeDao {
    // flow itself is a suspending function
    @Query("SELECT * FROM crime")
    fun getCrimes(): Flow<List<Crime>>

    @Query("SELECT * FROM CRIME WHERE id = (:id)")
    fun getCrime(id: UUID): Crime
}
