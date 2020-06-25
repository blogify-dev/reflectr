package reflectify.analysis.extensions

import reflectify.analysis.models.PropertyDescriptor
import reflectify.extensions.klass
import reflectify.models.Mapped

import kotlin.reflect.KProperty1

val KProperty1<out Mapped, Any?>.descriptor: PropertyDescriptor get() =
    this.klass.descriptor.propertyDescriptors[this]!!
