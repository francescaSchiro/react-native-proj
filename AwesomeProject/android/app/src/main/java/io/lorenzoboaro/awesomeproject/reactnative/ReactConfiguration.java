package io.lorenzoboaro.awesomeproject.reactnative;

import android.os.Bundle;

import com.facebook.react.bridge.ReadableMap;

public class ReactConfiguration {
    private static final String SCREEN_WIDTH = "screenWidth";

    private String moduleName;
    private Bundle initialProps;
    private Bundle defaultProps;

    public ReactConfiguration(String moduleName) {
        createDefaultProps();
        this.moduleName = moduleName;
    }

    public ReactConfiguration(String moduleName, ReadableMap props) {
        if (props != null) {
            this.defaultProps = ConversionUtil.toBundle(props);
        } else {
            createDefaultProps();
        }
        this.moduleName = moduleName;
    }

    public ReactConfiguration(String moduleName, int screenWidth) {
        this(moduleName);

        initialProps = new Bundle();
        initWidthProps(screenWidth);
    }

    ////////////////////////////////////////////////////////////////////////
    // Public Methods
    ////////////////////////////////////////////////////////////////////////

    public String getModuleName() {
        return moduleName;
    }

    public Bundle getProps() {
        if (initialProps != null) {
            return initialProps;
        } else {
            return defaultProps;
        }
    }

    public Bundle getMergedProps() {
        if (initialProps == null) {
            initialProps = new Bundle();
        }
        initialProps.putAll(defaultProps);
        return initialProps;
    }

    ////////////////////////////////////////////////////////////////////////
    // Private Methods
    ////////////////////////////////////////////////////////////////////////

    private void createDefaultProps() {
        defaultProps = new Bundle();
    }

    private void initWidthProps(int screenWidth) {
        initialProps.putInt(SCREEN_WIDTH, screenWidth);
    }
}
