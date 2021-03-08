package cv.vladislavchunikhin.discord.spock


import cv.vladislavchunikhin.discord.http.GeneralResponse
import cv.vladislavchunikhin.discord.http.NegativeResponse
import cv.vladislavchunikhin.discord.http.PositiveResponse
import cv.vladislavchunikhin.discord.util.JsonPojo
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class ApiBaseSpec extends IntegrationBaseSpec {
    private String BASE_URL = "/api/discord"
    protected String NOTIFICATION_SENDING_URL = "${BASE_URL}/notification/send"
    protected String NOTIFICATION_TASK_CREATING_URL = "${BASE_URL}/notification-task/create"

    protected ResultActions performGet(final String url) throws Exception {
        return this.mvc
                .perform(
                        MockMvcRequestBuilders
                                .get(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
    }

    protected ResultActions performPost(final String url, final JsonPojo payload) {
        return this.mvc
                .perform(
                        MockMvcRequestBuilders
                                .post(url)
                                .content(payload.toJsonString(false))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
    }

    protected static void checkResultOnSuccessful(final ResultActions resultActions) {
        resultActions.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
    }

    protected static void checkResultOnServerError(final ResultActions resultActions) {
        resultActions.andExpect(MockMvcResultMatchers.status().is5xxServerError())
    }

    protected GeneralResponse parseToGeneralResponse(final ResultActions resultActions) {
        final String contentAsString = resultActions.andReturn().getResponse().getContentAsString()
        if (contentAsString.contains("data")) return this.objectMapper.readValue(contentAsString, PositiveResponse.class)
        if (contentAsString.contains("errorType")) return this.objectMapper.readValue(contentAsString, NegativeResponse.class)
        return this.objectMapper.readValue(contentAsString, GeneralResponse.class)
    }
}
