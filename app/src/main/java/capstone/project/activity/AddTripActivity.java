package capstone.project.activity;

import capstone.project.activity.requests.AddMemberRequest;
import capstone.project.activity.requests.AddTripRequest;
import capstone.project.activity.results.AddMemberResult;
import capstone.project.activity.results.AddTripResult;
import capstone.project.converters.ModelConverter;
import capstone.project.dynamodb.MemberDao;
import capstone.project.dynamodb.TripDao;
import capstone.project.dynamodb.models.Member;
import capstone.project.dynamodb.models.Trip;
import capstone.project.models.MemberModel;
import capstone.project.models.TripModel;

import javax.inject.Inject;

public class AddTripActivity {
    private final TripDao tripDao;

    @Inject
    public AddTripActivity(TripDao tripDao) {
        this.tripDao = tripDao;
    }
    public AddTripResult handleRequest(final AddTripRequest addTripRequest) {
        Trip newTrip = new Trip();
        newTrip.setTripId(addTripRequest.getTripId());
        newTrip.setMemberId(addTripRequest.getMemberId());
        newTrip.setPickupDateTime(addTripRequest.getPickupDateTime());
        newTrip.setPickupStreetAddress(addTripRequest.getPickupStreetAddress());
        newTrip.setPickupCity(addTripRequest.getPickupCity());
        newTrip.setPickupState(addTripRequest.getPickupState());
        newTrip.setPickupZipCode(addTripRequest.getPickupZipCode());
        newTrip.setDropOffStreetAddress(addTripRequest.getDropOffStreetAddress());
        newTrip.setDropOffCity(addTripRequest.getDropOffCity());
        newTrip.setDropOffState(addTripRequest.getDropOffState());
        newTrip.setDropOffZipCode(addTripRequest.getDropOffZipCode());
        newTrip.setMobility(addTripRequest.getMobility());
        newTrip.setDriverId(addTripRequest.getDriverId());
        newTrip.setSelected(addTripRequest.getSelected());
        tripDao.saveTrip(newTrip);

        TripModel tripModel = new ModelConverter().toTripModel(newTrip);
        return AddTripResult.builder()
                .withTrip(tripModel)
                .build();

    }
}
