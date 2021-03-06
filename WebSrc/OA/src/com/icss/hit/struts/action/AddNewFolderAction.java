/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.icss.hit.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.icss.hit.bean.FileFolderBean;
import com.icss.hit.bean.interfaces.FileFolderDao;
import com.icss.hit.hibernate.vo.FileFolder;
import com.icss.hit.hibernate.vo.SysUser;

/** 
 * 添加一个新的文件夹
 * Creation date: 08-11-2009
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="AddNewFolder.error" path="/MyFile.do" redirect="true"
 * @struts.action-forward name="AddNewFolder.succeed" path="/MyFile.do" redirect="true"
 */
public class AddNewFolderAction extends Action {
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
		long suId = 1L;
		if( request.getSession().getAttribute("UserId") != null ){
			suId = Long.parseLong(request.getSession().getAttribute("UserId").toString());
		}
		String filename = request.getParameter("filename");
		
		// 在数据库中添加一个文件夹
		FileFolderDao filefolder = new FileFolderBean();
		FileFolder folder = new FileFolder();
		//书写文件名
		folder.setFfName(filename);
		
		SysUser user = new SysUser();
		user.setSuId(suId);
		folder.setSysUser(user);
		// 设置共享，默认为私有
		folder.setFfShare("0");
		// 添加文件夹
		filefolder.addFolder(folder);
		
		// 在文件系统添加文件夹
		filefolder.addFolder(request.getRealPath("/file/" + suId + "/") + "\\" + filename + "\\");
		return mapping.findForward("AddNewFolder.succeed");
	}
}