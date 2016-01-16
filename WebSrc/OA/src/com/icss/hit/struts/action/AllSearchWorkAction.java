/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.icss.hit.struts.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.icss.hit.struts.form.AllSearchWorkForm;

/** 
 * MyEclipse Struts
 * Creation date: 08-04-2009
 * 
 * XDoclet definition:
 * @struts.action path="/SearchWork" name="allSearchWorkForm" input="/AllSearchWork.do" scope="request" validate="true"
 * @struts.action-forward name="SearchWork.succeed" path="/AllSearchWork.do"
 */
public class AllSearchWorkAction extends Action {
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
		AllSearchWorkForm allSearchWorkForm = (AllSearchWorkForm) form;// TODO Auto-generated method stub
		
		// 得到搜索的条件
		String name = allSearchWorkForm.getName();
		String beginTime = allSearchWorkForm.getBeginTime();
		String endTime = allSearchWorkForm.getEndTime();
		String complete = allSearchWorkForm.getComplete();
		// 中文转码
		try {
			name = URLEncoder.encode(name, "UTF-8");
			beginTime = URLEncoder.encode(beginTime, "UTF-8");
			endTime = URLEncoder.encode(endTime,"UTF-8");
			System.out.println("name:"+name);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return mapping.findForward("SearchWork.succeed");
		}
		
		// 带参数的页面跳转
		ActionRedirect redirect = new ActionRedirect(mapping.findForward("SearchWork.succeed"));
		redirect.addParameter("name",name);
		redirect.addParameter("begin", beginTime);
		redirect.addParameter("end", endTime);
		redirect.addParameter("complete", complete);
		redirect.addParameter("page", 1);
		return redirect;
	}
}