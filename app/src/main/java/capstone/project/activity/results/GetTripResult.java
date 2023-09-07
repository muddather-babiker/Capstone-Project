package capstone.project.activity.results;

import capstone.project.models.TripModel;

public class GetTripResult {
    private final TripModel tripModel;

    public GetTripResult(TripModel tripModel) {
        this.tripModel = tripModel;
    }

    public TripModel getTripModel() {
        return tripModel;
    }

    @Override
    public String toString() {
        return "GetTripResult{" +
                "tripModel=" + tripModel +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private TripModel tripModel;

        public Builder withTrip(TripModel tripModel) {
            this.tripModel = tripModel;
            return this;
        }

        public GetTripResult build() {
            return new GetTripResult(tripModel);
        }
    }
}
