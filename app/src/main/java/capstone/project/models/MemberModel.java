package capstone.project.models;

import java.util.Objects;

public class MemberModel {
    private String memberEmail;
    private String name;
    private String dateOfBirth;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    public MemberModel(String memberEmail, String name, String dateOfBirth, String phoneNumber, String streetAddress, String city, String state, String zipCode) {
        this.memberEmail = memberEmail;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getMemberEmail() {
        return memberEmail;
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
        if (!(o instanceof MemberModel)) return false;
        MemberModel that = (MemberModel) o;
        return Objects.equals(memberEmail, that.memberEmail) && Objects.equals(name, that.name) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(streetAddress, that.streetAddress) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(zipCode, that.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberEmail, name, dateOfBirth, phoneNumber, streetAddress, city, state, zipCode);
    }

    public static Builder builder (){ return new Builder();}

    public static class Builder {
        private String memberEmail;
        private String name;
        private String dateOfBirth;
        private String phoneNumber;
        private String streetAddress;
        private String city;
        private String state;
        private String zipCode;

        public MemberModel.Builder withMemberEmail(String memberEmail) {
            this.memberEmail = memberEmail;
            return this;
        }

        public MemberModel.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public MemberModel.Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public MemberModel.Builder withDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public MemberModel.Builder withStreetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
            return this;
        }

        public MemberModel.Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public MemberModel.Builder withState(String state) {
            this.state = state;
            return this;
        }

        public MemberModel.Builder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }
        public MemberModel build() {
            return new MemberModel(memberEmail, name, phoneNumber, dateOfBirth, streetAddress, city, state, zipCode);
        }
    }
}
