package reflectify.entity.metadata

import reflectify.analysis.models.ClassDescriptor
import reflectify.analysis.models.PropertyDescriptor

val PropertyDescriptor.entity get() =
    this.getOrMake(EntityMetadata.Provider)

val ClassDescriptor.entity get() =
    this.getOrMake(EntityClassMetadata.Provider)
