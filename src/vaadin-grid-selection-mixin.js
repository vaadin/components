/**
 * @license
 * Copyright (c) 2020 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */

/**
 * @polymerMixin
 */
export const SelectionMixin = (superClass) =>
  class SelectionMixin extends superClass {
    static get properties() {
      return {
        /**
         * An array that contains the selected items.
         * @type {Array<GridItem>}
         */
        selectedItems: {
          type: Object,
          notify: true,
          value: () => []
        }
      };
    }

    static get observers() {
      return ['_selectedItemsChanged(selectedItems.*)'];
    }

    /**
     * @param {!GridItem} item
     * @return {boolean}
     * @protected
     */
    _isSelected(item) {
      return this.selectedItems && this._getItemIndexInArray(item, this.selectedItems) > -1;
    }

    /**
     * Selects the given item.
     *
     * @method selectItem
     * @param {!GridItem} item The item object
     */
    selectItem(item) {
      if (!this._isSelected(item)) {
        this.push('selectedItems', item);
      }
    }

    /**
     * Deselects the given item if it is already selected.
     *
     * @method deselect
     * @param {!GridItem} item The item object
     */
    deselectItem(item) {
      const index = this._getItemIndexInArray(item, this.selectedItems);
      if (index > -1) {
        this.splice('selectedItems', index, 1);
      }
    }

    /**
     * Toggles the selected state of the given item.
     *
     * @method toggle
     * @param {!GridItem} item The item object
     * @protected
     */
    _toggleItem(item) {
      const index = this._getItemIndexInArray(item, this.selectedItems);
      if (index === -1) {
        this.selectItem(item);
      } else {
        this.deselectItem(item);
      }
    }

    /** @private */
    _selectedItemsChanged(e) {
      if (this.$.items.children.length && (e.path === 'selectedItems' || e.path === 'selectedItems.splices')) {
        Array.from(this.$.items.children).forEach((row) => {
          this._updateItem(row, row._item);
        });
      }
    }

    /** @private */
    _selectedInstanceChangedCallback(instance, value) {
      if (value) {
        this.selectItem(instance.item);
      } else {
        this.deselectItem(instance.item);
      }
    }
  };
