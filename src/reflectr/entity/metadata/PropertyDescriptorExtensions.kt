package reflectr.entity.metadata

import reflectr.analysis.models.ClassDescriptor
import reflectr.analysis.models.PropertyDescriptor

val PropertyDescriptor.entity get() =
    this.getOrMake(EntityMetadata.Provider)

val ClassDescriptor.entity get() =
    this.getOrMake(EntityClassMetadata.Provider)
