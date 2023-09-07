package capstone.project.activity;

import capstone.project.activity.requests.RemoveTripRequest;
import capstone.project.activity.results.RemoveTripResult;
import capstone.project.converters.ModelConverter;
import capstone.project.dynamodb.TripDao;
import capstone.project.dynamodb.models.Trip;
import capstone.project.models.TripModel;

import javax.inject.Inject;

public class RemoveTripActivity {
    private final TripDao tripDao;

    @Inject
    public RemoveTripActivity(TripDao tripDao) {
        this.tripDao = tripDao;
    }
    public RemoveTripResult handleRequest(final RemoveTripRequest removeTripRequest) {
        String requestedTripId = removeTripRequest.getTripId();
        String requestedMemberId = removeTripRequest.getMemberId();
        Trip trip = tripDao.removeTrip(requestedTripId,requestedMemberId);
        TripModel tripModel = new ModelConverter().toTripModel(trip);
        return RemoveTripResult.builder()
                .withTrip(tripModel)
                .build();
    }
}
