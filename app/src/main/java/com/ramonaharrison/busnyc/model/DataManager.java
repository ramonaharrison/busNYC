package com.ramonaharrison.busnyc.model;
import com.ramonaharrison.busnyc.model.remote.StopMonitoringService;
import com.ramonaharrison.busnyc.model.remote.VehicleMonitoringService;

/**
 * Created by Ramona Harrison
 * on 2/18/16.
 */

public class DataManager
{
    private static VehicleMonitoringService vehicleMonitoringService;
    private static StopMonitoringService    stopMonitoringService;

    private static String ENDPOINT = "http://bustime.mta.info";



}
