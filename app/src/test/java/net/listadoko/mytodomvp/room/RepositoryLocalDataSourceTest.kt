package net.listadoko.mytodomvp.room

import androidx.room.Room
import org.assertj.core.api.Assertions.assertThat
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RepositoryLocalDataSourceTest {
    lateinit var repositoryLocalDataSource: RepositoryLocalDataSource

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "DB")
            .allowMainThreadQueries()
            .build()
        repositoryLocalDataSource = RepositoryLocalDataSource(db)
    }

    @Test
    fun insertAll_finishesSuccessfully() {
        val owner = "lista"
        repositoryLocalDataSource.insertAll(
            Repository(1, "hello", "hello", owner),
            Repository(2, "world", "world", owner)
        )
        val list = repositoryLocalDataSource.findByOwner("lista")
        assertThat(list).hasSize(2)
    }

    @Test
    fun findByOwner_expectsEmpty() {
        val list = repositoryLocalDataSource.findByOwner("lista")
        assertThat(list).isEmpty()
    }
}
