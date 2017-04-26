package rxzendesk;

import com.zendesk.service.ErrorResponse;
import com.zendesk.service.Header;
import com.zendesk.service.ZendeskCallback;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.annotations.NonNull;

public class RxZendesk {
    /**
     * We don't need to wrap SafeZendeskCallback because we have Disposable for disposing already
     * @param <T>
     */
    public static class MaybeZendeskCallback<T> extends ZendeskCallback<T> {
        @NonNull
        private final MaybeEmitter emit;

        public MaybeZendeskCallback(@NonNull MaybeEmitter emit) {
            this.emit = emit;
        }

        @Override
        public void onSuccess(@NonNull T t) {
            emit.onSuccess(t);
        }

        @Override
        public void onError(@NonNull ErrorResponse errorResponse) {
            emit.onError(new ResponseException(errorResponse));
        }
    }

    public static class ResponseException extends RuntimeException implements ErrorResponse {
        @NonNull
        private final ErrorResponse errorResponse;

        public ResponseException(ErrorResponse errorResponse) {
            super(errorResponse.getReason());
            this.errorResponse = errorResponse;
        }

        @SuppressWarnings("deprecation")
        @Override
        public boolean isNetworkError() {
            return errorResponse.isNetworkError();
        }

        @SuppressWarnings("deprecation")
        @Override
        public boolean isConversionError() {
            return errorResponse.isConversionError();
        }

        @Override
        public boolean isHTTPError() {
            return errorResponse.isHTTPError();
        }

        @Override
        @NonNull
        public String getReason() {
            return errorResponse.getReason();
        }

        @Override
        public int getStatus() {
            return errorResponse.getStatus();
        }

        @Override
        @NonNull
        public String getUrl() {
            return errorResponse.getUrl();
        }

        @Override
        @NonNull
        public String getResponseBody() {
            return errorResponse.getResponseBody();
        }

        @Override
        @NonNull
        public String getResponseBodyType() {
            return errorResponse.getResponseBodyType();
        }

        @Override
        @NonNull
        public List<Header> getResponseHeaders() {
            return errorResponse.getResponseHeaders();
        }
    }
}
