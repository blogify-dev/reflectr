package reflectr.entity.metadata

import reflectr.analysis.models.metadata.MetadataProvider
import reflectr.analysis.models.metadata.PropertyMetadata
import reflectr.annotations.Hidden
import reflectr.entity.annotations.NotUpdatable

import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

/**
 * Metadata for reflectr entities.
 *
 * @author Benjozork
 *
 * @property isVisible is `false` when this element should be prohibited from appearing in DTOs or be accepted
 *                  during entity creation or update
 */
class EntityMetadata (
    val isVisible: Boolean,
    val isUpdatable: Boolean
) : PropertyMetadata {

    object Provider : MetadataProvider<EntityMetadata, KProperty<*>> {

        override fun provideFor(element: KProperty<*>): EntityMetadata =
            EntityMetadata (
                isVisible = !element.isHidden,
                isUpdatable = !element.isUpdatable && !element.isHidden
            )

        private val KProperty<*>.isHidden get() = this.findAnnotation<Hidden>() != null

        private val KProperty<*>.isUpdatable get() = this.findAnnotation<NotUpdatable>() != null

    }

}
