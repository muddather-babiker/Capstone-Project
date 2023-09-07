package capstone.project.activity.requests;

public class SearchTripsDriverRequest {
    private final String pickupZipCode;
    private final String selected;

    public SearchTripsDriverRequest(String pickupZipCode, String selected) {
        this.pickupZipCode = pickupZipCode;
        this.selected = selected;
    }

    public String getPickupZipCode() {
        return pickupZipCode;
    }

    public String getSelected() {
        return selected;
    }

    @Override
    public String toString() {
        return "SearchTripsDriverRequest{" +
                "pickupZipCode='" + pickupZipCode + '\'' +
                ", selected='" + selected + '\'' +
                '}';
    }
    public static Builder builder(){
        return new Builder();
    }
    public static class Builder {
       private String pickupZipCode;
       private String selected;
       public Builder withPickupZipCode(String pickupZipCode){
           this.pickupZipCode=pickupZipCode;
           return this;
       }
       public Builder withSelected(String selected){
           this.selected=selected;
           return this;
       }
       public SearchTripsDriverRequest build(){
           return new SearchTripsDriverRequest(pickupZipCode,selected);
       }
    }
}
