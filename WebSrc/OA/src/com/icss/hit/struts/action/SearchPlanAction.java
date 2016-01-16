/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.icss.hit.struts.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.icss.hit.bean.WorkPlanBean;
import com.icss.hit.bean.interfaces.WorkPlanDao;
import com.icss.hit.component.PageBean;
import com.icss.hit.hibernate.vo.Schedule;

/** 
 * �ճ̹���,�߼�����
 * Creation date: 08-04-2009
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="SearchPlan.succeed" path="/work/allSearchWork.jsp"
 */
public class SearchPlanAction extends Action {
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
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		// �û�ID
		long uid = 1L;
		if( request.getSession().getAttribute("UserId") != null ){
			uid = Long.parseLong(request.getSession().getAttribute("UserId").toString());
		}else{
			return mapping.findForward("NullLogin");
		}
		
		// �ӵ�ַ���õ�����
		String nameUrl = request.getParameter("name");
		String beginUrl = request.getParameter("begin");
		String endUrl = request.getParameter("end");
		String completeUrl = request.getParameter("complete");
		
		//System.out.println("��ַ����ȡ���Ŀ�ʼʱ�䣺"+endUrl);
		String name = null;
		String begin = null;
		String end = null;
		
		// ҳ����
		int page = 1;
		
		// ҳ����
		try{
			if( request.getParameter("page") != null ){
				page = Integer.parseInt(request.getParameter("page").toString());
			}
		}catch( Exception e ){
			page = 1;
		}
		// ����
		try{
			if( nameUrl != null)
				name = URLDecoder.decode(nameUrl,"UTF-8");
			
		}catch(Exception e){
			name = null;
		}
		
		// ��ʼʱ��
		try {
			if( beginUrl != null ){
				begin = URLDecoder.decode(beginUrl,"UTF-8");
			}
		} catch (UnsupportedEncodingException e2) {
			begin = null;
		}
		
		// ����ʱ��
		try {
			if( endUrl != null ){
				end = URLDecoder.decode(endUrl,"UTF-8");
				//System.out.println("ת��֮��Ŀ�ʼʱ�䣺"+begin);
			}
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
			end = null;
		}
		
		// ����
		Date beginTime = null;
		Date endTime = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		try{
			//��ʼʱ��
			if( begin != null ){
				beginTime = format.parse(begin);
				//System.out.println("ת����BeginDate֮��Ŀ�ʼʱ��"+beginTime);
			}
		}catch( Exception e ){
			beginTime = null;
		}
		// ����ʱ��
		try{
			if( end != null ){
				endTime = format.parse(end);
				//System.out.println("ת����EndDate֮��Ŀ�ʼʱ��"+endTime);
			}
		}catch( Exception e ){
			endTime = null;
		}
		
		WorkPlanDao dao = new WorkPlanBean();
		
		// ���з�����������������
		int count = dao.allSearchCount(name, beginTime, endTime, completeUrl, uid);
		// �õ����ݵ�ҳ��
		int pageCount = dao.getPageCount(count, WorkPlanBean.PAGE_SIZE);
		
		// ��������������
		List<Schedule> list = dao.allSearch(name, beginTime, endTime, completeUrl, uid, page);
		// ���������ǰ̨
		request.setAttribute("list", list);
		
		// ���������������ص�ǰ̨
		request.setAttribute("name", name);
		request.setAttribute("begin", begin);
		request.setAttribute("end", end);
		request.setAttribute("complete", completeUrl);
		
		// ��ҳ��Ϣ����
		PageBean bean = new PageBean();
		bean.setTotal(pageCount);
		bean.setThispage(page);
		bean.setLink("SearchPlan.do");
		if( completeUrl == null ){
			bean.addParam("complete=2");
		}else{
			bean.addParam("complete=" + completeUrl);
		}
		
		// ��ַ���л��Զ���25��������Ҫ�ٴ�ת��
		if( name != null )
			bean.addParam("name=" + URLEncoder.encode(nameUrl));
		else
			bean.addParam("name=");
		
		// Ϊ�յ�ʱ���Զ��ڵ�ַ���ϼ�Null
		if( beginUrl != null ){
			bean.addParam("begin=" + URLEncoder.encode(beginUrl));
		}else{
			bean.addParam("begin=");
		}
		
		if( endUrl != null ){
			bean.addParam("end=" + URLEncoder.encode(endUrl));
		}else{
			bean.addParam("end=");
		}
		request.setAttribute("pageString", bean.getPageString());
		return mapping.findForward("SearchPlan.succeed");
	}
}