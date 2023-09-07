package capstone.project.lambda;

import capstone.project.activity.requests.GetDriverRequest;
import capstone.project.activity.results.GetDriverResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetDriverLambda extends LambdaActivityRunner<GetDriverRequest, GetDriverResult>
        implements RequestHandler<LambdaRequest<GetDriverRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetDriverRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromQuery(query ->
                        GetDriverRequest.builder()
                                .withDriverEmail(query.get("driverEmail"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetDriverActivity().handleRequest(request)
        );
    }
}
