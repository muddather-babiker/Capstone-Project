package capstone.project.activity.results;

import capstone.project.models.TripModel;

import java.util.ArrayList;
import java.util.List;

public class SearchTripsResult {
    private final List<TripModel> trips;

    public SearchTripsResult(List<TripModel> trips) {
        this.trips = trips;
    }

    public List<TripModel> getTrips() {
        return trips;
    }

    @Override
    public String toString() {
        return "SearchTripsResult{" +
                "trips=" + trips +
                '}';
    }
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<TripModel> trips ;
        public Builder withTrips(List<TripModel> trips) {
            this.trips = new ArrayList<>(trips);
            return this;
        }
        public SearchTripsResult build() {
            return new SearchTripsResult(trips);
        }
    }
}
