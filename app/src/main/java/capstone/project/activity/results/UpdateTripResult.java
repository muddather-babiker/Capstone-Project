package capstone.project.activity.results;

import capstone.project.models.TripModel;

public class UpdateTripResult {
    private final TripModel trip;

    public UpdateTripResult(TripModel trip) {
        this.trip = trip;
    }

    public TripModel getTrip() {
        return trip;
    }

    @Override
    public String toString() {
        return "UpdateTripResult{" +
                "trip=" + trip +
                '}';
    }
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private TripModel trip;

        public Builder withTrip(TripModel trip) {
            this.trip = trip;
            return this;
        }

        public UpdateTripResult build() {
            return new UpdateTripResult(trip);
        }
    }
}
