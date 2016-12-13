package com.storm.steven.spring;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller //负责注册一个bean 到spring 上下文中
public class TestSpring {
	public static final String SUCCESS = "success";
	////四、自动匹配参数
	@RequestMapping("testJson")
	public String testJson(){
		System.out.println("testJson");
		return SUCCESS;
	}
	/**
	 * HttpMessageConverter<T> 是 Spring3.0 新添加的一个接口，负责将请求信息转换为一个对象（类型为 T），将对象（类型为 T）输出为响应信息
	 */
	/** HttpMessageConverter消息转化器
	 * 使用 HttpMessageConverter<T> 将请求信息转化并绑定到处理方法的入
		参中或将响应结果转为对应类型的响应信息，Spring 提供了两种途径：
		– 使用 @RequestBody / @ResponseBody 对处理方法进行标注
		– 使用 HttpEntity<T> / ResponseEntity<T> 作为处理方法的入参或返回值
	 * @param body
	 * @return
	 */
	////四、自动匹配参数
	@ResponseBody
	@RequestMapping("testHttpMessageConverter" )
	public String testHttpMessageConverter(@RequestBody String body){
		System.out.println(body);
		return "HelloWord:"+new Date();
	}	
	/**
	 * 文件的下载
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/testResponseEntity")
	public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException{
		byte [] body = null;
		ServletContext servletContext = session.getServletContext();
		InputStream in = servletContext.getResourceAsStream("/files/abc.txt");
		body = new byte[in.available()];
		in.read(body);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=abc.txt");
		HttpStatus statusCode = HttpStatus.OK;
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
		return response;
	}

	@ResponseBody
	@RequestMapping("getUsers")
	public String getUsers(){
		User user=new User();
		user.setId(1);
		user.setUsername("steven");
		user.setAge(22);
		return user.toString();
	}	
	//文件的上传
	@RequestMapping("testUpFileLoad")
	public String testUpFileLoad(@RequestParam("desc") String desc,
			@RequestParam("file") MultipartFile[]  items_pic) throws IOException{
		//多个图片，不存数据库了，在此打印一下即可
		for(MultipartFile myfile : items_pic) {
			if(myfile.isEmpty()){  
				System.out.println("文件未上传");  
			}else{  
				System.out.println("文件长度: " + myfile.getSize());  
				System.out.println("文件类型: " + myfile.getContentType());  
				System.out.println("文件名称: " + myfile.getName());  
				System.out.println("文件原名: " + myfile.getOriginalFilename());  
				System.out.println("========================================");  
				//写入磁盘，和上面的单个文件上传一模一样
				String originalFileName = myfile.getOriginalFilename();
				String pic_path = "D:\files";
				String newFileName = UUID.randomUUID()
						+ originalFileName.substring(originalFileName
								.lastIndexOf("."));
				System.out.println("-----"+newFileName);
				File newFile = new File(pic_path + newFileName);
				myfile.transferTo(newFile);
			}  
		}   
		return SUCCESS;
	}


	@RequestMapping("testInterceptor")
	public String testInterceptor(){
		System.out.println("testInterceptor");
		return SUCCESS;
	}

	/**
	 * SpringMVC异常处理
	 */
	@RequestMapping("testHandlerExceptionResolver")
	public String testHandlerExceptionResolver(@RequestParam("i") int i){
		System.out.println("reslut:"+10/i);
		return SUCCESS;
	}
	/**
	 * SpringMVC异常处理
	 */
	/**
	 * 
	1  在@ExceptionHandler方法入参中可以加入Exception类型的参数，该参数即对应发生的异常对象
	2 ExceptionHandler不能传入Map。若希望把异常信息传到页面上，需要使用ModelAndView 作为返回值
	3 ExceptionHandler 标记的异常有优先级问题
	4 @ControllerAdvice:如果在Handler找不到@ExceptionHandler方法来出当前的方法异常
	 则将去@ControllerAdvice标记的类中去找查找@ExceptionHandler标记的方法来处理异常
	 */
	@ExceptionHandler({ArithmeticException.class})  //注解到方法上，出现异常时会执行该方法
	public ModelAndView testHandlerArithmeticException(Exception exception){
		ModelAndView modelAndView=new ModelAndView("error");
		System.out.println("exception:"+exception.getMessage());
		modelAndView.addObject("exception",exception);
		return modelAndView;
	}
	//	@ExceptionHandler({RuntimeException.class})
	//	public ModelAndView testHandlerArithmeticException2(Exception exception){
	//		ModelAndView modelAndView=new ModelAndView("error");
	//		System.out.println("【exception】:"+exception.getMessage());
	//		modelAndView.addObject("exception",exception);
	//		return modelAndView;
	//	}

	@RequestMapping("testResponseStatusExceptionResolver")  //在异常及异常父类中找到 @ResponseStatus
	//注解，然后使用这个注解的属性进行处理。
	public String testResponseStatusExceptionResolver(@RequestParam("i") int i){
		if(i==13){
			throw new UserNameNotMatchPasswordException();
		}
		System.out.println("testResponseStatusExceptionResolver");
		return SUCCESS;
	}
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="testDefaultHandlerExceptionResolver",method=RequestMethod.GET)
	public String testDefaultHandlerExceptionResolver(){
		System.out.println("testDefaultHandlerExceptionResolver");
		return SUCCESS;
	}
	@RequestMapping("testSimpleMappingExceptionResolver")
	public String testSimpleMappingExceptionResolver(@RequestParam("i") int i){
		String[] values=new String[10];
		System.out.println(values[i]);
		return SUCCESS;
	}
	@RequestMapping("login")
	public String testLogin(HttpServletRequest request){
		String username=request.getParameter("username");
		System.out.println("===="+username);
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		System.out.println("testLogin");
		return SUCCESS;
	}
	@RequestMapping("testConversionService")
	public String testConversionService(@RequestParam("user") User user){
		System.out.println("----ADD----");
		return SUCCESS;
	}
}
