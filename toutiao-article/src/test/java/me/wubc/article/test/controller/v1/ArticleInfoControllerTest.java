package me.wubc.article.test.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.wubc.model.article.dtos.ArticleInfoDto;
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
 * @date 2020/02/23
 * @desc 使用mockmvc进行测试
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ArticleInfoControllerTest {


    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    public void testLoadApArticleInfo() throws Exception {
        ArticleInfoDto articleInfoDto = new ArticleInfoDto();
        articleInfoDto.setArticleId(1);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/v1/article/loadArticleInfo");
        builder.contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsBytes(articleInfoDto));
        mvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testLoadArticleBehavior() throws Exception {
        ArticleInfoDto articleInfoDto = new ArticleInfoDto();
        articleInfoDto.setArticleId(1);
        articleInfoDto.setAuthorId(1);
        articleInfoDto.setEquipmentId(1);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/v1/article/loadArticleBehavior");
        builder.contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsBytes(articleInfoDto));
        mvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

}
