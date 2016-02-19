package com.ramonaharrison.busnyc.model;
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

public interface StopMonitoringService
{
    /**
     * Params:
     *
     * key - your MTA Bus Time developer API key (required).  Go here to get one.
     * version - which version of the SIRI API to use (1 or 2). Defaults to 1, but 2 is preferrable.
     * OperatorRef - the GTFS agency ID to be monitored (optional).  Currently, all stops have operator/agency ID of MTA. If left out, the system will make a best guess. Usage of the OperatorRef is suggested, as calls will return faster when populated.
     * MonitoringRef - the GTFS stop ID of the stop to be monitored (required).  For example, 308214 for the stop at 5th Avenue and Union St towards Bay Ridge.
     * LineRef - a filter by 'fully qualified' route name, GTFS agency ID + route ID (e.g. MTA NYCT_B63).
     * DirectionRef - a filter by GTFS direction ID (optional).  Either 0 or 1.
     * StopMonitoringDetailLevel - Level of detail present in response. In order of verbosity:
     *      basic - only available in version 2.
     *      normal - default.
     * calls Determines whether or not the response will include the stops ("calls" in SIRI-speak) each vehicle is going to make after it serves the selected stop (optional).
     * MaximumNumberOfCallsOnwards - Limits the number of OnwardCall elements returned in the query.
     * MaximumStopVisits - an upper bound on the number of buses to return in the results.
     * MinimumStopVisitsPerLine - a lower bound on the number of buses to return in the results per line/route (assuming that many are available)
     */

    String ENDPOINT = "http://bustime.mta.info";

    String KEY = "key";
    String VERSION = "version";
    String OPERATOR_REF = "OperatorRef";
    String STOP_REF = "MonitoringRef";
    String LINE_REF = "LineRef";
    String DIRECTION_REF = "DirectionRef";
    String STOP_DETAIL_LEVEL ="StopMonitoringDetailLevel";
    String CALLS = "calls";
    String MAX_ONWARD_CALLS = "MaximumNumberOfCallsOnwards";
    String MAX_STOPS = "MaximumStopVisits";
    String MIN_STOPS = "MinimumStopVisitsPerLine";

    @GET("/api/siri/stop-monitoring.json")
    Observable<List<Bus>> getBusesForStop(@Query(KEY) String API_KEY, @Query(STOP_REF) String stopRef);


    class Creator {
        public static StopMonitoringService newStopMonitoringService() {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(StopMonitoringService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(StopMonitoringService.class);
        }
    }
}
