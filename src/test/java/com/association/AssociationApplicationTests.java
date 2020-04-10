package com.association;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.WebApplicationContext;


//@RunWith(SpringRunner.class)
@SpringBootTest
class AssociationApplicationTests {

/*
	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(documrntationConfiguration(this.restDocumentation))
				.build();
	}
*/

    @Test
    public static void md2() {
        String s = "zhangjia";
        System.out.println(DigestUtils.md5DigestAsHex(s.getBytes()));
    }

    public static void main(String[] args) {
        md2();
    }

    @Test
    void contextLoads() throws Exception {
//		this.mockMvc.perform(get("/hello?page=2&per_page=100").accept(MediaType.APPLICATION_JSON)
//				.header("Authorization", "Basic dXNlcjpzZWNyZXQ="));
//				.andDo(print().andExpect(status().isOk())
//				.andDo()
//		)
    }


}
