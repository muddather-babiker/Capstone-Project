package capstone.project.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


@JsonDeserialize(builder = UpdateTripRequest.Builder.class)
public class UpdateTripRequest {
    private final String tripId;
    private final String memberId;
    private final String driverId;
    private final String selected;

    public UpdateTripRequest(String tripId, String memberId, String driverId, String selected) {
        this.tripId = tripId;
        this.memberId = memberId;
        this.driverId = driverId;
        this.selected = selected;
    }

    public String getTripId() {
        return tripId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getDriverId() {
        return driverId;
    }

    public String getSelected() {
        return selected;
    }

    @Override
    public String toString() {
        return "UpdateTripRequest{" +
                "tripId='" + tripId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", driverId='" + driverId + '\'' +
                ", selected='" + selected + '\'' +
                '}';
    }
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private  String tripId;
        private  String memberId;
        private  String driverId;
        private  String selected;

        public Builder withTripId(String tripId) {
            this.tripId = tripId;
            return this;
        }

        public Builder withMemberId(String memberId) {
            this.memberId = memberId;
            return this;
        }

        public Builder withDriverId(String driverId) {
            this.driverId = driverId;
            return this;
        }
        public Builder withSelected(String selected) {
            this.selected = selected;
            return this;
        }

        public UpdateTripRequest build() {
            return new UpdateTripRequest(tripId,memberId,driverId,selected);
        }
    }

}
