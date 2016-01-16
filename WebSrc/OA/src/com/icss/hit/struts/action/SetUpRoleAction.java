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

import com.icss.hit.bean.SysRoleBean;
import com.icss.hit.bean.UserInfoBean;
import com.icss.hit.bean.interfaces.SysRoleDao;
import com.icss.hit.hibernate.vo.SysRole;
import com.icss.hit.hibernate.vo.SysUser;

/** 
 * 得到需要修改角色的用户
 * Creation date: 08-11-2009
 * @author 朱金彪
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="setUpRole.succeed" path="/role/setUpRole.jsp"
 */
public class SetUpRoleAction extends Action {
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
		// 当前登录用户ID
		long userId = -1;
		if( request.getSession().getAttribute("UserId") != null ){
			userId = Long.parseLong(request.getSession().getAttribute("UserId").toString());
		}else{
			return mapping.findForward("NullLogin");
		}
		SysRoleDao role = new SysRoleBean();
		// 得到所有的角色信息
		List<SysRole> roleNameList = role.getAllSysRoleName();
		request.setAttribute("roleNameList",roleNameList);
		
		long otherUserId = -1;//存储查询字符串中的用户ID参数
		long dept = 0; // 部门号
		int pageNo = 1; // 页码
		UserInfoBean getter = null;//用户信息获取Bean
		SysUser otherUser = null;//用户信息实体
		String id = request.getParameter("suId");//先用String存 再转换成Long
		try{
			if( id != null){
				otherUserId = Long.parseLong(id);
			}
		}
		catch (Exception e){
			e.printStackTrace();
			return mapping.findForward("setUpRole.failed");
		}
		try{
			dept = Integer.parseInt(request.getParameter("dept"));
			pageNo = Integer.parseInt(request.getParameter("page"));
		}catch(Exception e){
			// 如果数据异常
			dept = 0;
			pageNo = 1;
		}
		
		getter = new UserInfoBean();
		otherUser = getter.getUserInfo(otherUserId);//从数据库中取数据
		if(otherUser != null){
			request.setAttribute("userInfoDetails", otherUser);
		}
		request.setAttribute("dept",dept);
		request.setAttribute("page", pageNo);
		return mapping.findForward("setUpRole.succeed");
	}
}