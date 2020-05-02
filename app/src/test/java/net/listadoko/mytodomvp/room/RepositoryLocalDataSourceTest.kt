package net.listadoko.mytodomvp.room

import androidx.room.Room
import org.assertj.core.api.Assertions.assertThat
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(Enclosed::class)
class RepositoryLocalDataSourceTest {
    abstract class DBTest {
        lateinit var repositoryLocalDataSource: RepositoryLocalDataSource

        @Before
        fun setUpParent() {
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            val db = Room.databaseBuilder(context, AppDatabase::class.java, "DB")
                .allowMainThreadQueries()
                .build()
            repositoryLocalDataSource = RepositoryLocalDataSource(db)
        }

        @After
        fun tearDownParent() { }
    }

    @RunWith(RobolectricTestRunner::class)
    class BlankRecord: DBTest() {
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

    @RunWith(RobolectricTestRunner::class)
    class RecordPrepared: DBTest() {
        @Before
        fun setUp() {
            repositoryLocalDataSource.insertAll(
                Repository(1, "hello", "hello", "lista"),
                Repository(2, "world", "world", "lista"),
                Repository(3, "yay!", "yay!", "mike")
            )
        }

        @Test
        fun findByOwner_givenLista_returnsSizeCount2() {
            val list = repositoryLocalDataSource.findByOwner("lista")
            assertThat(list).hasSize(2)
        }

        @Test
        fun findByOwner_givenMike_returnsSizeCount1() {
            val list = repositoryLocalDataSource.findByOwner("mike")
            assertThat(list).hasSize(1)
        }
    }
}
