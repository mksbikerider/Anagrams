package name.msutherland.rest;

import name.msutherland.config.SpringStart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringJUnitConfig(SpringStart.class)
@WebAppConfiguration
@ContextConfiguration(classes = SpringStart.class)
public class ApplicationTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document("{method-name}",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
    }

    @Test
    public void findCrepitusMatches() throws Exception {
        this.mockMvc.perform(get("/crepitus").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"crepitus\":[\"cuprites\",\"pictures\",\"piecrust\"]}"));
    }

    @Test
    public void findMultiMatches() throws Exception {
        this.mockMvc.perform(get("/crepitus,paste,kinship,enlist,boaster,fresher,sinks,knits,sort").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"crepitus\":[\"cuprites\",\"pictures\",\"piecrust\"],\"paste\":[\"pates\",\"peats\",\"septa\",\"spate\",\"tapes\",\"tepas\"],\"kinship\":[\"pinkish\"],\"enlist\":[\"elints\",\"inlets\",\"listen\",\"silent\",\"tinsel\"],\"boaster\":[\"boaters\",\"borates\",\"rebatos\",\"sorbate\"],\"fresher\":[\"refresh\"],\"sinks\":[\"skins\"],\"knits\":[\"skint\",\"stink\",\"tinks\"],\"sort\":[\"orts\",\"rots\",\"stor\",\"tors\"]}"));
    }

    @Test
    public void findNoMatches() throws Exception {
        this.mockMvc.perform(get("/sdfwehrtgegfg").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"sdfwehrtgegfg\":[]}"));
    }
}
