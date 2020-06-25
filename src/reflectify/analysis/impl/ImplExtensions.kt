package reflectify.analysis.impl

import reflectify.analysis.models.PropertyDescriptor

val PropertyDescriptor.base: BaseMetadata
    get() = this.getOrMake(BaseMetadata.Provider)
