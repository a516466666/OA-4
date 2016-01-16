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

import com.icss.hit.bean.MeetingAttendBean;
import com.icss.hit.bean.interfaces.MeetingAttendDao;
import com.icss.hit.component.PageBean;
import com.icss.hit.hibernate.vo.Meeting;

/** 
 * 得到用户参与会议列表
 * Creation date: 08-09-2009
 * @author 朱金彪
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="meetingAttend.succeed" path="/meeting/meetingAttend.jsp"
 * @struts.action-forward name="meetingAttend.failed" path="/meeting/meetingAttend.jsp"
 */
public class MeetingAttendAction extends Action {
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
		// 当前登陆用户ID
		long userId = -1;
		if( request.getSession().getAttribute("UserId") != null ){
			userId = Long.parseLong(request.getSession().getAttribute("UserId").toString());
		}else{
			return mapping.findForward("NullLogin");
		}
		// 查询页码
		int pageNo = 1;
		
		// 所有人员的数量
		int count = 0;
		// 分页的数量
		int pageCount = 0;
		// 得到搜索的数据
		List<Meeting> list = null;
		try{
			pageNo = Integer.parseInt(request.getParameter("page"));
		}
		catch(Exception e){
			pageNo =1;
		}
		
		if( userId != -1){
			MeetingAttendDao meeting = new MeetingAttendBean();
			
			// 得到总数和分页
			count = meeting.getAllAssociateMeetingCount(userId);
			pageCount = meeting.getPageCount(count, MeetingAttendBean.PAGE_SIZE);
			
			// 设置页面的范围
			pageNo = pageNo < 1? 1:pageNo;
			pageNo = pageNo > pageCount? pageCount: pageNo;
			
			list = meeting.getAllAssociateMeeting(pageNo, userId);
			request.setAttribute("AssociateMeetingList", list);
						
			// 生成前面的页面显示
			PageBean pagebean = new PageBean();
			pagebean.setLink("meetingAttend.do");
			pagebean.setTotal(pageCount);
			pagebean.setThispage(pageNo);
			request.setAttribute("pageString", pagebean.getPageString());
			request.setAttribute("pageNo",pageNo);
		}
		return mapping.findForward("meetingAttend.succeed");
	}
}