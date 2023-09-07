package capstone.project.activity;

import capstone.project.activity.requests.UpdateTripRequest;
import capstone.project.activity.results.UpdateTripResult;
import capstone.project.converters.ModelConverter;
import capstone.project.dynamodb.TripDao;
import capstone.project.dynamodb.models.Trip;
import capstone.project.exceptions.TripNotFoundException;
import capstone.project.metrics.MetricsConstants;
import capstone.project.metrics.MetricsPublisher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateTripActivity {
    private final Logger log = LogManager.getLogger();
    private final TripDao tripDao;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public UpdateTripActivity(TripDao tripDao, MetricsPublisher metricsPublisher) {
        this.tripDao = tripDao;
        this.metricsPublisher = metricsPublisher;
    }
    public UpdateTripResult handleRequest(final UpdateTripRequest updateTripRequest) {
        log.info("Received UpdateTripRequest {}", updateTripRequest);
        Trip trip = null;

        try {
          trip = tripDao.getTrip(updateTripRequest.getTripId(),updateTripRequest.getMemberId());
        }
        catch (TripNotFoundException e) {
            publishExceptionMetrics(true);
            throw new TripNotFoundException("You attempted to update a trip that does not exist", e.getCause());
        }
        publishExceptionMetrics(false);

        trip.setDriverId(updateTripRequest.getDriverId());
        trip.setSelected(updateTripRequest.getSelected());

        trip = tripDao.saveTrip(trip);

        return UpdateTripResult.builder()
                .withTrip(new ModelConverter().toTripModel(trip))
                .build();
    }
    private void publishExceptionMetrics(final boolean isTripNotFound) {
        metricsPublisher.addCount(MetricsConstants.GETCAR_CARNOTFOUND_COUNT,
                isTripNotFound ? 1 : 0);
    }
}
