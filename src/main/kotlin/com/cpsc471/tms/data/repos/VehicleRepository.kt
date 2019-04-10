package com.cpsc471.tms.data.repos

import com.cpsc471.tms.data.keys.VehicleKey
import com.cpsc471.tms.data.types.Vehicle
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface VehicleRepository : CrudRepository<Vehicle, VehicleKey> {

}