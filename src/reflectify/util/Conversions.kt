package reflectify.util

import java.util.*

fun String.toUUID(): UUID = UUID.fromString(this)

