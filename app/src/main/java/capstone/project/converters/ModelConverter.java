package capstone.project.converters;

import capstone.project.dynamodb.models.Driver;
import capstone.project.dynamodb.models.Member;
import capstone.project.models.DriverModel;
import capstone.project.models.MemberModel;

public class ModelConverter {
    public DriverModel toDriverModel(Driver driver) {

        return DriverModel.builder()
                .withDriverEmail(driver.getDriverEmail())
                .withName(driver.getName())
                .withPhoneNumber(driver.getPhoneNumber())
                .withDateOfBirth(driver.getDateOfBirth())
                .withStreetAddress(driver.getStreetAddress())
                .withCity(driver.getCity())
                .withState(driver.getState())
                .withZipCode(driver.getZipCode())
                .build();
    }
    public MemberModel toMemberModel(Member member) {

        return MemberModel.builder()
                .withMemberEmail(member.getMemberEmail())
                .withName(member.getName())
                .withPhoneNumber(member.getPhoneNumber())
                .withDateOfBirth(member.getDateOfBirth())
                .withStreetAddress(member.getStreetAddress())
                .withCity(member.getCity())
                .withState(member.getState())
                .withZipCode(member.getZipCode())
                .build();
    }
}
