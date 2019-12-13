package br.com.caelum.pepperpotts.domain;

public enum Priority {
    HIGH(0),
    MEDIUM(1),
    LOW(2);

    private final int value;

    Priority(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
