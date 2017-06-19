package api.user;

import api.user.UsersRestUriConstants;
import api.Application;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.web.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.context.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@TestPropertySource(locations="classpath:application-test.properties")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class UsersControllerTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void getUsersOk() throws Exception {
    this.mockMvc.perform(get(UsersRestUriConstants.INDEX))
      .andDo(print())
      .andExpect(status().isOk());
  }

  @Test
  public void getUsersEmpty() throws Exception {
    this.mockMvc.perform(get(UsersRestUriConstants.INDEX))
      .andDo(print())
      .andExpect(content().string("[]"));
  }
}
