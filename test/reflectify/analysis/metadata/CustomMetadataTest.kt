package reflectify.analysis.metadata

import reflectify.analysis.extensions.descriptor
import reflectify.analysis.models.PropertyDescriptor
import reflectify.analysis.models.metadata.MetadataProvider
import reflectify.analysis.models.metadata.PropertyMetadata
import reflectify.models.Mapped

import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CustomMetadataTest {

    @Target(AnnotationTarget.PROPERTY)
    @Retention
    private annotation class Important

    private class TestMetadata (
        val isImportant: Boolean
    ) : PropertyMetadata {

        object Provider : MetadataProvider<TestMetadata, KProperty<*>> {
            override fun provideFor(element: KProperty<*>): TestMetadata =
                TestMetadata(element.findAnnotation<Important>() != null)
        }

    }

    private class A (
       @Important val name: String
    ) : Mapped()

    private val PropertyDescriptor.status: TestMetadata
        get() = this.getOrMake(TestMetadata.Provider)

    @Test fun `should be able to find test metadata on property`() {
        assertTrue(A::name.descriptor.status.isImportant)
    }

}
