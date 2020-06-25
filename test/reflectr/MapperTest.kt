package reflectr

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import reflectr.annotations.Hidden
import reflectr.annotations.check
import reflectr.computed.models.Computed
import reflectr.entity.Entity
import reflectr.models.extensions.ok
import reflectr.models.extensions.valid
import kotlin.reflect.full.findAnnotation


class MapperTests {

    data class TestClass(val visible: String, @Hidden val invisible: String) : Entity()

    @Test
    fun `valid() should not return Invisible properties`() {
        val none = TestClass::class.propMap.valid
            .none { it.value.property.findAnnotation<Hidden>() !== null }

        assertTrue(none, "Should not contain @Invisible properties")
    }

    @Test
    fun `ok() should not return Invisible or Computed properties`() {
        val none = TestClass::class.propMap.ok
            .none { it.value.property.findAnnotation<Hidden>() != null || it.value.property.findAnnotation<Computed>() != null }

        assertTrue(none, "Should not contain @Invisible or @Computed properties")
    }

    data class TestClassWithRegexes(val noRegex: String, val withRegex: @check("[a-zA-Z0-9]{3}") String) : Entity()

    @Test
    fun `should pick up regexes`() {
        val prop = TestClassWithRegexes::class.propMap.ok.values.first { it.name == "withRegex" }

        val hasRegex = prop.regexCheck != null
        val regexPattern = prop.regexCheck?.pattern

        assertTrue(hasRegex, "Should have a non-null regex property")
        assertEquals("[a-zA-Z0-9]{3}", regexPattern)
    }

}
