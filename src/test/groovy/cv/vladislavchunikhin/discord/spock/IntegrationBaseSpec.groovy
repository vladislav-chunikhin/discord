package cv.vladislavchunikhin.discord.spock

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@AutoConfigureMockMvc
@SpringBootTest
class IntegrationBaseSpec extends BaseSpec {
    @Autowired protected MockMvc mvc
    @Autowired protected ObjectMapper objectMapper
}
