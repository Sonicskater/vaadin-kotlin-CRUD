package com.cpsc471.tms.data.keys

import com.cpsc471.tms.ui.components.Display
import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
class InstituteKey(
        @Display
        var name : String = "",
        @Display
        var postalCode : String = ""
) : Serializable