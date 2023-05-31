package capstone.project.activity.results;

import capstone.project.models.DriverModel;

public class AddDriverResult {
    private final DriverModel driverModel;

    public AddDriverResult(DriverModel driverModel) {
        this.driverModel = driverModel;
    }
    public DriverModel getDriver() {
        return driverModel;
    }

    @Override
    public String toString() {
        return "AddDriverResult{" +
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

        public AddDriverResult build() {
            return new AddDriverResult(driverModel);
        }
    }

}
