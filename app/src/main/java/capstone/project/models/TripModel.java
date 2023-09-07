package capstone.project.models;

import java.util.Objects;

public class TripModel {
    private String tripId;
    private String memberId;
    private String pickupDateTime;
    private String pickupStreetAddress;
    private String pickupCity;
    private String pickupState;
    private String pickupZipCode;
    private String dropOffStreetAddress;
    private String dropOffCity;
    private String dropOffState;
    private String dropOffZipCode;
    private String mobility;
    private String driverId;
    private String selected;

    public TripModel(String tripId, String memberId, String pickupDateTime, String pickupStreetAddress, String pickupCity, String pickupState, String pickupZipCode, String dropOffStreetAddress, String dropOffCity, String dropOffState, String dropOffZipCode, String mobility, String driverId, String selected) {
        this.tripId = tripId;
        this.memberId = memberId;
        this.pickupDateTime = pickupDateTime;
        this.pickupStreetAddress = pickupStreetAddress;
        this.pickupCity = pickupCity;
        this.pickupState = pickupState;
        this.pickupZipCode = pickupZipCode;
        this.dropOffStreetAddress = dropOffStreetAddress;
        this.dropOffCity = dropOffCity;
        this.dropOffState = dropOffState;
        this.dropOffZipCode = dropOffZipCode;
        this.mobility = mobility;
        this.driverId = driverId;
        this.selected = selected;
    }

    public String getTripId() {
        return tripId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getPickupDateTime() {
        return pickupDateTime;
    }

    public String getPickupStreetAddress() {
        return pickupStreetAddress;
    }

    public String getPickupCity() {
        return pickupCity;
    }

    public String getPickupState() {
        return pickupState;
    }

    public String getPickupZipCode() {
        return pickupZipCode;
    }

    public String getDropOffStreetAddress() {
        return dropOffStreetAddress;
    }

    public String getDropOffCity() {
        return dropOffCity;
    }

    public String getDropOffState() {
        return dropOffState;
    }

    public String getDropOffZipCode() {
        return dropOffZipCode;
    }

    public String getMobility() {
        return mobility;
    }

    public String getDriverId() {
        return driverId;
    }

    public String getSelected() {
        return selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TripModel)) return false;
        TripModel tripModel = (TripModel) o;
        return Objects.equals(tripId, tripModel.tripId) && Objects.equals(memberId, tripModel.memberId) && Objects.equals(pickupDateTime, tripModel.pickupDateTime) && Objects.equals(pickupStreetAddress, tripModel.pickupStreetAddress) && Objects.equals(pickupCity, tripModel.pickupCity) && Objects.equals(pickupState, tripModel.pickupState) && Objects.equals(pickupZipCode, tripModel.pickupZipCode) && Objects.equals(dropOffStreetAddress, tripModel.dropOffStreetAddress) && Objects.equals(dropOffCity, tripModel.dropOffCity) && Objects.equals(dropOffState, tripModel.dropOffState) && Objects.equals(dropOffZipCode, tripModel.dropOffZipCode) && Objects.equals(mobility, tripModel.mobility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripId, memberId, pickupDateTime, pickupStreetAddress, pickupCity, pickupState, pickupZipCode, dropOffStreetAddress, dropOffCity, dropOffState, dropOffZipCode, mobility);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String tripId;
        private String memberId;
        private String pickupDateTime;
        private String pickupStreetAddress;
        private String pickupCity;
        private String pickupState;
        private String pickupZipCode;
        private String dropOffStreetAddress;
        private String dropOffCity;
        private String dropOffState;
        private String dropOffZipCode;
        private String mobility;
        private String driverId;
        private String selected;

        public TripModel.Builder withTripId(String tripId) {
            this.tripId = tripId;
            return this;
        }

        public TripModel.Builder withMemberId(String memberId) {
            this.memberId = memberId;
            return this;
        }

        public TripModel.Builder withPickupDateTime(String pickupDateTime) {
            this.pickupDateTime = pickupDateTime;
            return this;
        }

        public TripModel.Builder withPickupStreetAddress(String pickupStreetAddress) {
            this.pickupStreetAddress = pickupStreetAddress;
            return this;
        }

        public TripModel.Builder withPickupCity(String pickupCity) {
            this.pickupCity = pickupCity;
            return this;
        }

        public TripModel.Builder withPickupState(String pickupState) {
            this.pickupState = pickupState;
            return this;
        }

        public TripModel.Builder withPickupZipCode(String pickupZipCode) {
            this.pickupZipCode = pickupZipCode;
            return this;
        }

        public TripModel.Builder withDropOffStreetAddress(String dropOffStreetAddress) {
            this.dropOffStreetAddress = dropOffStreetAddress;
            return this;
        }

        public TripModel.Builder withDropOffCity(String dropOffCity) {
            this.dropOffCity = dropOffCity;
            return this;
        }

        public TripModel.Builder withDropOffState(String dropOffState) {
            this.dropOffState = dropOffState;
            return this;
        }

        public TripModel.Builder withDropOffZipCode(String dropOffZipCode) {
            this.dropOffZipCode = dropOffZipCode;
            return this;
        }

        public TripModel.Builder withMobility(String mobility) {
            this.mobility = mobility;
            return this;
        }

        public TripModel.Builder withDriverId(String driverId) {
            this.driverId = driverId;
            return this;
        }

        public TripModel.Builder withSelected(String selected) {
            this.selected = selected;
            return this;
        }

        public TripModel build() {
            return new TripModel(tripId, memberId, pickupDateTime, pickupStreetAddress, pickupCity, pickupState, pickupZipCode, dropOffStreetAddress, dropOffCity, dropOffState, dropOffZipCode, mobility, driverId, selected);
        }
    }
}
