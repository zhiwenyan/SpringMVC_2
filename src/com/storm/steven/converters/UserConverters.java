package com.storm.steven.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.storm.steven.spring.User;

@Component
public class UserConverters implements Converter<String, User>{
	@Override
	public User convert(String arg0) {
		if(arg0!=null){
			String[] vals=arg0.split("-");
			if(vals!=null&&vals.length==2){
				String name=vals[0];
				int age=Integer.parseInt(vals[1]);
				User user=new User(name, age);
				System.out.println(arg0+"------>"+user);
				return user;
			}
		}
		return null;
	}

}
