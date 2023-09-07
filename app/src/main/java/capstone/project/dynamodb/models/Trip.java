package capstone.project.dynamodb.models;

import capstone.project.converters.ZonedDateTimeConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@DynamoDBTable(tableName = "trips")

public class Trip {
private static final String Trips_Member_INDEX = "TripsMemberIndex";
private static final String Trips_Driver_INDEX = "TripsDriverIndex";
    private String tripId;
    private String memberId;
    private String pickupDateTime;
    private String pickupStreetAddress;
    private String pickupCity;
    private String pickupState;
    private String pickupZipCode;
    private String dropOffStreetAddress;
    private String dropOffCity;
    private String dropOffState;
    private String dropOffZipCode;
    private String mobility;
    private String driverId;
    private String selected;

    @DynamoDBHashKey(attributeName = "tripId")
    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    @DynamoDBRangeKey(attributeName = "memberId")
    @DynamoDBIndexHashKey(globalSecondaryIndexName =Trips_Member_INDEX, attributeName = "memberId")
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
   // @DynamoDBTypeConverted(converter = ZonedDateTimeConverter.class)
    @DynamoDBAttribute(attributeName = "pickupDateTime")
    @DynamoDBIndexRangeKey(globalSecondaryIndexNames = Trips_Member_INDEX, attributeName = "pickupDateTime")
    public String getPickupDateTime() {
        return pickupDateTime;
    }

    public void setPickupDateTime(String pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    @DynamoDBAttribute(attributeName = "pickupStreetAddress")
    public String getPickupStreetAddress() {
        return pickupStreetAddress;
    }

    public void setPickupStreetAddress(String pickupStreetAddress) {
        this.pickupStreetAddress = pickupStreetAddress;
    }

    @DynamoDBAttribute(attributeName = "pickupCity")
    public String getPickupCity() {
        return pickupCity;
    }

    public void setPickupCity(String pickupCity) {
        this.pickupCity = pickupCity;
    }

    @DynamoDBAttribute(attributeName = "pickupState")
    public String getPickupState() {
        return pickupState;
    }

    public void setPickupState(String pickupState) {
        this.pickupState = pickupState;
    }

    @DynamoDBAttribute(attributeName = "pickupZipCode")
    @DynamoDBIndexHashKey(globalSecondaryIndexName =Trips_Driver_INDEX, attributeName = "pickupZipCode")
    public String getPickupZipCode() {
        return pickupZipCode;
    }

    public void setPickupZipCode(String pickupZipCode) {
        this.pickupZipCode = pickupZipCode;
    }

    @DynamoDBAttribute(attributeName = "dropOffStreetAddress")
    public String getDropOffStreetAddress() {
        return dropOffStreetAddress;
    }

    public void setDropOffStreetAddress(String dropOffStreetAddress) {
        this.dropOffStreetAddress = dropOffStreetAddress;
    }

    @DynamoDBAttribute(attributeName = "dropOffCity")
    public String getDropOffCity() {
        return dropOffCity;
    }

    public void setDropOffCity(String dropOffCity) {
        this.dropOffCity = dropOffCity;
    }

    @DynamoDBAttribute(attributeName = "dropOffState")
    public String getDropOffState() {
        return dropOffState;
    }

    public void setDropOffState(String dropOffState) {
        this.dropOffState = dropOffState;
    }

    @DynamoDBAttribute(attributeName = "dropOffZipCode")
    public String getDropOffZipCode() {
        return dropOffZipCode;
    }

    public void setDropOffZipCode(String dropOffZipCode) {
        this.dropOffZipCode = dropOffZipCode;
    }

    @DynamoDBAttribute(attributeName = "mobility")
    public String getMobility() {
        return mobility;
    }

    public void setMobility(String mobility) {
        this.mobility = mobility;
    }

    @DynamoDBAttribute(attributeName = "driverId")
    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    @DynamoDBAttribute(attributeName = "selected")
    @DynamoDBIndexRangeKey(globalSecondaryIndexNames = Trips_Driver_INDEX, attributeName = "selected")
    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripId='" + tripId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", pickupDateTime='" + pickupDateTime + '\'' +
                ", pickupStreetAddress='" + pickupStreetAddress + '\'' +
                ", pickupCity='" + pickupCity + '\'' +
                ", pickupState='" + pickupState + '\'' +
                ", pickupZipCode='" + pickupZipCode + '\'' +
                ", dropOffStreetAddress='" + dropOffStreetAddress + '\'' +
                ", dropOffCity='" + dropOffCity + '\'' +
                ", dropOffState='" + dropOffState + '\'' +
                ", dropOffZipCode='" + dropOffZipCode + '\'' +
                ", mobility='" + mobility + '\'' +
                ", driverId='" + driverId + '\'' +
                ", selected=" + selected +
                '}';
    }
}
