package capstone.project.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateTripMemberRequest.Builder.class)
public class UpdateTripMemberRequest {
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

    public UpdateTripMemberRequest(String tripId, String memberId, String pickupDateTime, String pickupStreetAddress, String pickupCity, String pickupState, String pickupZipCode, String dropOffStreetAddress, String dropOffCity, String dropOffState, String dropOffZipCode, String mobility) {
        this.tripId = tripId;
        this.memberId = memberId;
        this.pickupDateTime = pickupDateTime;
        this.pickupStreetAddress = pickupStreetAddress;
        this.pickupCity = pickupCity;
        this.pickupState = pickupState;
        this.pickupZipCode = pickupZipCode;
        this.dropOffStreetAddress = dropOffStreetAddress;
        this.dropOffCity = dropOffCity;
        this.dropOffState = dropOffState;
        this.dropOffZipCode = dropOffZipCode;
        this.mobility = mobility;
    }

    public String getTripId() {
        return tripId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getPickupDateTime() {
        return pickupDateTime;
    }

    public String getPickupStreetAddress() {
        return pickupStreetAddress;
    }

    public String getPickupCity() {
        return pickupCity;
    }

    public String getPickupState() {
        return pickupState;
    }

    public String getPickupZipCode() {
        return pickupZipCode;
    }

    public String getDropOffStreetAddress() {
        return dropOffStreetAddress;
    }

    public String getDropOffCity() {
        return dropOffCity;
    }

    public String getDropOffState() {
        return dropOffState;
    }

    public String getDropOffZipCode() {
        return dropOffZipCode;
    }

    public String getMobility() {
        return mobility;
    }

    @Override
    public String toString() {
        return "UpdateTripMemberRequest{" +
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
                '}';
    }

    public static Builder builder(){
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder{
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
        public Builder withTripId(String tripId){
            this.tripId=tripId;
            return this;
        }
        public Builder withMemberId(String memberId){
            this.memberId=memberId;
            return this;
        }
        public Builder withPickupDateTime(String pickupDateTime){
            this.pickupDateTime=pickupDateTime;
            return this;
        }
        public Builder withPickupStreetAddress(String pickupStreetAddress){
            this.pickupStreetAddress=pickupStreetAddress;
            return this;
        }
        public Builder withPickupCity(String pickupCity){
            this.pickupCity=pickupCity;
            return this;
        }
        public Builder withPickupState(String pickupState){
            this.pickupState=pickupState;
            return this;
        }
        public Builder withPickupZipCode(String pickupZipCode){
            this.pickupZipCode=pickupZipCode;
            return this;
        }
        public Builder withDropOffStreetAddress(String dropOffStreetAddress){
            this.dropOffStreetAddress=dropOffStreetAddress;
            return this;
        }
        public Builder withDropOffCity(String dropOffCity){
            this.dropOffCity=dropOffCity;
            return this;
        }
        public Builder withDropOffState(String dropOffState){
            this.dropOffState=dropOffState;
            return this;
        }
        public Builder withDropOffZipCode(String dropOffZipCode){
            this.dropOffZipCode=dropOffZipCode;
            return this;
        }
        public Builder withMobility(String mobility){
            this.mobility=mobility;
            return this;
        }
        public UpdateTripMemberRequest build (){
            return new UpdateTripMemberRequest(tripId, memberId, pickupDateTime, pickupStreetAddress, pickupCity, pickupState, pickupZipCode, dropOffStreetAddress, dropOffCity, dropOffState, dropOffZipCode, mobility);
        }
    }
}
