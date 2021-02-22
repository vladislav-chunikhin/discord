package cv.vladislavchunikhin.discord.discord

import cv.vladislavchunikhin.discord.discord.dto.ScheduleTaskDto
import cv.vladislavchunikhin.discord.spock.BaseSpec
import org.springframework.http.HttpStatus

import java.util.concurrent.TimeUnit

/**
 * Unit tests for {@link ScheduledTaskComponentImpl}.
 */
class ScheduledTaskComponentImplSpec extends BaseSpec {
    private def testable = new ScheduledTaskComponentImpl()

    /**
     * Normal case.
     * {@link ScheduledTaskComponentImpl#createScheduledTask(cv.vladislavchunikhin.discord.discord.dto.ScheduleTaskDto)}.
     */
    def "creating scheduled task in normal case"() {
        given:
        def taskMock = Mock(Runnable.class)
        def description = UUID.randomUUID().toString()
        def inputDto = new ScheduleTaskDto(taskMock, 1, 1, TimeUnit.DAYS, description)
        when:
        def actualResult = testable.createScheduledTask(inputDto)
        then:
        testable.getAllTasks().containsKey(actualResult)
    }

    /**
     * Normal case.
     * {@link ScheduledTaskComponentImpl#turnOffTaskById(UUID)}.
     */
    def "turn offing scheduled task in normal case"() {
        given:
        def taskMock = Mock(Runnable.class)
        def description = UUID.randomUUID().toString()
        def inputDto = new ScheduleTaskDto(taskMock, 1, 1, TimeUnit.DAYS, description)
        def inputId = testable.createScheduledTask(inputDto)
        when:
        def actualResult = testable.turnOffTaskById(inputId)
        then:
        actualResult.code == HttpStatus.OK
        actualResult.message == HttpStatus.OK.name()
    }

    /**
     * There are not tasks.
     * {@link ScheduledTaskComponentImpl#turnOffTaskById(UUID)}.
     */
    def "turn offing a scheduled task when there are not tasks to process"() {
        given:
        def inputId = UUID.randomUUID()
        when:
        def actualResult = testable.turnOffTaskById(inputId)
        then:
        actualResult.code == HttpStatus.NOT_FOUND
        actualResult.message == "Schedule task not found by id = ${inputId}."
    }

    /**
     * A scheduled task is already disabled.
     * {@link ScheduledTaskComponentImpl#turnOffTaskById(UUID)}.
     */
    def "turn offing a scheduled task when it is already disabled"() {
        given:
        def taskMock = Mock(Runnable.class)
        def description = UUID.randomUUID().toString()
        def inputDto = new ScheduleTaskDto(taskMock, 1, 1, TimeUnit.DAYS, description)
        def inputId = testable.createScheduledTask(inputDto)
        testable.turnOffAllTasks()
        when:
        def actualResult = testable.turnOffTaskById(inputId)
        then:
        actualResult.code == HttpStatus.OK
        actualResult.message == "Schedule task is shutdown already."
    }

    /**
     * Normal case.
     * {@link ScheduledTaskComponentImpl#turnOffAllTasks}.
     */
    def "turn offing all scheduled tasks in normal case"() {
        given:
        def taskMock = Mock(Runnable.class)
        def description = UUID.randomUUID().toString()
        def inputDto = new ScheduleTaskDto(taskMock, 1, 1, TimeUnit.DAYS, description)
        def taskId = testable.createScheduledTask(inputDto)
        when:
        def actualResult = testable.turnOffAllTasks()
        then:
        actualResult.code == HttpStatus.OK
        actualResult.message == HttpStatus.OK.name()
        testable.getAllTasks().containsKey(taskId)
        testable.getAllTasks().get(taskId).getScheduledExecutorService().isShutdown()
    }

    def cleanup() {
        testable.turnOffAllTasks()
    }
}
