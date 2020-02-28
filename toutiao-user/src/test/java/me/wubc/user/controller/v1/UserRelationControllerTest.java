package me.wubc.user.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.wubc.model.user.dtos.UserRelationDto;
import me.wubc.model.user.pojos.ApUser;
import me.wubc.utils.threadlocal.AppThreadLocalUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author wbc
 * @date 2020/02/27
 * @desc
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRelationControllerTest {

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void initUser() {
        ApUser apUser = new ApUser();
        apUser.setId(1l);
        AppThreadLocalUtils.setUser(apUser);
    }


    @Test
    public void testFollowAdd() throws Exception {
        UserRelationDto userRelationDto = new UserRelationDto();
        userRelationDto.setOperation((short) 0);
        userRelationDto.setArticleId(1);
        userRelationDto.setAuthorId(1);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/v1/user/userFollow");
        builder.contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(userRelationDto));
        mvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testFollowCancel() throws Exception {
        UserRelationDto dto = new UserRelationDto();
        dto.setOperation((short) 1);
        dto.setArticleId(1);
        dto.setAuthorId(1);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/v1/user/userFollow");
        builder.contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(dto));
        mvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

}
