package reflectr.entity.metadata

import reflectr.analysis.models.metadata.ClassMetadata
import reflectr.analysis.models.metadata.MetadataProvider
import reflectr.annotations.search.NoSearch
import reflectr.models.Mapped

import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

/**
 * Metadata for reflectr entities.
 *
 * @author Benjozork
 *
 * @property isSearchable is `false` when this element type cannot have a search performed on it
 */
class EntityClassMetadata (
    val isSearchable: Boolean
) : ClassMetadata {

    object Provider : MetadataProvider<EntityClassMetadata, KClass<out Mapped>> {

        override fun provideFor(element: KClass<out Mapped>): EntityClassMetadata =
            EntityClassMetadata (
                isSearchable = element.isSearchable
            )

        private val KClass<out Mapped>.isSearchable get() = this.findAnnotation<NoSearch>() == null

    }

}
