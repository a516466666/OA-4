/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.icss.hit.struts.action;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.icss.hit.bean.FileBean;
import com.icss.hit.bean.interfaces.FileDao;
import com.icss.hit.component.DrawPieConfig;

/** 
 * 我的文件夹显示页，主要显示占用的系统资源空间
 * Creation date: 08-07-2009
 * @author 万里鹏
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="MyFile.succeed" path="/filepage/myFile.jsp"
 */
public class MyFileAction extends Action {
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
		// 用户ID
		long uid = 1;
		if( request.getSession().getAttribute("UserId") != null ){
			uid = Long.parseLong(request.getSession().getAttribute("UserId").toString());
		}else{
			return mapping.findForward("NullLogin");
		}
		FileDao fileBean = new FileBean();
		
		// 得到用户所在的文件夹
		String filePath = request.getRealPath("/file/" + uid + "/") + "\\";
		// 饼状图的文件名
		String pieName = uid + "_filepie.jpg";
		
		// 得到用户空间已经使用的大小
		long length = fileBean.getFileLength(filePath);
		// 剩余空间大小(KB)
		double space = 50 - length/(1024.0 * 1024.0);
		DecimalFormat myformat = new DecimalFormat("#0.00");
		double newspace = Double.parseDouble(myformat.format(space));
		DrawPieConfig cfg = new DrawPieConfig();
		// 设置空余空间大小
		cfg.setSpace(newspace);
		
		// 剩余空间百分比
		double spaceScale = 1.0 - length/(50*1024*1024.0);
		
		// 设置已用空间大小
		cfg.setUsed(50 - newspace);
		
		// 设置生成饼状图的路径
		cfg.setPath(request.getRealPath("/file/pie/") + "\\" + pieName);
		
		// 生成饼状图
		cfg.init();
		
		// 传到前台的文件名
		request.setAttribute("pieName", pieName);
		request.setAttribute("spaceScale", spaceScale);
		return mapping.findForward("MyFile.succeed");
	}
}