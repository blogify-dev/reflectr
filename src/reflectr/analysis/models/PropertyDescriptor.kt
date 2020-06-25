package reflectr.analysis.models

import reflectr.analysis.models.metadata.Metadata
import reflectr.analysis.models.metadata.MetadataProvider
import reflectr.analysis.models.metadata.PropertyMetadata

import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class PropertyDescriptor(val element: KProperty<*>) : ElementDescriptor<KProperty<*>>() {

    override val storedMetadata = mutableMapOf<KClass<out Metadata<KProperty<*>>>, Metadata<KProperty<*>>>()

    inline fun <reified TMetadata : PropertyMetadata> getOrMake (
        provider: MetadataProvider<TMetadata, KProperty<*>>
    ): TMetadata {
        return storedMetadata.getOrPut(TMetadata::class, { provider.provideFor(element) }) as TMetadata
    }

}
