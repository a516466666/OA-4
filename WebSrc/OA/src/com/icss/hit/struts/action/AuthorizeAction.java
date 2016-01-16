/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.icss.hit.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.icss.hit.bean.ConfigWorkBean;
import com.icss.hit.bean.DepartmentBean;
import com.icss.hit.bean.UserInfoBean;
import com.icss.hit.bean.interfaces.ConfigWork;
import com.icss.hit.bean.interfaces.Department;
import com.icss.hit.component.PageBean;
import com.icss.hit.hibernate.vo.ScheduleConfig;
import com.icss.hit.hibernate.vo.SysDept;
import com.icss.hit.hibernate.vo.SysUser;

/** 
 * 授权指定ID员工代办工作
 * Creation date: 08-04-2009
 * @author 朱金彪
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="authorize.succeed" path="/work/configWork.jsp"
 */
public class AuthorizeAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 授权人ID
		long auId=-1;
		// 当前登陆用户ID
		long userId = -1;
		if( request.getSession().getAttribute("UserId") != null ){
			userId = Long.parseLong(request.getSession().getAttribute("UserId").toString());
		}else{
			return mapping.findForward("NullLogin");
		}
		// 总记录条数
		int count=0;
		// 总页数
		int pageCount = 0;
		// 查询页码
		int pageNo = 1;
		// 部门ID
		int dept=0;
		try{
			auId = Long.parseLong(request.getParameter("auId"));
		}
		catch(Exception e){
			auId=-1;
		}
		try{
			pageNo = Integer.parseInt(request.getParameter("page"));
			dept=Integer.parseInt(request.getParameter("dept"));
		}
		catch(Exception e){
			pageNo = 1;
		}
		
		UserInfoBean user = new UserInfoBean();
		// 得到当前用户信息
		SysUser userFrom = user.getUserInfo(userId);
		// 得到被授权用户ID
		SysUser userTo = user.getUserInfo(auId);
		ScheduleConfig sc = new ScheduleConfig();
		
		sc.setSysUserBySuIdFrom(userFrom);
		sc.setSysUserBySuIdTo(userTo);
		
		ConfigWork work = new ConfigWorkBean();
		List<SysUser> list = null;
		// 授权ID为变量auId的值的用户代办工作
		work.authorize(sc);
		Department deptm = new DepartmentBean();
		// 得到所有的部门信息
		List<SysDept> deptList = deptm.getAllDept();
		request.setAttribute("deptList",deptList);
		
		count = work.getAllOtherUserInfoCount(userId, dept);
		pageCount = work.getPageCount(count, ConfigWorkBean.PAGE_SIZE);
		
		// 设置页面的范围
		pageNo = pageNo < 1? 1:pageNo;
		pageNo = pageNo > pageCount? pageCount: pageNo;
		
		list = work.getAllOtherUserInfo(userId, dept, pageNo);
		request.setAttribute("allOtherUserInfoList", list);
					
		// 生成前面的页面显示
		PageBean pagebean = new PageBean();
		pagebean.addParam("dept=" + dept );
		pagebean.setLink("configWork.do");
		pagebean.setTotal(pageCount);
		pagebean.setThispage(pageNo);
		
		request.setAttribute("pageString", pagebean.getPageString());
		request.setAttribute("searchDept",dept);
		request.setAttribute("pageNo",pageNo );
		return mapping.findForward("authorize.succeed");
	}
}