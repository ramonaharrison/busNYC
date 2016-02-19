package com.ramonaharrison.busnyc.model.remote;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ramonaharrison.busnyc.model.object.Bus;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ramona Harrison
 * on 2/18/16.
 */

public interface VehicleMonitoringService
{
    /**
     * Params:
     *
     * key - your MTA Bus Time developer API key (required).
     * version - which version of the SIRI API to use (1 or 2). Defaults to 1, but 2 is preferable.
     * OperatorRef - the GTFS agency ID to be monitored (optional, currently MTA)
     * VehicleRef - the ID of the vehicle to be monitored (optional).  This is the 4-digit number painted on the side of the bus, for example 7560. Response will include all buses if not included.
     * LineRef - a filter by 'fully qualified' route name, GTFS agency ID + route ID (optional).
     * DirectionRef - a filter by GTFS direction ID (optional).  Either 0 or 1.
     * VehicleMonitoringDetailLevel - Level of detail present in response. In order of verbosity:
     *      basic - only available in version 2.
     *      normal - default.
     * calls Determines whether or not the response will include the stops ("calls" in SIRI-speak) each vehicle is going to make after it serves the selected stop (optional).
     * MaximumNumberOfCallsOnwards Limit on the number of OnwardCall elements for each vehicle when VehicleMonitoringDetailLevel=calls
     * MaximumStopVisits - an upper bound on the number of buses to return in the results.
     * MinimumStopVisitsPerLine - a lower bound on the number of buses to return in the results per line/route.
     */

    String ENDPOINT = "http://bustime.mta.info";

    String KEY = "key";
    String VERSION = "version";
    String OPERATOR_REF = "OperatorRef";
    String VEHICLE_REF = "VehicleRef";
    String LINE_REF = "LineRef";
    String DIRECTION_REF = "DirectionRef";
    String VEHICLE_DETAIL_LEVEL ="VehicleMonitoringDetailLevel";
    String CALLS = "calls";
    String MAX_ONWARD_CALLS = "MaximumNumberOfCallsOnwards";
    String MAX_STOPS = "MaximumStopVisits";
    String MIN_STOPS = "MinimumStopVisitsPerLine";

    @GET("/api/siri/vehicle-monitoring.json")
    Observable<List<Bus>> getBusesForLine(@Query(KEY) String API_KEY, @Query(LINE_REF) String lineRef);


    class Creator {
        public static VehicleMonitoringService newVehicleMonitoringService() {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(VehicleMonitoringService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(VehicleMonitoringService.class);
        }
    }
}
