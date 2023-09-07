package capstone.project.activity.requests;

public class GetDriverRequest {
    private final String driverEmail;

    public GetDriverRequest(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    @Override
    public String toString() {
        return "GetDriverRequest{" +
                "driverEmail='" + driverEmail + '\'' +
                '}';
    }
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String driverEmail;

        public Builder withDriverEmail(String driverEmail) {
            this.driverEmail = driverEmail;
            return this;
        }

        public GetDriverRequest build() {
            return new GetDriverRequest(driverEmail);
        }
    }
}
