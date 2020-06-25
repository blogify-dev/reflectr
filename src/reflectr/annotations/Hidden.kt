package reflectr.annotations

/**
 * Marks a property of a [reflectr.models.Mapped] to not be included in a [propMap][reflectr.models.PropMap]
 *
 * @author Benjozork
 */
@Suppress("ClassName")
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Hidden
