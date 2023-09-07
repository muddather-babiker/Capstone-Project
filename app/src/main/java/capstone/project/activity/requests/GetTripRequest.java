package capstone.project.activity.requests;

public class GetTripRequest {
    private final String tripId;
    private final String memberId;

    public GetTripRequest(String tripId, String memberId) {
        this.tripId = tripId;
        this.memberId = memberId;
    }

    public String getTripId() {
        return tripId;
    }

    public String getMemberId() {
        return memberId;
    }

    @Override
    public String toString() {
        return "GetTripRequest{" +
                "tripId='" + tripId + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
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

        public GetTripRequest build() {
            return new GetTripRequest(tripId, memberId);
        }
    }
}
