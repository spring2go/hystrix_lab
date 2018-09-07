package io.spring2go.hystrixlab.schoolservice.delegate;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class StudentServiceDelegate {

	@Autowired
	RestTemplate restTemplate;

	 @HystrixCommand
//    	@HystrixCommand(commandProperties = {
//    			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000") })
//	@HystrixCommand(fallbackMethod = "callStudentService_Fallback")
	// @HystrixCommand(fallbackMethod = "callStudentService_Fallback",
	// 		threadPoolKey = "studentServiceThreadPool",
	// 		threadPoolProperties = {
	// 				@HystrixProperty(name="coreSize", value="30"),
	// 				@HystrixProperty(name="maxQueueSize", value="10")
	// 		}
	// 		)
	public String callStudentService(String schoolName) {

		System.out.println("Getting School details for " + schoolName);

		String response = restTemplate.exchange("http://localhost:8090/getStudentBySchool/{schoolName}", HttpMethod.GET,
				null, new ParameterizedTypeReference<String>() {

				}, schoolName).getBody();

		System.out.println("Response Received as " + response + " - " + new Date());

		return "NORMAL FLOW !!! - School Name - " + schoolName + " ::: " + " Student Details " + response + " - "
				+ new Date();
	}

	@SuppressWarnings("unused")
	private String callStudentService_Fallback(String schoolName) {

		System.out.println("Student Service is down!!! fallback route enabled...");

		return "CIRCUIT BREAKER ENABLED!!! NO Response From Student Service ath this moment. "
				+ " Service will be back shortly - " + new Date();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
