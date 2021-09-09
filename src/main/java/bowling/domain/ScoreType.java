package bowling.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum ScoreType {
    STRIKE("X", (previous, current) -> (previous == -1 || previous == Rolling.MAX_PINS) && current == Rolling.MAX_PINS),
    SPARE("|/", (previous, current) -> previous != -1 && previous < Rolling.MAX_PINS && previous + current == Rolling.MAX_PINS),
    MISS("|", (previous, current) -> previous != -1 && previous < Rolling.MAX_PINS && previous + current < Rolling.MAX_PINS && current != 0),
    GUTTER("|-", (previous, current) -> previous != -1 && current == 0);

    private static final String SEPARATOR = "|";
    private String symbol;
    private BiPredicate<Integer, Integer> predicate;

    ScoreType(String symbol, BiPredicate<Integer, Integer> predicate) {
        this.symbol = symbol;
        this.predicate = predicate;
    }

    static String findType(final int previousPin, final int currentPin) {
        String symbol = Arrays.stream(ScoreType.values())
                .filter(type -> type.predicate.test(previousPin, currentPin))
                .map(ScoreType::getSymbol)
                .findFirst()
                .orElse(previousPin == -1 ? String.valueOf(currentPin) : SEPARATOR + currentPin);

        if (symbol.equals(MISS.getSymbol())) return symbol + currentPin;
        return symbol;
    }

    public String getSymbol() {
        return symbol;
    }

}
