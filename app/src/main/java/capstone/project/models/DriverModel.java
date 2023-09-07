package capstone.project.models;

import java.util.Objects;

public class DriverModel {
    private String driverEmail;
    private String name;
    private String dateOfBirth;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    public DriverModel(String driverEmail, String name, String dateOfBirth, String phoneNumber, String streetAddress, String city, String state, String zipCode) {
        this.driverEmail = driverEmail;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DriverModel)) return false;
        DriverModel that = (DriverModel) o;
        return Objects.equals(driverEmail, that.driverEmail) && Objects.equals(name, that.name) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(streetAddress, that.streetAddress) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(zipCode, that.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverEmail, name, dateOfBirth, phoneNumber, streetAddress, city, state, zipCode);
    }
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String driverEmail;
        private String name;
        private String dateOfBirth;
        private String phoneNumber;
        private String streetAddress;
        private String city;
        private String state;
        private String zipCode;

        public DriverModel.Builder withDriverEmail(String driverEmail) {
            this.driverEmail = driverEmail;
            return this;
        }

        public DriverModel.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public DriverModel.Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public DriverModel.Builder withDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public DriverModel.Builder withStreetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
            return this;
        }

        public DriverModel.Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public DriverModel.Builder withState(String state) {
            this.state = state;
            return this;
        }

        public DriverModel.Builder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public DriverModel build() {
            return new DriverModel(driverEmail, name, phoneNumber, dateOfBirth, streetAddress, city, state, zipCode);
        }
    }

}
