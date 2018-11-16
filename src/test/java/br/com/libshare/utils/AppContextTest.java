package br.com.libshare.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = { AppContext.class })
@ComponentScan(basePackages = { "br.com.libshare" })
public abstract class AppContextTest {

}
