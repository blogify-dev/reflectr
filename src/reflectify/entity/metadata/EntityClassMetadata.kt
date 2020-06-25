package reflectify.entity.metadata

import reflectify.analysis.models.metadata.ClassMetadata
import reflectify.analysis.models.metadata.MetadataProvider
import reflectify.annotations.search.NoSearch
import reflectify.models.Mapped

import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

/**
 * Metadata for reflectify entities.
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
