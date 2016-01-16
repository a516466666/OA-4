/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.icss.hit.struts.action;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.icss.hit.bean.InBoxBean;
import com.icss.hit.bean.UserInfoBean;
import com.icss.hit.bean.interfaces.InBox;
import com.icss.hit.bean.interfaces.UserInfo;
import com.icss.hit.hibernate.vo.ReceiverInfo;
import com.icss.hit.hibernate.vo.SysUser;

/** 
 * 新建信息&回复信息
 * Creation date: 08-06-2009
 * @author 朱金彪
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="NewMessage.succeed" path="/message/newMessage.jsp"
 */
public class NewMessageAction extends Action {
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
		// 当前登录用户ID(发件人ID)
		long senderId = -1;
		if( request.getSession().getAttribute("UserId") != null ){
			senderId = Long.parseLong(request.getSession().getAttribute("UserId").toString());
		}else{
			return mapping.findForward("NullLogin");
		}
		
		// ReceiverInfo表ID
		long riId =-1;
		// 收件人ID
		long receiverId =-1;
		
		SysUser sysUser = new SysUser();
		// 存放ID
		String receiver=null;
		// 页码
		int pageNo=1;
		// 搜索方式
		String searchType=null;
		// 搜索内容
		String content=null;
		// 搜索内容URL编码
		String contentURL=null;
		searchType= request.getParameter("searchType");
		content = request.getParameter("content");
		try{
			receiverId = Long.parseLong(request.getParameter("receiverId"));
		}
		catch(Exception e){
			receiverId=-1;
		}
		try{
			riId = Long.parseLong(request.getParameter("riId"));
		}
		catch(Exception e){
			riId=-1;
		}
		try{
			content = URLDecoder.decode(content, "UTF-8");
		}
		catch(Exception e){
			content=null;
		}
		try{
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		catch(Exception e){
			pageNo=1;
		}
		if(receiverId!=-1){
			UserInfo user = new UserInfoBean(); 
			sysUser = user.getUserInfo(receiverId);
			request.setAttribute("sysUser", sysUser);
			InBox inbox = new InBoxBean();
			// 得到收件箱信息
			ReceiverInfo rInfo = inbox.getReceiverInfo(riId, senderId);
			request.setAttribute("messageInfo",rInfo);
		}
		if( request.getParameter("content") == null ){
			request.setAttribute("forwardString","pageNo="+pageNo+"&searchType="+searchType+"&content=");
		}
		else{
			request.setAttribute("forwardString","pageNo="+pageNo+"&searchType="+searchType+"&content="+URLEncoder.encode(request.getParameter("content")));
		}
		try{
			contentURL= URLEncoder.encode(request.getParameter("content"));
		}
		catch(Exception e){
			contentURL="";
		}
		if(searchType == null||contentURL == null){
			searchType ="";
			contentURL ="";
		}
		request.setAttribute("forwardString","pageNo="+pageNo+"&searchType="+searchType+"&content="+contentURL);

		return mapping.findForward("NewMessage.succeed");
	}
}