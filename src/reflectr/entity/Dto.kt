package reflectr.entity

import reflectr.util.Sr
import reflectr.util.WrapBlocking
import reflectr.util.MappedData
import reflectr.util.Dto
import reflectr.models.Mapped
import reflectr.models.PropMap
import reflectr.models.extensions.ok
import reflectr.propMap
import reflectr.unsafePropMap

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
                .firstOrNull { it.name == key } ?: error("unknown key '$key'")) to value
        }.toMap()
    }
}
