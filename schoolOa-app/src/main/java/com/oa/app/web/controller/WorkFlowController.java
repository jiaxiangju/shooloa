package com.oa.app.web.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oa.app.service.workFlow.IFlowStepService;
import com.oa.app.service.workFlow.IWorkFlowService;
import com.oa.common.entity.FlowStep;
import com.oa.common.entity.WorkFlow;
import com.oa.common.util.JsonDateValueProcessor;
import com.oa.common.util.PageUtil;

@Controller
public class WorkFlowController {
	@Resource(name = "workFlowService")
	private IWorkFlowService workFlowService;
	@Resource(name = "flowStepService")
	private IFlowStepService flowStepService;
	
	@RequestMapping(value = "/resource/workFlow",method=RequestMethod.GET)
	public void list(PageUtil pageUtil,String keyWord,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException {
		keyWord = request.getParameter("key");
		
		HashMap<String,Object> map = new HashMap<String, Object>();
		map = workFlowService.queryWorkFlowsByPage(pageUtil, keyWord);
		String json = getJSONString(map);
		response.getWriter().write(json);
		
	}
	
	@RequestMapping(value = "/resource/workFlow/{id}",method=RequestMethod.GET)
	public void get(@PathVariable String id,HttpServletRequest request, HttpServletResponse response) throws IOException {
		WorkFlow workFlow = workFlowService.queryWfById(Long.valueOf(id));
		String json = getJSONString(workFlow);
		response.getWriter().write(json);
		
	}
	
	@RequestMapping(value = "/resource/workFlow",method=RequestMethod.POST)
	public void add(@ModelAttribute("workFlow") WorkFlow workFlow,HttpServletRequest request, HttpServletResponse response) throws IOException{
		workFlowService.addWorkFlow(workFlow);
		String json = getJSONString(workFlow);
		response.getWriter().write(json);
	}
	
	@RequestMapping(value = "/resource/workFlow",method=RequestMethod.PUT)
	public void update(WorkFlow workFlow,HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id = request.getParameter("id");
		workFlow.setId(Long.valueOf(id));
		workFlowService.updateWorkFlow(workFlow);
		String json = getJSONString(workFlow);
		response.getWriter().write(json);
	}
	
	@RequestMapping(value = "/resource/workFlow/{ids}",method=RequestMethod.DELETE)
	public void delete(@PathVariable String ids,HttpServletRequest request, HttpServletResponse response) throws IOException{
		String[] id = ids.split(",");
		for (String string : id) {
			WorkFlow workFlow = new WorkFlow();
			workFlow.setId(Long.valueOf(string));
			workFlowService.deleteWorkFlow(workFlow);
		}
		response.getWriter().write("true");
	}
	
	
	@RequestMapping(value = "/resource/workFlowStep/{workFlowId}",method=RequestMethod.GET)
	public void getWfSteps(PageUtil pageUtil,@PathVariable String workFlowId, HttpServletRequest request, HttpServletResponse response) throws IOException{
		HashMap<String,Object> map = new HashMap<String, Object>();
		map = flowStepService.queryFlowStepByPage(pageUtil, workFlowId);
		String json = getJSONString(map);
		response.getWriter().write(json);
	}
	
	@RequestMapping(value = "/resource/workFlowStep",method=RequestMethod.POST)
	public void getWfSteps(@ModelAttribute("flowStep") FlowStep flowStep,  HttpServletRequest request, HttpServletResponse response) throws IOException{
		WorkFlow wf = workFlowService.queryWfById(flowStep.getWorkFlowId());
		flowStep.setWorkFlow(wf);
		flowStepService.addFlowStep(flowStep);
	}
	
	@RequestMapping(value = "/resource/workFlowStep",method=RequestMethod.PUT)
	public void update(FlowStep flowStep,HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id = request.getParameter("id");
		flowStep.setId(Long.valueOf(id));
		
		WorkFlow wf = workFlowService.queryWfById(flowStep.getWorkFlowId());
		flowStep.setWorkFlow(wf);
		flowStepService.updateFlowStep(flowStep);
	}
	
	@RequestMapping(value = "/resource/workFlowStep/{id}",method=RequestMethod.DELETE)
	public void deleteFlowStep(@PathVariable String id,HttpServletRequest request, HttpServletResponse response) throws IOException{
		FlowStep flowStep = new FlowStep();
		flowStep.setId(Long.valueOf(id));
		flowStepService.deleteFlowStep(flowStep);
		response.getWriter().write("true");
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 对象转换为json
	 * @param object
	 * @return
	 */
	public static String getJSONString(Object object){
		String jsonString = null;
		//日期值处理器
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if(arg1.equals("flowSteps")) { //要过滤的areas ，Map对象中的
		            return true;
		        } else {
		            return false;
		        }
			}
		});


		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
		if(object != null){
			if(object instanceof Collection || object instanceof Object[]){
				jsonString = JSONArray.fromObject(object, jsonConfig).toString();
			}else{
				jsonString = JSONObject.fromObject(object, jsonConfig).toString();
			}
		}
		return jsonString == null ? "{}" : jsonString;
	}
}
