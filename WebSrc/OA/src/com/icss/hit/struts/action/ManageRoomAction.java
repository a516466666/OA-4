/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.icss.hit.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.icss.hit.bean.CheckPower;
import com.icss.hit.bean.ManageRoomBean;
import com.icss.hit.bean.MessageSaveBean;
import com.icss.hit.bean.interfaces.MessageDao;
import com.icss.hit.bean.interfaces.manageRoomDao;
import com.icss.hit.component.PageBean;
import com.icss.hit.hibernate.vo.MessageReceivers;
import com.icss.hit.hibernate.vo.Room;

/** 
 * 用于将会议室显示出来以便管理
 * Creation date: 08-10-2009
 * @author 赵颖申
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="manageRoom.successd" path="/adminmeeting/managerroom.jsp"
 */
public class ManageRoomAction extends Action {
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
		if( !check.check(CheckPower.ADMIN_ROOM)){
			return mapping.findForward("NullLogin");
		}
		
		manageRoomDao manageRoom = new ManageRoomBean();
		
		
		// 所有房间的数量
		int count = 0;
		// 分页的数量
		int pageCount = 0;
		
		int pageNo = 1;
		try{
			pageNo = Integer.parseInt(request.getParameter("page"));
		}catch(Exception e){
			// 如果数据异常
			pageNo = 1;
		}
		// 得到总数和分页
		count = manageRoom.getRoomCount();
		pageCount = manageRoom.getPageCount(count,ManageRoomBean.PAGE_SIZE);
		
		// 设置页面的范围
		pageNo = pageNo < 1? 1:pageNo;
		pageNo = pageNo > count? count: pageNo;
		
		List<Room> rooms = new ArrayList<Room>();
		//List<String[]> receiverList = null;
		rooms = manageRoom.getAllRooms(pageNo);
		
		request.setAttribute("roomList", rooms);
		
		// 生成前面的页面显示
		PageBean pagebean = new PageBean();
		pagebean.setLink("manageRoom.do");
		pagebean.setTotal(pageCount);
		pagebean.setThispage(pageNo);
		
		request.setAttribute("pageString", pagebean.getPageString());
		return mapping.findForward("manageRoom.successd");
	}
}