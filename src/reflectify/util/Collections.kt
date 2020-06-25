package reflectify.util

/**
 * Returns with only the entries whose key is not `null`.
 */
@Suppress("UNCHECKED_CAST")
fun <K : Any, V : Any> Map<K, V?>.withoutNullValues() =
    this.filterValues { it != null }.toMap() as Map<K, V>


fun <K : Any, V : Any, R : Any> Map<K, V>.filterThenMapValues (
    predicate: (V) -> Boolean,
    mapper:    (Map.Entry<K, V>) -> R
): Map<K, R> {
    return this.filterValues(predicate).mapValues(mapper)
}
