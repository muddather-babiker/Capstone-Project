package capstone.project.activity.results;

import capstone.project.models.MemberModel;

public class AddMemberResult {
    private final MemberModel memberModel;

    public AddMemberResult(MemberModel memberModel) {
        this.memberModel = memberModel;
    }

    public MemberModel getMemberModel() {
        return memberModel;
    }

    @Override
    public String toString() {
        return "AddMemberResult{" +
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

        public AddMemberResult build() {
            return new AddMemberResult(memberModel);
        }
    }
}
