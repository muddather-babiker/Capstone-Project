package capstone.project.activity.results;

import capstone.project.models.DriverModel;

public class GetDriverResult {
private final DriverModel driverModel;

    public GetDriverResult(DriverModel driverModel) {
        this.driverModel = driverModel;
    }

    public DriverModel getDriverModel() {
        return driverModel;
    }

    @Override
    public String toString() {
        return "GetDriverResult{" +
                "driverModel=" + driverModel +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private DriverModel driverModel;
        public Builder withDriver(DriverModel driverModel) {
            this.driverModel = driverModel;
            return this;
        }

        public GetDriverResult build() {
            return new GetDriverResult(driverModel);
        }
    }
}
