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
import org.apache.struts.action.ActionRedirect;

import com.icss.hit.bean.DepartmentBean;
import com.icss.hit.bean.SysPositionBean;
import com.icss.hit.bean.UserInfoBean;
import com.icss.hit.bean.interfaces.Department;
import com.icss.hit.bean.interfaces.SysPositionDao;
import com.icss.hit.bean.interfaces.UserInfo;
import com.icss.hit.hibernate.vo.SysDept;
import com.icss.hit.hibernate.vo.SysPosition;
import com.icss.hit.hibernate.vo.SysUser;
import com.icss.hit.struts.form.ChangeInfoForm;

/** 
 * ����Ա�޸�Ա����Ϣ
 * Creation date: 08-12-2009
 * @author ����
 * XDoclet definition:
 * @struts.action path="/changeInfo" name="changeInfoForm" input="/form/changeInfo.jsp" scope="request" validate="true"
 */
public class ChangeInfoAction extends Action {
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
		ChangeInfoForm changeInfoForm = (ChangeInfoForm) form;// TODO Auto-generated method stub
		// ��ǰ��¼�û�ID
		long userId = 1;
		if( request.getSession().getAttribute("UserId") != null ){
			userId = Long.parseLong(request.getSession().getAttribute("UserId").toString());
		}else{
			return mapping.findForward("NullLogin");
		}
		// ��ס������Ϣ�Լ�ҳ��
		String deptNo= request.getParameter("dept");
		String pageNo = request.getParameter("page");
		// �õ�Ա��ID
		String suId = request.getParameter("su_id");
		long workerId = -1;
		try{
			if( suId != null ){
				workerId = Long.parseLong(suId);
			}
		}
		catch(Exception e){
			workerId =-1;
		}
		if(workerId!=-1){
			// �õ��޸���Ϣ
			String suUid = changeInfoForm.getSuUid();
			String suUsername = changeInfoForm.getSuUsername();
			String suSex = changeInfoForm.getSuSex();
			String suDept = changeInfoForm.getSuDept();
			String suPos = changeInfoForm.getSuPos();
			
			// ����ID�õ�������Ϣ
			Department dept = new DepartmentBean();
			SysDept dp = dept.getDept(Long.parseLong(suDept));
			// ����ID�õ�ְλ��Ϣ
			SysPositionDao pos = new SysPositionBean();
			SysPosition sp =pos.getPosition(Long.parseLong(suPos));
			
			UserInfo info = new UserInfoBean();
			SysUser user = info.getUserInfo(workerId);
			user.setSuUid(suUid);
			user.setSuUsername(suUsername);
			user.setSuSex(suSex);
			user.setSysDept(dp);
			user.setSysPosition(sp);
			// �����û���Ϣ
			info.updateUserInfo(user);
		}
		ActionRedirect redirect = new ActionRedirect(mapping.findForward("changeInfo.succeed"));
		redirect.addParameter("dept",deptNo);
		redirect.addParameter("page", pageNo);
		return redirect;
	}
}