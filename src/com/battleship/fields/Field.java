package com.battleship.fields;

public interface Field {
    /**
     * Hit it
     *
     * @return hit message
     */
    String hit();

    /**
     * Set state of the field
     *
     * @param fieldState
     */
    void setFieldState(FieldState fieldState);

    /**
     * Get state of the field
     *
     * @return state
     */
    FieldState getFieldState();
}
