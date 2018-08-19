实验三、Spring Cloud Hystrix实验
======

### 实验步骤

#### 1. 将student和school微服务项目导入Eclipse IDE


**注意**：school项目依赖

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-hystrix</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
		</dependency>
		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
```


#### 2. 运行student-springboot微服务项目

#### 3. 运行school-springboot微服务项目

通过postman调用school微服务

```
http://localhost:8088/getSchoolDetails/abcschool
```

修改`io.spring2go.hystrixlab.schoolservice.delegate.StudentServiceDelegate`中`StudentServiceDelegate`方法上的标注，依次实验各种Hystrix Command标注用法：

1. 简单用法
```java
	 @HystrixCommand
```

2. 定制超时
```java
    @HystrixCommand(commandProperties = {
    		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000") })
```

3. 定制降级方法
```java
    @HystrixCommand(fallbackMethod = "callStudentService_Fallback")
```

4. 定制线程池隔离策略
```java
	@HystrixCommand(fallbackMethod = "callStudentService_Fallback",
			threadPoolKey = "studentServiceThreadPool",
			threadPoolProperties = {
					@HystrixProperty(name="coreSize", value="30"),
					@HystrixProperty(name="maxQueueSize", value="10")
			}
			)
```

5. 查看hystrix stream和dashboard

hystrix stream:
```
http://localhost:8088/hystrix.stream
```

hystrix dashboard 
```
http://localhost:8088/hystrix
```




