package capstone.project.converters;

import capstone.project.dynamodb.models.Driver;
import capstone.project.dynamodb.models.Member;
import capstone.project.dynamodb.models.Trip;
import capstone.project.models.DriverModel;
import capstone.project.models.MemberModel;
import capstone.project.models.TripModel;

import java.util.ArrayList;
import java.util.List;

public class ModelConverter {
    public DriverModel toDriverModel(Driver driver) {

        return DriverModel.builder()
                .withDriverEmail(driver.getDriverEmail())
                .withName(driver.getName())
                .withPhoneNumber(driver.getPhoneNumber())
                .withDateOfBirth(driver.getDateOfBirth())
                .withStreetAddress(driver.getStreetAddress())
                .withCity(driver.getCity())
                .withState(driver.getState())
                .withZipCode(driver.getZipCode())
                .build();
    }
    public MemberModel toMemberModel(Member member) {

        return MemberModel.builder()
                .withMemberEmail(member.getMemberEmail())
                .withName(member.getName())
                .withPhoneNumber(member.getPhoneNumber())
                .withDateOfBirth(member.getDateOfBirth())
                .withStreetAddress(member.getStreetAddress())
                .withCity(member.getCity())
                .withState(member.getState())
                .withZipCode(member.getZipCode())
                .build();
    }
    public TripModel toTripModel(Trip trip) {

        return TripModel.builder()
                .withTripId(trip.getTripId())
                .withMemberId(trip.getMemberId())
                .withPickupDateTime(trip.getPickupDateTime())
                .withPickupStreetAddress(trip.getPickupStreetAddress())
                .withPickupCity(trip.getPickupCity())
                .withPickupState(trip.getPickupState())
                .withPickupZipCode(trip.getPickupZipCode())
                .withDropOffStreetAddress(trip.getDropOffStreetAddress())
                .withDropOffCity(trip.getDropOffCity())
                .withDropOffState(trip.getDropOffState())
                .withDropOffZipCode(trip.getDropOffZipCode())
                .withMobility(trip.getMobility())
                .withDriverId(trip.getDriverId())
                .withSelected(trip.getSelected())
                .build();
    }
    public List<TripModel> toTripModelList(List<Trip> trips) {
        List<TripModel> tripModels = new ArrayList<>();

        for (Trip trip : trips) {
            tripModels.add(toTripModel(trip));
        }

        return tripModels;
    }
}
