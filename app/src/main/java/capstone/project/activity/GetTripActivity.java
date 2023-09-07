package capstone.project.activity;

import capstone.project.activity.requests.GetTripRequest;
import capstone.project.activity.results.GetTripResult;
import capstone.project.activity.results.SearchTripsResult;
import capstone.project.converters.ModelConverter;
import capstone.project.dynamodb.TripDao;
import capstone.project.dynamodb.models.Trip;
import capstone.project.models.TripModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

import static capstone.project.utils.NullUtils.ifNull;

public class GetTripActivity {
    private final TripDao tripDao;
    private final Logger log = LogManager.getLogger();

    @Inject
    public GetTripActivity(TripDao tripDao) {
        this.tripDao = tripDao;
    }
    public GetTripResult handleRequest(final GetTripRequest getTripRequest) {
        log.info("Received GetTripRequest {}", getTripRequest);
        String requestedTripId = getTripRequest.getTripId();
        String requestedMemberId=getTripRequest.getMemberId();
        Trip trip = tripDao.getTrip(requestedTripId,requestedMemberId);
        TripModel tripModel = new ModelConverter().toTripModel(trip);
        return GetTripResult.builder()
                .withTrip(tripModel)
                .build();

    }

}