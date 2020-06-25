package reflectr.analysis.impl

import reflectr.analysis.models.metadata.MetadataProvider
import reflectr.analysis.models.metadata.PropertyMetadata

import kotlin.reflect.KProperty

class BaseMetadata : PropertyMetadata {

    object Provider : MetadataProvider<BaseMetadata, KProperty<*>> {

        override fun provideFor(element: KProperty<*>): BaseMetadata =
            BaseMetadata()

    }

}
