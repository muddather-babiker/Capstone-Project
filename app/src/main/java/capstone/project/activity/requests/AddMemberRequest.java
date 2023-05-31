package capstone.project.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = AddMemberRequest.Builder.class)

public class AddMemberRequest {
    private String memberEmail;
    private String name;
    private String dateOfBirth;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    public AddMemberRequest(String memberEmail, String name, String dateOfBirth, String phoneNumber, String streetAddress, String city, String state, String zipCode) {
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
    public String toString() {
        return "AddMemberRequest{" +
                "memberEmail='" + memberEmail + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
    public static AddMemberRequest.Builder builder() {
        return new AddMemberRequest.Builder();
    }
    @JsonPOJOBuilder
    public static class Builder {
        private String memberEmail;
        private String name;
        private String dateOfBirth;
        private String phoneNumber;
        private String streetAddress;
        private String city;
        private String state;
        private String zipCode;

        public Builder withMemberEmail(String memberEmail) {
            this.memberEmail = memberEmail;
            return this;
        }
        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        public Builder withDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }
        public Builder withStreetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
            return this;
        }
        public Builder withCity(String city) {
            this.city = city;
            return this;
        }
        public Builder withState(String state) {
            this.state = state;
            return this;
        }
        public Builder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }
        public AddMemberRequest build() {
            return new AddMemberRequest(memberEmail, name, phoneNumber, dateOfBirth, streetAddress, city, state, zipCode);
        }
    }

}
