package reflectify.analysis.models.metadata

import reflectify.models.Mapped

import kotlin.reflect.KClass

interface ClassMetadata : Metadata<KClass<out Mapped>>
