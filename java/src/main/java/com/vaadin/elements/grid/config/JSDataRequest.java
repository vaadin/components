package com.vaadin.elements.grid.config;

import com.google.gwt.core.client.js.JsProperty;
import com.google.gwt.core.client.js.JsType;
import com.vaadin.elements.common.js.JSArray;

/**
 * This class is a JsInterop wrapper for the JS object editor handler request.
 */
@JsType
public interface JSDataRequest {
    @JsProperty
    int getIndex();

    @JsProperty
    void setIndex(int index);

    @JsProperty
    int getCount();

    @JsProperty
    void setCount(int count);

    @JsProperty
    JSArray<JSSortOrder> getSortOrder();

    @JsProperty
    void setSortOrder(JSArray<JSSortOrder> sortOrder);
}
