package nl.jmuijsenberg.androiddemo.entities;

public enum Gender {
    MALE(0), FEMALE(1), UNKNOWN(2);

    private final int id;
    Gender(int id) { this.id = id; }
    public int getValue() { return id; }

    public static Gender getGender(int genderIndex) {
        for (Gender l : Gender.values()) {
            if (l.getValue() == genderIndex)
            {
                return l;
            }
        }
        throw new IllegalArgumentException("Gender not found. " + genderIndex);
    }
}
