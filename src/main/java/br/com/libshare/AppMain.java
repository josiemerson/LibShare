package br.com.libshare;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.libshare.utils.AppContext;
import br.com.libshare.utils.BaseBean;

@SpringBootApplication
public class AppMain {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(AppContext.class, args);
//		AppMain.testeObjectMapper();
	}
	
	public static void testeObjectMapper() throws Exception {
		String json = "{\"username\" : \"admin@admin.com\", \"password\" : \"admin\"}";
		InputStream is = new ByteArrayInputStream(json.getBytes());
		teste om = new ObjectMapper().readValue(is, teste.class);
		System.out.println(om.getUsername());
		System.out.println(om.getPassword());
	}
	
	public static class teste extends BaseBean{
		private static final long serialVersionUID = 1L;
		private String username;
		private String password;

		public teste() {
			
		}
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
	}
}