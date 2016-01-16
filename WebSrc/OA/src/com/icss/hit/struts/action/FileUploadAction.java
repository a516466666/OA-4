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

import com.icss.hit.bean.FileFolderBean;
import com.icss.hit.bean.interfaces.FileFolderDao;
import com.icss.hit.hibernate.vo.FileFolder;

/** 
 * MyEclipse Struts
 * Creation date: 08-07-2009
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="FileUpload.succeed" path="/FolderDetail.do"
 * @struts.action-forward name="FileUpload.error" path="/MyFile.do" redirect="true"
 */
public class FileUploadAction extends Action {
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
		// �û�ID
		long uid = 1;
		if( request.getSession().getAttribute("UserId") != null ){
			uid = Long.parseLong(request.getSession().getAttribute("UserId").toString());
		}else{
			return mapping.findForward("NullLogin");
		}
		FileFolderDao folder = new FileFolderBean();
		List<FileFolder> list = folder.getAllFolders(uid);
		
		request.setAttribute("fileFolder", list);
		return mapping.findForward("FileUpload.succeed");
	}
}