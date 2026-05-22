package fr.lil4s;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit tests for the DiceScore Class")
public class DiceScoreTest {

    @Mock
    private Dice dice;

    private DiceScore diceScore;

    @BeforeEach
    public void setUp() {
        diceScore = new DiceScore(dice);
    }

    @ParameterizedTest
    @CsvSource({
        "1, 12",
        "2, 14",
        "3, 16",
        "4, 18",
        "5, 20"
    })
    @DisplayName("Should return 2x+10 when rolling doubles but not if sixes are rolled")
    void shouldReturnTwiceDiceValuePlus10WhenRollingDoublesButNotIfSixesAreRolled(int diceRoll, int expectedDiceScore) {

        when(dice.getRoll()).thenReturn(diceRoll, diceRoll);

        int actualDiceScore = diceScore.getScore();

        assertThat(actualDiceScore).isEqualTo(expectedDiceScore);
    }

    @Test
    @DisplayName("Should return 30 when rolling double sixes")
    void shouldReturn30WhenRollingDoubleSixes() {
        when(dice.getRoll()).thenReturn(6, 6);

        int actualDiceScore = diceScore.getScore();

        assertThat(actualDiceScore).isEqualTo(30);
    }

    @ParameterizedTest
    @CsvSource({
        "4, 2, 4",
        "2, 3, 3",
        "6, 4, 6",
        "4, 5, 5",
        "5, 1, 5"
    })
    @DisplayName("Should return the highest value of the two rolls when rolling non doubles")
    void shouldReturnTheHighestValueOfTwoRollsWhenRollingNonDoubles(int firstDiceRoll, int secondDiceRoll, int expectedDiceScore) {
        when(dice.getRoll()).thenReturn(firstDiceRoll, secondDiceRoll);

        int actualDiceScore = diceScore.getScore();

        assertThat(actualDiceScore).isEqualTo(expectedDiceScore);
    }
}