package com.training.backend.configuration;

import com.training.backend.component.RequestTimeInterceptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WebMvcConfigurationTest {

    @InjectMocks
    private WebMvcConfiguration webMvcConfiguration;

    @Mock
    private RequestTimeInterceptor requestTimeInterceptor;

    @Test
    public void addInterceptorTest() {
        InterceptorRegistry registry = mock(InterceptorRegistry.class);
        webMvcConfiguration.addInterceptors(registry);
        verify(registry, times(1)).addInterceptor(requestTimeInterceptor);
    }
}
