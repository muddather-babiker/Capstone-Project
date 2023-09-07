package capstone.project.activity;

import capstone.project.activity.requests.GetMemberRequest;
import capstone.project.activity.results.GetMemberResult;
import capstone.project.converters.ModelConverter;
import capstone.project.dynamodb.MemberDao;
import capstone.project.dynamodb.models.Member;
import capstone.project.models.MemberModel;

import javax.inject.Inject;

public class GetMemberActivity {
    private final MemberDao memberDao;

    @Inject
    public GetMemberActivity(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    public GetMemberResult handleRequest(final GetMemberRequest getMemberRequest) {
        String requestedMemberEmail = getMemberRequest.getMemberEmail();
        Member member = memberDao.getMember(requestedMemberEmail);
        MemberModel memberModel = new ModelConverter().toMemberModel(member);

        return GetMemberResult.builder()
                .withMember(memberModel)
                .build();
    }
}
