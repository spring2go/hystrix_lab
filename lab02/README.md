实验二、Hystrix+Dashboard实验
======

### 实验步骤

#### 1. 克隆Netflix Hystrix和Hystrix-Dashboard项目到本地

```
https://github.com/Netflix/Hystrix.git
https://github.com/Netflix-Skunkworks/hystrix-dashboard
```

**注意**：hystrix-dashboard缺失一个`gradle-wrapper.jar`文件，可以将Hystrix中的`wrapper`文件夹复制并覆盖hystrix-dashboard下的`wrapper`文件夹。


#### 2. 运行hystrix-example-webapp应用

```
cd hystrix-examples-webapp
../gradlew appRun
```

打开`http://localhost:8989/hystrix-examples-webapp`校验

#### 3. 运行hystrix-dashboard应用

```
cd hystrix-dashboard
./gradlew appRun
```

打开`http://localhost:7979/hystrix-dashboard`校验

添加`http://localhost:8989/hystrix-examples-webapp/hystrix.stream`，并启动hystrix流监控

##### 4. 运行curl.sh脚本触发hystrix流

```sheel
while true; 
do curl "http://localhost:8989/hystrix-examples-webapp/"; 
done
```

##### 5. 观察hystrix-dashboard实时流


