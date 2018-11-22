package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class DemoApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }

    @Test
    public void getPageList()throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/AddressList/ListUser"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":1,\"name\":\"王强\",\"password\":\"123123\",\"number\":\"13812341234\"},{\"id\":2,\"name\":\"老万\",\"password\":\"232232\",\"number\":\"13312341234\"},{\"id\":3,\"name\":\"蓝天1\",\"password\":\"asdasd1\",\"number\":\"136987612181\"},{\"id\":4,\"name\":\"守一\",\"password\":\"asdasd\",\"number\":\"13798761218\"},{\"id\":5,\"name\":\"沧海\",\"password\":\"canghai\",\"number\":\"13555551218\"},{\"id\":6,\"name\":\"泰然\",\"password\":\"tairan\",\"number\":\"13655661218\"},{\"id\":7,\"name\":\"追风\",\"password\":\"zhuifeng\",\"number\":\"13255661218\"}]"));
    }

    @Test
    public void addUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\n" +
                "  \"id\": 8,\n" +
                "  \"name\":\"令狐冲\",\n" +
                "  \"password\":\"123123\",\n" +
                "  \"number\":\"13681234567\"\n" +
                "}";
        User user = mapper.readValue(jsonString, User.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/AddressList/insert")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$.name").value("令狐冲"));
    }

}
