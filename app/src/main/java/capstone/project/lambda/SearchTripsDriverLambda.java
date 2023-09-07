package capstone.project.lambda;

import capstone.project.activity.requests.SearchTripsDriverRequest;
import capstone.project.activity.requests.SearchTripsRequest;
import capstone.project.activity.results.SearchTripsDriverResult;
import capstone.project.activity.results.SearchTripsResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class SearchTripsDriverLambda extends LambdaActivityRunner<SearchTripsDriverRequest, SearchTripsDriverResult>
        implements RequestHandler<LambdaRequest<SearchTripsDriverRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<SearchTripsDriverRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromQuery(query ->
                        SearchTripsDriverRequest.builder()
                                .withPickupZipCode(query.get("pickupZipCode"))
                                .withSelected(query.get("selected"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideSearchTripsDriverActivity().handleRequest(request)
        );
    }
}
