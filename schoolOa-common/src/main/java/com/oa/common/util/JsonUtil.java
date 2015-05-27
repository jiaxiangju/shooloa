package com.oa.common.util;

import java.util.Collection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

/**
 *
  *
  * 类名称:    [JsonUtil]
  * 类描述:    []
  * 创建人:    [LIUXX]
  * 修改人:    [LIUXX]
  * 修改备注:  [说明本次修改内容]
  * 版本:      [v1.0]
  *
 */
public class JsonUtil {
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
				if(arg1.equals("fonds")) { //要过滤的areas ，Map对象中的
		            return true;
		        } else if(arg1.equals("roles")){
		        	return true;
		        }else if(arg1.equals("applyForm")){
		        	return true;
		        }else if(arg1.equals("applyItems")){
		        	return true;
		        }else if(arg1.equals("applyFormDzjy")){
		        	return true;
		        }else if(arg1.equals("applyItemDzjys")){
		        	return true;
		        }else if(arg1.equals("classMgmt")){
		            return true;
		        }else if(arg1.equals("users")){
		        	return true;
		        }else if(arg1.equals("user")){
		        	return true;
		        }else if(arg1.equals("nodeValues")){
		        	return true;
		        }else if(arg1.equals("basicStateReport")){
		        	return true;
		        }else if(arg1.equals("reportValue")){
		        	return true;
		        }else if(arg1.equals("creator")){
		        	return true;
		        }else {
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
