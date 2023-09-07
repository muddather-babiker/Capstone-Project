package capstone.project.activity;

import capstone.project.activity.requests.SearchTripsDriverRequest;
import capstone.project.activity.requests.SearchTripsRequest;
import capstone.project.activity.results.SearchTripsDriverResult;
import capstone.project.activity.results.SearchTripsResult;
import capstone.project.converters.ModelConverter;
import capstone.project.dynamodb.TripDao;
import capstone.project.dynamodb.models.Trip;
import capstone.project.models.TripModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class SearchTripsDriverActivity {
    private final TripDao tripDao;
    private final Logger log = LogManager.getLogger();
    @Inject
    public SearchTripsDriverActivity(TripDao tripDao) {
        this.tripDao = tripDao;
    }
    public SearchTripsDriverResult handleRequest(final SearchTripsDriverRequest searchTripsDriverRequest) {
        String requestedPickupZipCode = searchTripsDriverRequest.getPickupZipCode();
        String requestedSelected=searchTripsDriverRequest.getSelected();
        log.info("this is the requestedPickupZipCode"+ requestedPickupZipCode);
        log.info("this is the requestedSelected"+ requestedSelected);
        List<Trip> results = tripDao.searchTripsDriver(requestedPickupZipCode,requestedSelected);
        List<TripModel> tripModels = new ModelConverter().toTripModelList(results);

        return SearchTripsDriverResult.builder()
                .withTrips(tripModels)
                .build();
    }
}
