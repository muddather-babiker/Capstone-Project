package capstone.project.lambda;

import capstone.project.activity.requests.AddDriverRequest;
import capstone.project.activity.results.AddDriverResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddDriverLambda extends LambdaActivityRunner<AddDriverRequest, AddDriverResult> implements RequestHandler<AuthenticatedLambdaRequest<AddDriverRequest>, LambdaResponse> {
    private final Logger logger = LogManager.getLogger();
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddDriverRequest> input, Context context) {
        logger.info("the input: '{}'" , input.toString());
        return super.runActivity(
                () -> {
                    AddDriverRequest authenticatedRequest = input.fromBody(AddDriverRequest.class);
               logger.info("the request: '{}'" , authenticatedRequest.toString());
                    return AddDriverRequest.builder()
                            .withDriverEmail(authenticatedRequest.getDriverEmail())
                            .withName(authenticatedRequest.getName())
                            .withPhoneNumber(authenticatedRequest.getPhoneNumber())
                            .withDateOfBirth(authenticatedRequest.getDateOfBirth())
                            .withStreetAddress(authenticatedRequest.getStreetAddress())
                            .withCity(authenticatedRequest.getCity())
                            .withState(authenticatedRequest.getState())
                            .withZipCode(authenticatedRequest.getZipCode())
                            .build();
                },
                (request, serviceComponent) ->  serviceComponent.provideAddDriverActivity().handleRequest(request)

        );
    }
}

