package corp.wmsoft.android.examples.mvpc.longrunning.iteractor;

import java.util.concurrent.TimeUnit;

import corp.wmsoft.android.lib.mvpc.interactor.MVPCUseCase;
import hugo.weaving.DebugLog;

/**
 * Created by westman on 8/22/16.
 */
@DebugLog
public class LongRunningUseCase extends MVPCUseCase<LongRunningUseCase.RequestValues, LongRunningUseCase.ResponseValue> {


    @Override
    protected void executeUseCase(RequestValues requestValues) {

        String message = "response from use case";

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
            getUseCaseCallback().onError(new Error("error in use case"));
        }

        ResponseValue responseValue = new ResponseValue(message);
        getUseCaseCallback().onSuccess(responseValue);
    }

    public static class RequestValues extends MVPCUseCase.RequestValues {
    }

    public static class ResponseValue extends MVPCUseCase.ResponseValue {

        private final String responseMessage;


        public ResponseValue(String responseMessage) {
            this.responseMessage = responseMessage;
        }

        public String getResponseMessage() {
            return responseMessage;
        }
    }
}
