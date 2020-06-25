package reflectify.annotations

/**
 * Marks a property of a [reflectify.reflect.models.Mapped] to not be included in a [propMap][reflectify.reflect.models.PropMap]
 *
 * @author Benjozork
 */
@Suppress("ClassName")
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Hidden
