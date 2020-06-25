package reflectr.analysis.models.metadata

import reflectr.models.Mapped

import kotlin.reflect.KClass

interface ClassMetadata : Metadata<KClass<out Mapped>>
