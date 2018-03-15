//package test;
//
//import java.io.File;
//import java.io.InputStream;
//import java.util.List;
//import java.util.zip.ZipInputStream;
//
//import org.flowable.engine.ProcessEngine;
//import org.flowable.engine.ProcessEngineConfiguration;
//import org.flowable.engine.RepositoryService;
//import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
//import org.flowable.engine.repository.Deployment;
//import org.flowable.engine.repository.ProcessDefinition;
//import org.flowable.engine.runtime.ProcessInstance;
//import org.flowable.task.api.Task;
//import org.flowable.task.api.history.HistoricTaskInstance;
//import org.junit.Test;
//
//public class LeaveProcessTest {
//	ProcessEngine processEngine = null;
//	
//	//1.创建流程引擎
//	@Test
//	public void createProcessEngine() throws Exception {
//        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
//                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
//                .setJdbcUsername("sa")
//                .setJdbcPassword("")
//                .setJdbcDriver("org.h2.Driver")
//                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
// 
//        processEngine = cfg.buildProcessEngine();
//        System.out.println(processEngine);
//	}
//	
//	//2.发布流程
//	@Test
//	public void deploy() throws Exception{
//		//1.发布之前先获取流程引擎
//		createProcessEngine();
//		//ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine(); //此处获取不到?,报空指针
//		//获取仓库服务的实例
//		Deployment deployment = processEngine.getRepositoryService()
//											.createDeployment()
//											.name("请假流程")
//											.addClasspathResource("processes/leave.bpmn20.xml")
//											.addClasspathResource("processes/leave.png")
//											.deploy();
//		System.out.println(deployment.getId() + "  :  " + deployment.getName());
//		
//	}
//	
//	//3.启动流程
//	@Test
//	public void startProcess() throws Exception{
//		//2.启动之前先发布
//		deploy();
//		//创建流程实例
//		ProcessInstance pi = processEngine.getRuntimeService()
//										  .startProcessInstanceByKey("leaveProcess");
//		System.out.println("pid:"+pi.getId()+" , acitivitiId:"+pi.getActivityId());
//			
//	}
//	
//	//4.查看任务
//	@Test
//	public void queryTask() throws Exception{
//		//3.查看任务前先启动流程
//		startProcess();
//		//任务执行者
//		String assignee = "James";
//		//查看任务列表
//		List<Task> tasks = processEngine.getTaskService() //获取任务服务
//										.createTaskQuery() //创建任务查询
//										.taskAssignee(assignee) //指定任务执行者
//										.list();
//		//查看任务内容
//		for(Task task:tasks) {
//			System.out.println("taskId:"+task.getId() + ",taskName:"+ task.getName());
//			System.out.println("=========");
//		}
//		
//	}
//	
//	//5.办理任务
//	@Test
//	public void completeTask() throws Exception{
//		//3.先启动流程
//		startProcess();
//		//任务执行者
//		String taskId = "5";
//		processEngine.getTaskService().complete(taskId);
//		System.out.println("任务已完成");
//	}
//	
//	// 6.查询流程定义 
//	 @Test
//    public void findProcessDefinition() throws Exception {
//		//3.先启动流程
//		startProcess();
//        List<ProcessDefinition> list = processEngine.getRepositoryService()// 与流程定义和部署对象相关的Service
//                .createProcessDefinitionQuery()// 创建一个流程定义的查询
//                /** 指定查询条件,where条件 */
//                // .deploymentId(deploymentId)//使用部署对象ID查询
//                // .processDefinitionId(processDefinitionId)//使用流程定义ID查询
//                // .processDefinitionKey(processDefinitionKey)//使用流程定义的key查询
//                // .processDefinitionNameLike(processDefinitionNameLike)//使用流程定义的名称模糊查询
//
//                /** 排序 */
//                .orderByProcessDefinitionVersion().asc()// 按照版本的升序排列
//                // .orderByProcessDefinitionName().desc()//按照流程定义的名称降序排列
//
//                /** 返回的结果集 */
//                .list();// 返回一个集合列表，封装流程定义
//		        // .singleResult();//返回惟一结果集
//		        // .count();//返回结果集数量
//		        // .listPage(firstResult, maxResults);//分页查询
//        if (list != null && list.size() > 0) {
//            for (ProcessDefinition pd : list) {
//                System.out.println("流程定义ID:" + pd.getId());// 流程定义的key+版本+随机生成数
//                System.out.println("流程定义的名称:" + pd.getName());// 对应helloworld.bpmn文件中的name属性值
//                System.out.println("流程定义的key:" + pd.getKey());// 对应helloworld.bpmn文件中的id属性值
//                System.out.println("流程定义的版本:" + pd.getVersion());// 当流程定义的key值相同的相同下，版本升级，默认1
//                System.out.println("资源名称bpmn文件:" + pd.getResourceName());
//                System.out.println("资源名称png文件:" + pd.getDiagramResourceName());
//                System.out.println("部署对象ID：" + pd.getDeploymentId());
//                System.out.println("##############################################");
//                        
//            }
//        }
//    }
//	 
//	//部署流程文件(zip格式) 
//    @Test
//    public void deployZIP() throws Exception {
//    		createProcessEngine();
//    		InputStream in = this.getClass().getClassLoader().getResourceAsStream("processes/leave.zip");
//    		ZipInputStream zipInputStream = new ZipInputStream(in);
//    		Deployment deployment = processEngine.getRepositoryService()
//    											.createDeployment()
//    											.name("leave process")
//    											.addZipInputStream(zipInputStream)
//    											.deploy();
//    	  System.out.println(deployment.getId() + "		" + deployment.getName());
//    }
//    
//    //删除流程
//    @Test
//    public void deleteProcess() throws Exception {
//    		createProcessEngine();
//    		String deploymentId = "1";
//
//    		RepositoryService repositoryService = processEngine.getRepositoryService();
//    		//普通删除,如果当前规则下有正在执行的流程,则抛异常
//    		repositoryService.deleteDeployment(deploymentId);
//    		//级联删除,会删除和当前规则相关的所有信息,正在执行的信息,也包括历史信息
//    		
//    		repositoryService.deleteDeployment(deploymentId, true);
//    		
//    }
//    
//    //查看流程附件(流程图片)
//    @Test
//    public void viewImage() throws Exception {
//    		deployZIP();
//    		String deploymentId = "1";
//    		List<String> names = processEngine.getRepositoryService()
//    										.getDeploymentResourceNames(deploymentId);
//    		String imageName = null;
//    		for(String name:names) {
//    			System.out.println("name:"+name);
//    			if(name.indexOf(".png") >= 0) {
//    				imageName = name;
//    			}
//    		}
//    		if(imageName != null) {
//    			File f = new File("/Users/huanggangyu/Desktop/"+imageName);
//    			//通过部署id和文件名得到文件的输入流
//    			InputStream in = processEngine.getRepositoryService()
//    										.getResourceAsStream(deploymentId, imageName);
//    			
//    		}								
//    		
//    }
//    
//    @Test
//    public void deleteProcessDefinitionByKey() throws Exception {
//    		deployZIP();
//        // 流程定义的key
//        String processDefinitionKey = "leaveProcess";
//        // 先使用流程定义的key查询流程定义，查询出所有的版本
//        List<ProcessDefinition> list = processEngine.getRepositoryService()
//                .createProcessDefinitionQuery()//
//                .processDefinitionKey(processDefinitionKey).list();
//        // 遍历，获取每个流程定义的部署ID
//        if (list != null && list.size() > 0) {
//            for(ProcessDefinition pd:list){
//                //获取部署ID
//                String deploymentId = pd.getDeploymentId();
//                processEngine.getRepositoryService()//
//                            .deleteDeployment(deploymentId, true); 
//            }
//        }
//    }
//    
//    //查看流程状态
//    @Test
//    public void isProcessEnd() throws Exception {
//    		deployZIP();
//        String processInstanceId = "501";
//        ProcessInstance pi = processEngine.getRuntimeService()// 表示正在执行的流程实例和执行对象
//                .createProcessInstanceQuery()// 创建流程实例查询
//                .processInstanceId(processInstanceId)// 使用流程实例ID查询
//                .singleResult();
//        if (pi == null) {
//            System.out.println("流程已经结束");
//        } else {
//            System.out.println("流程没有结束");
//        }
//    }
//    
//    //查询历史任务
//    @Test
//    public void findHistoryTask() throws Exception {
//		deployZIP();
//        String taskAssignee = "Sandy";
//        List<HistoricTaskInstance> list = processEngine.getHistoryService()//与历史数据（历史表）相关的Service
//                        .createHistoricTaskInstanceQuery()//创建历史任务实例查询
//                        .taskAssignee(taskAssignee)//指定历史任务的办理人
//                        .list();
//        if(list!=null && list.size()>0){
//            for(HistoricTaskInstance hti:list){
//                System.out.println(hti.getId()+"    "+hti.getName()+"    "+hti.getProcessInstanceId()+"   "+hti.getStartTime()+"   "+hti.getEndTime()+"   "+hti.getDurationInMillis());
//                System.out.println("################################");
//            }
//        }
//    }
//
//}
