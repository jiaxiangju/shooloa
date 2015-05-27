package com.oa.common.util;
/**
 * 
 * 类名称:    [PageUtil]  
 * 类描述:    [分页处理实体]
 * 创建人:    [LIUXX]   
 * 创建时间:  [2014年8月1日 下午4:28:46]   
 * 修改人:    [LIUXX]   
 * 修改时间:  [2014年8月1日 下午4:28:46]   
 * 修改备注:  [说明本次修改内容]  
 * 版本:      [v1.0]   
 *
*/
public class PageUtil {
	/**每页显示多少**/
	private int pageSize;
	/**当前页**/
	private int pageIndex;
	/**排序  desc、asc	 */
	private String sortOrder;
	/**排序字段**/
	private String sortField;


	public int getPageSize() {
		return pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

}
