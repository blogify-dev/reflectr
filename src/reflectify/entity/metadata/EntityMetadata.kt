package reflectify.entity.metadata

import reflectify.analysis.models.metadata.MetadataProvider
import reflectify.analysis.models.metadata.PropertyMetadata
import reflectify.annotations.Hidden
import reflectify.entity.annotations.NotUpdatable

import kotlin.reflect.KProperty
import kotlin.reflect.full.findAnnotation

/**
 * Metadata for reflectify entities.
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
