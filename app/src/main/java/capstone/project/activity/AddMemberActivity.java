package capstone.project.activity;
import capstone.project.activity.requests.AddMemberRequest;
import capstone.project.activity.results.AddMemberResult;
import capstone.project.converters.ModelConverter;
import capstone.project.dynamodb.MemberDao;
import capstone.project.dynamodb.models.Member;
import capstone.project.models.MemberModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class AddMemberActivity {
    private final Logger log = LogManager.getLogger();
    private final MemberDao memberDao;

    @Inject
    public AddMemberActivity(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    public AddMemberResult handleRequest(final AddMemberRequest addMemberRequest) {
        log.info("Received AddMemberRequest {}",addMemberRequest);
        Member newMember = new Member();
        newMember.setMemberEmail(addMemberRequest.getMemberEmail());
        newMember.setName(addMemberRequest.getName());
        newMember.setPhoneNumber(addMemberRequest.getPhoneNumber());
        newMember.setDateOfBirth(addMemberRequest.getDateOfBirth());
        newMember.setStreetAddress(addMemberRequest.getStreetAddress());
        newMember.setCity(addMemberRequest.getCity());
        newMember.setState(addMemberRequest.getState());
        newMember.setZipCode(addMemberRequest.getZipCode());

        memberDao.saveMember(newMember);

        MemberModel memberModel = new ModelConverter().toMemberModel(newMember);
        return AddMemberResult.builder()
                .withMember(memberModel)
                .build();

    }
}
