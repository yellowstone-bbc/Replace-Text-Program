package com.cloudClientSetup.CreateSourceCode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class CreateSourceCodeApplication {

	public static void main(String[] args) throws IOException {
		ApplicationContext appCnt = SpringApplication.run(CreateSourceCodeApplication.class, args);
		Data data = appCnt.getBean(Data.class);
		performWrite obj = new performWrite();
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.putAll(data.configPropMap());
		
		obj.modifyFile("ApplicationClass.txt", data.classFileName+".java", dataMap);
		obj.modifyFile("bootstrapYML.txt", "bootstrap.yml", dataMap);
		
		SpringApplication.exit(appCnt, () -> 0);
	}
	
	@Component
	class Data{
		@Value("${packageName}")
		public String packageName;
		
		@Value("${classFileName}")
		public String classFileName;
		
		@Value("${serviceName}")
		public String serviceName;
		
		@PostConstruct
		public Map<String, String> configPropMap() {
			Map<String, String> configMap = new HashMap<String, String>();
			configMap.put("packageName", packageName);
			configMap.put("classFileName", classFileName);
			configMap.put("serviceName", serviceName);
			return configMap;
		}
	}

}