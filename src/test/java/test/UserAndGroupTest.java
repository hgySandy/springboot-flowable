//package test;
//
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//
//import org.flowable.engine.IdentityService;
//import org.flowable.engine.ProcessEngine;
//import org.flowable.engine.ProcessEngineConfiguration;
//import org.flowable.idm.api.Group;
//import org.flowable.idm.api.Picture;
//import org.flowable.idm.api.User;
//import org.junit.Before;
//import org.junit.Test;
//
//public class UserAndGroupTest {
//	private IdentityService identityService = null;
//	
//	@Before
//	public void createIdentityService() {
//		ProcessEngine processEngine = ProcessEngineConfiguration
//				.createProcessEngineConfigurationFromResourceDefault()
//				.buildProcessEngine();
//		// 得到身份服务组件实例
//		identityService = processEngine.getIdentityService();
//	}
//	
//	/**
//	 * 创建用户组
//	 * */
//	@Test
//	public void saveGroup(){
//
//		// 调用newGroup方法创建Group实例
//		 Group group = identityService.newGroup("1");
//		// 为了使用Activiti自己的ID生成策略
//		 group.setId(null);
//		 group.setName("ROLE_USER");
//		 group.setType("ROLE_USER");
//		// 将Group保存到数据库
//		 identityService.saveGroup(group);
//	}
//	
//	/**
//	 * 修改用户组
//	 * */
//	@Test
//	public void modifyGroup(){
//		
//		 Group data =  identityService.createGroupQuery().groupId("347501").singleResult();
//		// data.setId("1"); ID是不能修改的
//		 data.setName("management");
//		 data.setType("owner");
//		 identityService.saveGroup(data);
//	}
//	
//	/**
//	 * 删除用户组
//	 * */
//	@Test
//	public void deleteGroup(){
//		 identityService.deleteGroup("350001");
//	}
//	
//
//	/**
//	 * 添加用户
//	 * */
//	@Test
//	public void saveUser(){
//		
//		 User user = identityService.newUser("2");
//		 user.setId(null);
////		 user.setFirstName("Lily");
////		 user.setLastName("King");
////		 user.setEmail("114566@qq.com");
////		 user.setPassword("test");
//		 user.setFirstName("John");
//		 user.setLastName("Smith");
//		 user.setEmail("11539166@qq.com");
//		 user.setPassword("test");
//		 identityService.saveUser(user);
//	}
//	
//	/**
//	 * 修改用户
//	 * */
//	@Test
//	public void modifyUser(){
//		 User user = identityService.createUserQuery().userId("352501").singleResult();
//		 user.setFirstName("Bob");
//		 identityService.saveUser(user);
//	}
//	
//	/**
//	 * 删除用户
//	 * */
//	@Test
//	public void deleteUser(){
//		 identityService.deleteUser("352501");
//	}
//	
//	
//	/**
//	 * 验证用户密码
//	 * */
//	@Test
//	public void checkPassword(){
//		
//		// 这里的密码是用户的密码，不是用户帐号的密码
//		 System.out.println("验证用户密码的结果1 = "+ identityService.checkPassword("355001", "test"));
//		 System.out.println("验证用户密码的结果2 = "+ identityService.checkPassword("355001", "ac"));
//	}
//	
//	/**
//	 * 设置用户权限，也就是将用户ID设置到当前的线程中
//	 * */
//	@Test
//	public void setAuthenticatedUserId(){
//		identityService.setAuthenticatedUserId("355001");
//	}
//
//	/**
//	 * 添加和删除用户信息
//	 * */
//	@Test
//	public void setAndDeleteUser(){
//		
//		// 创建两个用户信息 用户的信息保存在ACT_ID_INFO
//		 identityService.setUserInfo("355001", "height", "175cm");
//		 identityService.setUserInfo("355001", "weight", "60kg");
//		// 删除用户的体重信息
//		 identityService.deleteUserInfo("355001", "weight");
//	}
//	
//	/**
//	 * 查询用户信息
//	 * */
//	@Test
//	public void getUserInfo(){
//		 String value = identityService.getUserInfo("355001", "height");
//		 System.out.println(value);
//	}
//	
//	/**
//	 * 设置用户图片
//	 * @throws IOException 
//	 * */
//	@Test
//	public void setUserPicture() throws Exception{
//
//		// 建立一个文件流把图片读取进来
//		 FileInputStream inputStream = new FileInputStream(new File("/Users/huanggangyu/Downloads/使用Git命令从Github下载代码仓库 - CSDN博客_files/2_kongying19910218.jpg"));
//
//		// 生成java的图片
//		 BufferedImage image = ImageIO.read(inputStream);
//		// 定义一个内存输出流
//		 ByteArrayOutputStream out = new ByteArrayOutputStream();
//		// 把图片写入内存输出流中
//		 ImageIO.write(image, "jpg", out);
//		 byte[] picArray = out.toByteArray();
//		 Picture picture = new Picture(picArray, "cc pic");
//		 identityService.setUserPicture("355001", picture);
//	}
//	
//	/**
//	 * 绑定用户与用户组的关系
//	 * */
//	@Test
//	public void createMembership(){
//		identityService.createMembership("867501", "862501"); //867501  862501
//		identityService.createMembership("870001", "865001"); //870001  865001
//	}
//	
//	/**
//	 * 解除用户组与用户的绑定关系
//	 * */
//	@Test
//	public void deleteMembership(){
//		 identityService.deleteMembership("37501", "32501");
//	}
//	
//	/**
//	 * 查询用户组下的用户
//	 * */
//	@Test
//	public void userQuery(){
//
//		 User user = identityService.createUserQuery().memberOfGroup("347501").singleResult();
//		 System.out.println(user.getFirstName()+" "+user.getLastName());
//	}
//	
//	/**
//	 * 查询用户所属的用户组
//	 * */
//	@Test
//	public void groupQuery(){
//		 Group group = identityService.createGroupQuery().groupMember("355001").singleResult();
//		 System.out.println(group.getName());
//	}
//	
//
//}
//
