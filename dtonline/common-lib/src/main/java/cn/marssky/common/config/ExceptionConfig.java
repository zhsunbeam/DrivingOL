package cn.marssky.common.config;

import cn.marssky.common.error.GlobalExceptionTranslator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {GlobalExceptionTranslator.class})
public class ExceptionConfig {
}
