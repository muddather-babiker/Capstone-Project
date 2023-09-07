package capstone.project.activity.requests;

import com.amazonaws.services.dynamodbv2.xspec.S;

public class RemoveTripRequest {
    private final String tripId;
    private final String memberId;

    public RemoveTripRequest(String tripId,String memberId) {
        this.tripId = tripId;
        this.memberId=memberId;
    }

    public String getTripId() {
        return tripId;
    }

    public String getMemberId() {
        return memberId;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "RemoveTripRequest{" +
                "tripId='" + tripId + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }

    public static class Builder {
        private String tripId;
        private String memberId;

        public Builder withTripId(String tripId) {
            this.tripId = tripId;
            return this;
        }
        public Builder withMemberId(String memberId) {
            this.memberId = memberId;
            return this;
        }
        public RemoveTripRequest build() {
            return new RemoveTripRequest(tripId,memberId);
        }
    }
}
