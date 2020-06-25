package reflectr

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import reflectr.annotations.Hidden
import reflectr.computed.models.Computed
import reflectr.entity.Entity
import reflectr.models.Mapped
import reflectr.models.extensions.valid

@ExperimentalStdlibApi
class SlicerTests {

    data class TestClass(
        val name: String,
        val age: Int,
        @Hidden val password: String
    ) : Entity()

    private val propMap = TestClass::class.propMap
    private val testObject = TestClass("abc", 17, "whatever")
    private val slicedObject = testObject.slice(propMap.map.keys - "uuid")
    private val sanitizedObject = testObject.sanitize()

    @Test
    fun `slice should include visible properties`() {
        assertTrue(slicedObject["name"] == "abc" && slicedObject["age"] == 17, "Should contain valid properties")
    }

    @Test
    fun `slice should not include invisible properties`() {
        assertTrue(slicedObject["password"] == null, "Should not contain password in main values")
        assertTrue(
            slicedObject["_accessDenied"] == setOf("password"),
            "Should contain invisible properties in _accessDenied"
        )
    }

    @Test
    fun `sanitize should include all visible properties`() {
        assertTrue(
            sanitizedObject.filterNot { it.key.startsWith('_') }.all { it.key in propMap.valid.keys },
            "All keys of DTO should be in propMap.valid()"
        )
    }

    data class MappedTestClass(
        val name: String,
        val age: Int,
        @Hidden val password: String
    ) : Mapped()

    private val mappedPropMap = MappedTestClass::class.propMap
    private val mappedTestObject =
        MappedTestClass("abc", 17, "whatever")
    private val mappedSlicedObject = mappedTestObject.slice(mappedPropMap.map.keys)

    @Test
    fun `should slice mapped object properly`() {
        assertTrue(
            mappedSlicedObject["name"] == "abc" && mappedSlicedObject["age"] == 17,
            "Should contain valid properties"
        )
    }

    @Test
    fun `should not include invisible properties in sliced mapped object`() {
        assertTrue(mappedSlicedObject["password"] == null, "Should not contain password in main values")
        assertTrue(
            mappedSlicedObject["_accessDenied"] == setOf("password"),
            "Should contain invisible properties in _accessDenied"
        )
    }

    data class BadMappedTestClass(
        val name: String,
        val age: Int,
        @Computed val password: String
    ) : Mapped()

    @Test
    fun `should throw an error when mapping a mapped class with a @Computed property`() {
        val exception = assertThrows<IllegalStateException>("Should throw an error when mapping the class") {
            BadMappedTestClass::class.propMap
        }

        assertEquals("@Computed properties can only appear on classes implementing Identified", exception.message)
    }

}
