package capstone.project.activity;

import capstone.project.activity.requests.UpdateTripMemberRequest;
import capstone.project.activity.requests.UpdateTripRequest;
import capstone.project.activity.results.UpdateTripMemberResult;
import capstone.project.activity.results.UpdateTripResult;
import capstone.project.converters.ModelConverter;
import capstone.project.dynamodb.TripDao;
import capstone.project.dynamodb.models.Trip;
import capstone.project.exceptions.TripNotFoundException;
import capstone.project.metrics.MetricsConstants;
import capstone.project.metrics.MetricsPublisher;

import javax.inject.Inject;

public class UpdateTripMemberActivity {
    private final TripDao tripDao;
    private final MetricsPublisher metricsPublisher;
    @Inject
    public UpdateTripMemberActivity(TripDao tripDao, MetricsPublisher metricsPublisher) {
        this.tripDao = tripDao;
        this.metricsPublisher = metricsPublisher;
    }
    public UpdateTripMemberResult handleRequest(final UpdateTripMemberRequest updateTripMemberRequest) {
        Trip trip = null;

        try {
            trip = tripDao.getTrip(updateTripMemberRequest.getTripId(),updateTripMemberRequest.getMemberId());
        }
        catch (TripNotFoundException e) {
            publishExceptionMetrics(true);
            throw new TripNotFoundException("You attempted to update a trip that does not exist", e.getCause());
        }
        publishExceptionMetrics(false);

        trip.setPickupDateTime(updateTripMemberRequest.getPickupDateTime());
        trip.setPickupStreetAddress(updateTripMemberRequest.getPickupStreetAddress());
        trip.setPickupCity(updateTripMemberRequest.getPickupCity());
        trip.setPickupState(updateTripMemberRequest.getPickupState());
        trip.setPickupZipCode(updateTripMemberRequest.getPickupZipCode());
        trip.setDropOffStreetAddress(updateTripMemberRequest.getDropOffStreetAddress());
        trip.setDropOffCity(updateTripMemberRequest.getDropOffCity());
        trip.setDropOffState(updateTripMemberRequest.getDropOffState());
        trip.setDropOffZipCode(updateTripMemberRequest.getDropOffZipCode());
        trip.setMobility(updateTripMemberRequest.getMobility());
        trip = tripDao.saveTrip(trip);

        return UpdateTripMemberResult.builder()
                .withTrip(new ModelConverter().toTripModel(trip))
                .build();
    }
    private void publishExceptionMetrics(final boolean isTripNotFound) {
        metricsPublisher.addCount(MetricsConstants.GETCAR_CARNOTFOUND_COUNT,
                isTripNotFound ? 1 : 0);
    }
}
