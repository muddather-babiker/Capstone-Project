package capstone.project.activity.results;

import capstone.project.models.MemberModel;

public class GetMemberResult {
    private final MemberModel memberModel;

    public GetMemberResult(MemberModel memberModel) {
        this.memberModel = memberModel;
    }

    public MemberModel getMemberModel() {
        return memberModel;
    }

    @Override
    public String toString() {
        return "GetMemberResult{" +
                "memberModel=" + memberModel +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MemberModel memberModel;

        public Builder withMember(MemberModel memberModel) {
            this.memberModel = memberModel;
            return this;
        }

        public GetMemberResult build() {
            return new GetMemberResult(memberModel);
        }
    }
}
