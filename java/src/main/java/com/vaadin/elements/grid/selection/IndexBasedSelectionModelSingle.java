package com.vaadin.elements.grid.selection;

import com.vaadin.client.widget.grid.selection.SelectionEvent;
import com.vaadin.client.widget.grid.selection.SelectionModelSingle;
import com.vaadin.client.widgets.Grid;
import com.vaadin.elements.common.js.JS;
import com.vaadin.elements.common.js.JSArray;
import com.vaadin.elements.common.js.JSFunction;
import com.vaadin.elements.common.js.JSValidate;

/**
 * An {@link IndexBasedSelectionModel} for single selection.
 */
public class IndexBasedSelectionModelSingle extends
        SelectionModelSingle<Object> implements IndexBasedSelectionModel {

    private Grid<Object> grid;
    private int selectedRow = -1;
    private boolean dataSizeUpdated = false;

    @Override
    public boolean select(Object row) {
        return select(SelectionUtil.getRowIndexByRow(grid, row), false);
    }

    @Override
    public boolean deselect(Object row) {
        return deselect(SelectionUtil.getRowIndexByRow(grid, row), false);
    }

    @Override
    public boolean isSelected(Object row) {
        return selectedRow == SelectionUtil.getRowIndexByRow(grid, row);
    }

    @Override
    public void setGrid(Grid<Object> grid) {
        this.grid = grid;
        super.setGrid(grid);
    }

    @Override
    public void reset() {
        selectedRow = -1;
        grid.fireEvent(new SelectionEvent<Object>(grid, null, null, false));
    }

    @Override
    public int size() {
        return selectedRow == -1 ? 0 : 1;
    }

    @Override
    public JSArray<Object> selected(JSFunction<Object, Integer> mapper,
            Integer from, Integer to) {
        JSArray<Object> result = JS.createArray();
        if (selectedRow != -1) {
            Object mappedValue = mapper == null ? selectedRow : mapper.f(selectedRow);
            if (mappedValue != null) {
                result.add(mappedValue);
            }
        }

        return result;
    }

    @Override
    public JSArray<Object> deselected(JSFunction<Object, Integer> mapper,
            Integer from, Integer to) {
        return JS.createArray();
    }

    @Override
    public boolean select(int index, boolean skipOwnEvents) {
        if (index >= 0
                && (!dataSizeUpdated || index < grid.getDataSource().size())) {
            selectedRow = index;
            skipOwnEvents = JSValidate.Boolean.val(skipOwnEvents, false, false);
            if (!skipOwnEvents) {
                grid.fireEvent(new SelectionEvent<Object>(grid, null, null,
                        false));
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean deselect(int index, boolean skipOwnEvents) {
        if (selectedRow == index) {
            selectedRow = -1;
            skipOwnEvents = JSValidate.Boolean.val(skipOwnEvents, false, false);
            if (!skipOwnEvents) {
                grid.fireEvent(new SelectionEvent<Object>(grid, null, null,
                        false));
            }
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        deselect(selectedRow, false);
    }

    @Override
    public void selectAll() {
    }

    @Override
    public IndexBasedSelectionMode getMode() {
        return IndexBasedSelectionMode.SINGLE;
    }

    @Override
    public void dataSizeUpdated(int newSize) {
        dataSizeUpdated = true;
        if (selectedRow >= newSize) {
            selectedRow = -1;
        }
    }

    @Override
    public void setMode(IndexBasedSelectionMode mode) {
    }

    @Override
    public boolean supportsMode(IndexBasedSelectionMode mode) {
        return mode == IndexBasedSelectionMode.SINGLE;
    }

}
