package reflectr.analysis.extensions

import reflectr.analysis.models.PropertyDescriptor
import reflectr.extensions.klass
import reflectr.models.Mapped

import kotlin.reflect.KProperty1

val KProperty1<out Mapped, Any?>.descriptor: PropertyDescriptor get() =
    this.klass.descriptor.propertyDescriptors[this]!!
