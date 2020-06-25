package reflectify.annotations

/**
 * Marks a property of a [reflectify.models.Mapped] to not be included in a [slice][reflectify.slice]
 *
 * @author Benjozork
 */
@Suppress("ClassName")
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Undisplayed
