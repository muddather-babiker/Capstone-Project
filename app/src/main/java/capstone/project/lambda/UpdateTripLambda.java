package capstone.project.lambda;

import capstone.project.activity.requests.UpdateTripRequest;
import capstone.project.activity.results.UpdateTripResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateTripLambda extends LambdaActivityRunner<UpdateTripRequest, UpdateTripResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateTripRequest>, LambdaResponse> {
    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateTripRequest> input, Context context){
        log.info("Our input to UpdateTripLambda: '{}'", input.toString());

        return super.runActivity(
                () -> {
                    UpdateTripRequest authenticatedRequest = input.fromBody(UpdateTripRequest.class);
                    log.info("Out UpdateTipRequest: '{}'", authenticatedRequest.toString());

                    return UpdateTripRequest.builder()
                            .withTripId(authenticatedRequest.getTripId())
                            .withMemberId(authenticatedRequest.getMemberId())
                            .withDriverId(authenticatedRequest.getDriverId())
                            .withSelected(authenticatedRequest.getSelected())
                            .build();
                },
                (request, serviceComponent) ->
                        serviceComponent.provideUpdateTripActivity().handleRequest(request));
    }
}
