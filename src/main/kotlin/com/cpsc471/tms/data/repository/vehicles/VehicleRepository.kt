package com.cpsc471.tms.data.repository.vehicles

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface VehicleRepository : CrudRepository<Vehicle, VehicleKey> {

}