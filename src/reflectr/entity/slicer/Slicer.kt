package reflectr.entity.slicer

import reflectr.entity.metadata.entity
import reflectr.util.Dto
import reflectr.analysis.extensions.descriptor
import reflectr.models.Mapped

import kotlin.reflect.KProperty1

inline fun <reified TMapped : Mapped> TMapped.slice (
    selectedProperties: Array<out KProperty1<TMapped, Any?>> = emptyArray(),
    unsafe: Boolean = false
): Dto {
    val props = TMapped::class.descriptor.propertyDescriptors
    val dto = mutableMapOf<String, Any?>()

    val propIterator =
        if (selectedProperties.isEmpty() || unsafe)
            props
        else
            props.filter { it.key.name in selectedProperties.map { p -> p.name } }

    for (prop in propIterator) {
        if (prop.value.entity.isVisible)
            dto[prop.key.name] = prop.key.get(this)
    }

    return dto
}
