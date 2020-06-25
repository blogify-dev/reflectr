package reflectify.analysis.extensions

import reflectify.analysis.ClassAnalysisCache
import reflectify.models.Mapped

import kotlin.reflect.KClass

val KClass<out Mapped>.descriptor get() = ClassAnalysisCache[this]
