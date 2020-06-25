package reflectify.analysis.impl

import reflectify.analysis.models.metadata.MetadataProvider
import reflectify.analysis.models.metadata.PropertyMetadata

import kotlin.reflect.KProperty

class BaseMetadata : PropertyMetadata {

    object Provider : MetadataProvider<BaseMetadata, KProperty<*>> {

        override fun provideFor(element: KProperty<*>): BaseMetadata =
            BaseMetadata()

    }

}
