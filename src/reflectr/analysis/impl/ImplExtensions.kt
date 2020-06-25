package reflectr.analysis.impl

import reflectr.analysis.models.PropertyDescriptor

val PropertyDescriptor.base: BaseMetadata
    get() = this.getOrMake(BaseMetadata.Provider)
