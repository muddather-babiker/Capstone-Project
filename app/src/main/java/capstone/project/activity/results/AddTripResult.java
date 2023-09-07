package capstone.project.activity.results;

import capstone.project.models.TripModel;

public class AddTripResult {
    private final TripModel tripModel;

    public AddTripResult(TripModel tripModel) {
        this.tripModel = tripModel;
    }

    public TripModel getTripModel() {
        return tripModel;
    }

    @Override
    public String toString() {
        return "AddTripResult{" +
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

        public AddTripResult build() {
            return new AddTripResult(tripModel);
        }
    }
}

