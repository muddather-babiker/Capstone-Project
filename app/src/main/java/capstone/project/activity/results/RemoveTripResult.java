package capstone.project.activity.results;

import capstone.project.models.TripModel;

public class  RemoveTripResult {
    private final TripModel tripModel;

    public RemoveTripResult(TripModel tripModel) {
        this.tripModel = tripModel;
    }

    public TripModel getTripModel() {
        return tripModel;
    }

    @Override
    public String toString() {
        return "RemoveTripResult{" +
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

        public RemoveTripResult build() {
            return new RemoveTripResult(tripModel);
        }
    }
}
