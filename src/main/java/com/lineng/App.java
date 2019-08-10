package com.lineng;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ServletComponentScan
@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.lineng.mapper")
public class App implements CommandLineRunner {
	
//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		super.configureMessageConverters(converters);
//		
//		/*
//		 * 1、需要先定义一个 convert 转换消息的对象;
//		 * 2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
//		 * 3、在convert中添加配置信息.
//		 * 4、将convert添加到converters当中.
//		 * 
//		 */
//		
//		// 1、需要先定义一个 convert 转换消息的对象;
//		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//		
//		//2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
//		FastJsonConfig fastJsonConfig = new FastJsonConfig();
//		fastJsonConfig.setSerializerFeatures(
//                SerializerFeature.PrettyFormat
//        );
//		
//		//3、在convert中添加配置信息.
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        
//        //4、将convert添加到converters当中.
//    	converters.add(fastConverter);
//		
//	}


	/**
	 * 在这里我们使用 @Bean注入 fastJsonHttpMessageConvert
	 * @return
	 */
/*	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		// 1、需要先定义一个 convert 转换消息的对象;
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		
		//2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
		
		//3、在convert中添加配置信息.
		fastConverter.setFastJsonConfig(fastJsonConfig);
		
		
		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}*/

	/**
	 * redisTemplate 序列化使用的jdkSerializeable, 存储二进制字节码, 所以自定义序列化类
	 * @param redisConnectionFactory
	 * @return
	 */
	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate();
		redisTemplate.setConnectionFactory(redisConnectionFactory);

		// 使用Jackson2JsonRedisSerialize 替换默认序列化
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

		// 设置value的序列化规则和 key的序列化规则
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	/**
	 * 这是springloader的配置方式：-javaagent:.\lib\springloaded-1.2.4.RELEASE.jar -noverify
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * 在main方法进行启动我们的应用程序.
		 */
		System.setProperty("es.set.netty.runtime.available.processors", "false");
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
