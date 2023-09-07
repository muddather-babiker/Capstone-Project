package capstone.project.activity.requests;

public class SearchTripsRequest {
    private final String memberId;
    private final String pickupDateTime;

    public SearchTripsRequest(String memberId,String pickupDateTime) {
        this.memberId = memberId;
        this.pickupDateTime=pickupDateTime;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getPickupDateTime() {
        return pickupDateTime;
    }

    @Override
    public String toString() {
        return "SearchTripsRequest{" +
                "memberId='" + memberId + '\'' +
                ", pickupDateTime='" + pickupDateTime + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String memberId;
        private String pickupDateTime;

        public Builder withMemberId(String memberId) {
            this.memberId = memberId;
            return this;
        }
        public Builder withPickupDateTime(String pickupDateTime) {
            this.pickupDateTime = pickupDateTime;
            return this;
        }

        public SearchTripsRequest build() {
            return new SearchTripsRequest(memberId,pickupDateTime);
        }
    }
}
