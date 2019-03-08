public class OffByN implements CharacterComparator {
    private int offValue;

    public OffByN(int N) {
        offValue = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return (diff == offValue || diff == -offValue);
    }
}
