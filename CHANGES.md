## Vaadin Components v0.3.0 (2015-xx-xx)
- Polymer updated to v0.9.0.

### `<v-grid>`
- Drag selection is now disabled.
- Row editor is now disabled.
- `Value Generator` is now removed because of feature overlaps with `Renderer` (#12)
- Spinner added to visualize data loading. (#14)
- Issues fixed:
  - Grid doesn't work when using selection-mode multi, frozen columns and sortable columns. (#7)
  - Grid height is recalculated incorrectly when sorting a grid with a fixed height. (#8)
  - "Fix regression in row focus indicator"
  - "column.width actually changes column.maxWidth"
  - "Select event shouldn't update the selection-mode attribute"
  - "Default editor save handler should show a message in the editor error message area and prevent the editor from closing"
  - "Dbl click on row should prevent text selection if editor is enabled"
  - "Setting a valueGenerator to a column fires a select event on multi-select mode"
  - "Toggling display:none; on v-grid breaks sizing calculations"
  - "Fix bug with empty rows when using the `x-repeat` template"
  - "Fix the "v-grid-ready" event firing in IE"

***

## Vaadin Components v0.2.1 (2015-05-15)

### `<v-grid>`
- New 'Material' Theme

***

## Vaadin Components v0.2.0 (2015-05-08)

### `<v-grid>`
- Polymer updated to v0.8.0-rc.7.
- Supported Grid features:
  - Selection modes: single, multi, all, disabled
  - Data binding
  - Sorting rows
  - Editing headers, footers and columns dynamically
  - Inline row editing
  - For more, see the [Examples](http://vaadin.github.io/components-examples/)


### `<v-button>`, `<v-slider>` and `<v-progress-bar>`
- Removed from the project for now.