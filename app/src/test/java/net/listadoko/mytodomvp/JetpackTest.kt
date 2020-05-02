package net.listadoko.mytodomvp

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JetpackTest {
    @Test
    fun gettingContextTest() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val appName = context.getString(R.string.app_name)
        assertThat(appName).isEqualTo("MyTodoMvp")
    }

}