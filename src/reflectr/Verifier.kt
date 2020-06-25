package reflectr

import reflectr.extensions.klass
import reflectr.models.Mapped
import reflectr.models.PropMap
import reflectr.models.extensions.ok
import reflectr.util.filterThenMapValues

/**
 * Verifies that a [Mapped] object's [String] properties conform to
 * any [check][check] annotation regexps
 *
 * @author Benjozork
 */
fun Mapped.verify(): Map<PropMap.PropertyHandle.Ok, Boolean> = this.propMap.ok
    .mapKeys { it.value } // Use property handles as keys
    .filterThenMapValues (
        { it.property.returnType.klass() == String::class },
        { (it.value.regexCheck?.let { regex -> (it.value.property.get(this) as String).matches(regex) } ?: true) }
    )
