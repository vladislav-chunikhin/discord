package cv.vladislavchunikhin.discord.discord.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.TimeUnit;

class ScheduleTaskDtoTest {
    private ScheduleTaskDto testable;

    @BeforeEach
    protected void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void constructorWithAllFields() {
        Runnable expectedTaskMock = Mockito.mock(Runnable.class);
        long expectedDelay = 4;
        long expectedPeriod = 2;
        TimeUnit expectedTimeUnit = TimeUnit.DAYS;
        testable = new ScheduleTaskDto(expectedTaskMock, expectedDelay, expectedPeriod, expectedTimeUnit);
        Assertions.assertEquals(testable.getDelay(), expectedDelay);
        Assertions.assertEquals(testable.getTask(), expectedTaskMock);
        Assertions.assertEquals(testable.getPeriod(), expectedPeriod);
        Assertions.assertEquals(testable.getUnit(), expectedTimeUnit);
    }
}