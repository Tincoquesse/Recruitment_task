package com.atipera.recruitment_task.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class RecruitmentTaskApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldGetRepos() throws Exception {
        //GIVEN
        String username = "Tincoquesse";

        //WHEN
        MvcResult mvcResult = this.mockMvc.perform(get("/api/user/" + username + "/repos")).andReturn();
        int status = mvcResult.getResponse().getStatus();

        //THEN
        assertThat(status).isEqualTo(200);
    }
}
