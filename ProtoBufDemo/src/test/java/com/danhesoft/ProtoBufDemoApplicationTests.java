package com.danhesoft;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProtoBufDemoApplicationTests {

	@Test
	public void contextLoads() {
		//序列化过程
		FirstProtobuf.Person.Builder personBuild = FirstProtobuf.Person.newBuilder();
		personBuild.setPersonName("曹伟");
		personBuild.setPersonAge(32);
		personBuild.setPersonSex("男");
		personBuild.addFriends("周永灿");
		personBuild.addFriends("金浩");

		FirstProtobuf.Person person = personBuild.build();
		byte[] person_serial = person.toByteArray();

		System.out.println("序列化完成");

		//反序列化
		ByteArrayInputStream input = new ByteArrayInputStream(person_serial);
		try {
			FirstProtobuf.Person newPerson = FirstProtobuf.Person.parseFrom(person_serial);
			if(newPerson!=null){
				System.out.println(newPerson.getPersonName());
				System.out.println(newPerson.getPersonAge());
				System.out.println(newPerson.getPersonSex());
				List<String> list = newPerson.getFriendsList();
				for(String friend: list){
					System.out.println(friend);
				}

			}
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}


	}

}
