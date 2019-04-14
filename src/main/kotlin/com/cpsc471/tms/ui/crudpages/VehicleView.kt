package com.cpsc471.tms.ui.crudpages

import com.cpsc471.tms.data.repository.vehicles.Vehicle
import com.cpsc471.tms.data.repository.vehicles.VehicleRepository
import com.cpsc471.tms.ui.BaseAppLayout
import com.cpsc471.tms.ui.templates.CrudPage
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired

@Route("vehicles", layout = BaseAppLayout::class)
class VehicleView(@Autowired repo: VehicleRepository) : VerticalLayout() {

    init {
        add(CrudPage(Vehicle::class.java,repo))
    }
}
