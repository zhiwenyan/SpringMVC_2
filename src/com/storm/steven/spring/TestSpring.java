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

@Controller //����ע��һ��bean ��spring ��������
public class TestSpring {
	public static final String SUCCESS = "success";
	////�ġ��Զ�ƥ�����
	@RequestMapping("testJson")
	public String testJson(){
		System.out.println("testJson");
		return SUCCESS;
	}
	/**
	 * HttpMessageConverter<T> �� Spring3.0 ����ӵ�һ���ӿڣ�����������Ϣת��Ϊһ����������Ϊ T��������������Ϊ T�����Ϊ��Ӧ��Ϣ
	 */
	/** HttpMessageConverter��Ϣת����
	 * ʹ�� HttpMessageConverter<T> ��������Ϣת�����󶨵�����������
		���л���Ӧ���תΪ��Ӧ���͵���Ӧ��Ϣ��Spring �ṩ������;����
		�C ʹ�� @RequestBody / @ResponseBody �Դ��������б�ע
		�C ʹ�� HttpEntity<T> / ResponseEntity<T> ��Ϊ����������λ򷵻�ֵ
	 * @param body
	 * @return
	 */
	////�ġ��Զ�ƥ�����
	@ResponseBody
	@RequestMapping("testHttpMessageConverter" )
	public String testHttpMessageConverter(@RequestBody String body){
		System.out.println(body);
		return "HelloWord:"+new Date();
	}	
	/**
	 * �ļ�������
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
	//�ļ����ϴ�
	@RequestMapping("testUpFileLoad")
	public String testUpFileLoad(@RequestParam("desc") String desc,
			@RequestParam("file") MultipartFile[]  items_pic) throws IOException{
		//���ͼƬ���������ݿ��ˣ��ڴ˴�ӡһ�¼���
		for(MultipartFile myfile : items_pic) {
			if(myfile.isEmpty()){  
				System.out.println("�ļ�δ�ϴ�");  
			}else{  
				System.out.println("�ļ�����: " + myfile.getSize());  
				System.out.println("�ļ�����: " + myfile.getContentType());  
				System.out.println("�ļ�����: " + myfile.getName());  
				System.out.println("�ļ�ԭ��: " + myfile.getOriginalFilename());  
				System.out.println("========================================");  
				//д����̣�������ĵ����ļ��ϴ�һģһ��
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
	 * SpringMVC�쳣����
	 */
	@RequestMapping("testHandlerExceptionResolver")
	public String testHandlerExceptionResolver(@RequestParam("i") int i){
		System.out.println("reslut:"+10/i);
		return SUCCESS;
	}
	/**
	 * SpringMVC�쳣����
	 */
	/**
	 * 
	1  ��@ExceptionHandler��������п��Լ���Exception���͵Ĳ������ò�������Ӧ�������쳣����
	2 ExceptionHandler���ܴ���Map����ϣ�����쳣��Ϣ����ҳ���ϣ���Ҫʹ��ModelAndView ��Ϊ����ֵ
	3 ExceptionHandler ��ǵ��쳣�����ȼ�����
	4 @ControllerAdvice:�����Handler�Ҳ���@ExceptionHandler����������ǰ�ķ����쳣
	 ��ȥ@ControllerAdvice��ǵ�����ȥ�Ҳ���@ExceptionHandler��ǵķ����������쳣
	 */
	@ExceptionHandler({ArithmeticException.class})  //ע�⵽�����ϣ������쳣ʱ��ִ�и÷���
	public ModelAndView testHandlerArithmeticException(Exception exception){
		ModelAndView modelAndView=new ModelAndView("error");
		System.out.println("exception:"+exception.getMessage());
		modelAndView.addObject("exception",exception);
		return modelAndView;
	}
	//	@ExceptionHandler({RuntimeException.class})
	//	public ModelAndView testHandlerArithmeticException2(Exception exception){
	//		ModelAndView modelAndView=new ModelAndView("error");
	//		System.out.println("��exception��:"+exception.getMessage());
	//		modelAndView.addObject("exception",exception);
	//		return modelAndView;
	//	}

	@RequestMapping("testResponseStatusExceptionResolver")  //���쳣���쳣�������ҵ� @ResponseStatus
	//ע�⣬Ȼ��ʹ�����ע������Խ��д���
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
