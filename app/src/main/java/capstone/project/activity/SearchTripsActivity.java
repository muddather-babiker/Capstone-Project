package capstone.project.activity;

import capstone.project.activity.requests.SearchTripsRequest;
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

public class SearchTripsActivity {
    private final TripDao tripDao;
    private final Logger log = LogManager.getLogger();


    @Inject
    public SearchTripsActivity(TripDao tripDao) {
        this.tripDao = tripDao;
    }
    public SearchTripsResult handleRequest(final SearchTripsRequest searchTripsRequest) {
        String requestedMemberId = searchTripsRequest.getMemberId();
        String requestedPickupDateTime=searchTripsRequest.getPickupDateTime();
        log.info("this is the requestedMemberId"+ requestedMemberId);
        log.info("this is the requestedMemberId"+ requestedPickupDateTime);
        List<Trip> results = tripDao.searchTrips(requestedMemberId,requestedPickupDateTime);

        List<TripModel> tripModels = new ModelConverter().toTripModelList(results);

        return SearchTripsResult.builder()
                .withTrips(tripModels)
                .build();
    }

}

