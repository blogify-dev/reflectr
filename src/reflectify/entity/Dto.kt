package reflectify.entity

import reflectify.util.Sr
import reflectify.util.WrapBlocking
import reflectify.util.MappedData
import reflectify.util.Dto
import reflectify.models.Mapped
import reflectify.models.PropMap
import reflectify.models.extensions.ok
import reflectify.propMap
import reflectify.unsafePropMap

import kotlin.reflect.KClass

/**
 * Attempts to turn a [Dto]'s String keys into [property handles][PropMap.PropertyHandle.Ok] of [klass]
 *
 * @author Benjozork
 */
fun <TMapped : Mapped> Dto.mappedByHandles(klass: KClass<TMapped>, unsafe: Boolean = false): Sr<MappedData> {
    return WrapBlocking {
        this.map { (key, value) ->
            ((if (!unsafe) klass.propMap else klass.unsafePropMap)
                .ok.values
                .firstOrNull { it.name == key } ?: kotlin.error("unknown key '$key'")) to value
        }.toMap()
    }
}
