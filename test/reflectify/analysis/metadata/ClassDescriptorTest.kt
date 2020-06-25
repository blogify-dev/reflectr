package reflectify.analysis.metadata

import reflectify.analysis.extensions.descriptor
import reflectify.analysis.models.PropertyDescriptor
import reflectify.models.Mapped

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.reflect.KProperty1

class ClassDescriptorTest {

    private class A (
        val name: String,
        val age: Int
    ) : Mapped()

    @Test fun `should find class descriptor for simple class`() {
        assertDoesNotThrow {
            A::class.descriptor
        }
    }

    @Test fun `should have all property descriptors in simple class descriptor`() {
        val descriptor = A::class.descriptor

        arrayOf(A::name, A::age).forEach { property ->
            assertTrue(descriptor.propertyDescriptors.contains<KProperty1<*, *>, PropertyDescriptor>(property))
        }
    }

}
