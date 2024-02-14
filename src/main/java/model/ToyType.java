package model;

import java.util.Arrays;

public enum ToyType  {
    F(0),
    M(1),
    U (2);

    private final int value;

    ToyType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ToyType fromType (int value) {
        return Arrays.stream(ToyType.values()).filter(c -> c.value==value).findFirst().orElseThrow();
    }
}


//Equals:

//Value: Es que coincida con el valor ahi si lo puede lanzar
