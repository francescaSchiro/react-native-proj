package io.lorenzoboaro.awesomeproject.reactnative;

import com.facebook.react.ReactRootView;

public interface ReactInterface {
    // @formatter:off
    String getInstanceId();

    ReactRootView getReactRootView();

    void emitEvent(String eventName, Object object);
    // @formatter:on
}