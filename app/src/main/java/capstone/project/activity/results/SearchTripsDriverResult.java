package capstone.project.activity.results;

import capstone.project.models.TripModel;

import java.util.ArrayList;
import java.util.List;

public class SearchTripsDriverResult {
    private final List<TripModel> trips;

    public SearchTripsDriverResult(List<TripModel> trips) {
        this.trips = trips;
    }

    public List<TripModel> getTrips(){
        return trips;
    }
    @Override
    public String toString() {
        return "SearchTripsDriverResult{" +
                "trips=" + trips +
                '}';
    }
    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{
        private List<TripModel> trips;
        public Builder withTrips(List<TripModel> trips){
            this.trips=new ArrayList<>(trips);
            return this;
        }
        public SearchTripsDriverResult build(){
            return new SearchTripsDriverResult(trips);
        }
    }
}
