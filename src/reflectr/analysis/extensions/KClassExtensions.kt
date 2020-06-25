package reflectr.analysis.extensions

import reflectr.analysis.ClassAnalysisCache
import reflectr.models.Mapped

import kotlin.reflect.KClass

val KClass<out Mapped>.descriptor get() = ClassAnalysisCache[this]
