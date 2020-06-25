package reflectr.util

import com.andreapivetta.kolor.red
import reflectr.models.PropMap
import kotlin.system.exitProcess

/**
 * If this property is evaluated and the `BLOGIFY_ENABLE_ASSERTIONS` env variable is set to 1, the server immediately dies.
 * Should be used as the right-hand side of Elvis expressions, like in the following :
 * `shouldNeverReturnNull() ?: never`. Useful for representing states that should never be reached.
 *
 * @author Benjozork
 */
val never: Nothing get() {
    println("### FATAL ERROR : assertion failed - shutting down system! stack-trace : ###".red())

    Exception().printStackTrace()

    if (System.getenv("BLOGIFY_ENABLE_ASSERTIONS")?.isNotBlank() == true) exitProcess(155)
    else error("fatal: exception thrown due to failed assertion".red())
}

/**
 * A map of property names to associated values
 */
typealias Dto = Map<String, Any?>

/**
 * A map of [property handles][PropMap.PropertyHandle.Ok] to associated values
 */
typealias MappedData = Map<PropMap.PropertyHandle.Ok, Any?>
