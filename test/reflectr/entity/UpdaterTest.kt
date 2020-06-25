package reflectr.entity

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import reflectr.annotations.Hidden
import reflectr.models.extensions.ok
import reflectr.unsafePropMap
import reflectr.util.Sr

@ExperimentalStdlibApi
@Suppress("MapGetWithNotNullAssertionOperator")
class UpdaterTests {

    data class TestClass(
        val name: String,
        val age: Int,
        @Hidden val password: String
    ) : Entity()

    private val propMap = TestClass::class.unsafePropMap
    private val testObject = TestClass("abc", 17, "whatever")
    private val testUpdateData = mapOf(
        propMap.ok["name"]!! to "steven",
        propMap.ok["age"]!! to 18,
        propMap.ok["password"]!! to "ha"
    )

    @Test
    fun `should update mapped object`() {
        runBlocking {
            val updated = testObject.update(testUpdateData, jacksonObjectMapper()) { _, _ -> Sr.of { error("") } }

            assertEquals("steven", updated.get().name)
            assertEquals(18, updated.get().age)
            assertEquals("ha", updated.get().password)
        }
    }

    private val testObjectUnchanged =
        TestClass("def", 21, "dontlook")
    private val testUpdateDataUnchanged = mapOf(
        propMap.ok["name"]!! to "wow",
        propMap.ok["age"]!! to 36,
        propMap.ok["password"]!! to "ho"
    )

    @Test
    fun `should keep unchanged values`() {
        runBlocking {
            val updated = testObjectUnchanged.update(
                testUpdateDataUnchanged,
                jacksonObjectMapper()
            ) { _, _ -> Sr.of { error("") } }

            assertEquals("wow", updated.get().name)
            assertEquals(36, updated.get().age)
            assertEquals("ho", updated.get().password)
        }
    }

}
