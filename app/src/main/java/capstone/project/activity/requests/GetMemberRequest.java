package capstone.project.activity.requests;

public class GetMemberRequest {
    private final String memberEmail;

    public GetMemberRequest(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    @Override
    public String toString() {
        return "GetMemberRequest{" +
                "memberEmail='" + memberEmail + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String memberEmail;

        public Builder withMemberEmail(String memberEmail) {
            this.memberEmail = memberEmail;
            return this;
        }

        public GetMemberRequest build() {
            return new GetMemberRequest(memberEmail);
        }
    }
}
