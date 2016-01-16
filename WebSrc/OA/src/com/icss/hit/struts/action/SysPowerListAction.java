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

import com.icss.hit.bean.CheckPower;
import com.icss.hit.bean.DepartmentBean;
import com.icss.hit.bean.MessageSaveBean;
import com.icss.hit.bean.SysPowerTypeBean;
import com.icss.hit.bean.interfaces.Department;
import com.icss.hit.bean.interfaces.SysPowerTypeDao;
import com.icss.hit.component.PageBean;
import com.icss.hit.hibernate.vo.SysDept;
import com.icss.hit.hibernate.vo.SysPower;

/** 
 * 列出系统的权限列表
 * Creation date: 08-09-2009
 * @author xw-pc
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class SysPowerListAction extends Action {
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
		// 当前用户ID
		long userId = -1;
		if( request.getSession().getAttribute("UserId") != null ){
			userId = Long.parseLong(request.getSession().getAttribute("UserId").toString());
		}else{
			return mapping.findForward("NullLogin");
		}
		// 判断用户权限
		CheckPower check = new CheckPower(userId);
		check.getPower();
		if( !check.check(CheckPower.ADMIN_POWER)){
			return mapping.findForward("NullLogin");
		}
		
		SysPowerTypeDao spt = new SysPowerTypeBean();
		//得到所有的用户权限
		List<SysPower> spList = spt.getAllSysPower();
		
		request.setAttribute("spList", spList);
		return mapping.findForward("SysPowerList.succeed");
	}
}