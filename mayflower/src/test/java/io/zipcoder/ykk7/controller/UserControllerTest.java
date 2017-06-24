//package io.zipcoder.ykk7.controller;
//
//import io.zipcoder.ykk7.MayflowerApplication;
//import io.zipcoder.ykk7.filter.CORSFilter;
//import io.zipcoder.ykk7.repository.UserRepository;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//@WebAppConfiguration
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = { MayflowerApplication.class })
//public class UserControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserController userController;
//
//    @Before
//    public void init(){
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders
//                .standaloneSetup(userController)
//                .addFilters(new CORSFilter())
//                .build();
//    }
//}
