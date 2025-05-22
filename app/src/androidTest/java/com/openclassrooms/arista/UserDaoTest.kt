package com.openclassrooms.arista

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.openclassrooms.arista.data.AristaDatabase
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith (AndroidJUnit4::class)
class UserDaoTest {
    private lateinit var database: AristaDatabase

    @Before
    // Given a database and a user:
    fun createDb(){
        database = Room
            .inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AristaDatabase::class.java
            )
            .allowMainThreadQueries()
            .build()
    }
    private val user = User(1, "test", "test@test.com")

    @Test
    fun userDao_InsertUser_ReturnsCorrectUser() = runTest {
        // Given user is inserted in the database
        database.userDao().insertUser(user)
        // When the user is retrieved from the database
        val retrievedUser = database.userDao().getUser()
        // Then the retrieved user is the same as the inserted user
        assert(retrievedUser == user)
    }

    @After
    // close the database instance after test
    fun closeDb(){
        database.close()
    }
}