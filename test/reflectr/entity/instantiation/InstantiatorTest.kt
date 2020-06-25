package reflectr.entity.instantiation

import reflectr.annotations.Hidden
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import reflectr.entity.mappedByHandles

import kotlinx.coroutines.runBlocking

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import reflectr.entity.Entity
import reflectr.util.Wrap
import reflectr.util.assertGet
import reflectr.util.getOr
import reflectr.util.never

@ExperimentalStdlibApi
class InstantiatorTests {

    data class TestClass(
        val name: String,
        val age: Int = 25,
        @Hidden val password: String
    ) : Entity()

    private val testObject =
        TestClass("abc", 17, "whatever")
    private val testData = mapOf(
        "name" to "abc",
        "age" to 17,
        "password" to "whatever"
    )

    @Test
    fun `instantiate should create object correctly`() {
        runBlocking {
            val propHandleDto = testData.mappedByHandles(TestClass::class, unsafe = true).getOr { never }
            val newInstance = TestClass::class.construct(propHandleDto, jacksonObjectMapper())

            assertEquals(testObject, newInstance.get())
        }
    }

    private val testObjectDefaultAge =
        TestClass(
            name = "abc",
            password = "whatever"
        )
    private val testDataNoAge = mapOf(
        "name" to "abc",
        "password" to "whatever"
    )

    @Test
    fun `instantiate should create object correctly while omitting values for default properties`() {
        runBlocking {
            val propHandleDto = testDataNoAge.mappedByHandles(TestClass::class, unsafe = true).getOr { never }
            val newInstance = TestClass::class.construct(propHandleDto, jacksonObjectMapper())

            assertEquals(testObjectDefaultAge, newInstance.get())
        }
    }

    data class OtherTestClass(
        val data: Int,
        val other: TestClass
    ) : Entity()

    private val testObjectForOther =
        TestClass("me", 18, "hunter2")
    private val otherTestObject =
        OtherTestClass(1337, testObjectForOther)
    private val otherTestData = mapOf(
        "data" to 1337,
        "other" to testObjectForOther.uuid
    )

    @Test
    fun `instantiate should create object correctly with external fetching`() {
        runBlocking {
            val propHandleDto = otherTestData.mappedByHandles(OtherTestClass::class, unsafe = true).assertGet()
            val newInstance = OtherTestClass::class.construct(
                propHandleDto,
                jacksonObjectMapper(),
                { _, _ -> Wrap { testObjectForOther } })

            assertEquals(otherTestObject, newInstance.get())
        }
    }

}
