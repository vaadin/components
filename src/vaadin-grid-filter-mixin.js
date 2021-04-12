/**
 * @license
 * Copyright (c) 2020 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */

/**
 * @polymerMixin
 */
export const FilterMixin = (superClass) =>
  class FilterMixin extends superClass {
    static get properties() {
      return {
        /** @private */
        _filters: {
          type: Array,
          value: function () {
            return [];
          }
        }
      };
    }

    /** @protected */
    ready() {
      super.ready();
      this.addEventListener('filter-changed', this._filterChanged.bind(this));
    }

    /** @private */
    _filterChanged(e) {
      e.stopPropagation();

      this.__updateFilter(e.target);
      this.__applyFilters();
    }

    __removeFilters(filters) {
      if (filters.length == 0) {
        return;
      }

      filters.forEach((filter) => this.__updateFilter(filter, true));
      this.__applyFilters();
    }

    /** @private */
    __updateFilter(filter, removed = false) {
      const filterIndex = this._filters.indexOf(filter);

      if (filterIndex === -1) {
        this._filters.push(filter);
      } else if (removed) {
        this._filters.splice(filterIndex, 1);
      }
    }

    /** @private */
    __applyFilters() {
      if (this.dataProvider && this.__isConnected) {
        this.clearCache();
      }
    }

    /**
     * @return {!Array<!GridFilter>}
     * @protected
     */
    _mapFilters() {
      return this._filters.map((filter) => {
        return {
          path: filter.path,
          value: filter.value
        };
      });
    }
  };
