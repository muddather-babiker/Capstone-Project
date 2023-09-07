package capstone.project.activity.results;

import capstone.project.models.TripModel;

public class UpdateTripMemberResult {
    private final TripModel trip;

    public UpdateTripMemberResult(TripModel trip) {
        this.trip = trip;
    }

    public TripModel getTrip() {
        return trip;
    }

    @Override
    public String toString() {
        return "UpdateTripMemberResult{" +
                "trip=" + trip +
                '}';
    }
    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{
        private TripModel trip;
        public Builder withTrip(TripModel trip){
            this.trip=trip;
            return this;
        }
        public UpdateTripMemberResult build(){
            return new UpdateTripMemberResult(trip);
        }
    }
}
