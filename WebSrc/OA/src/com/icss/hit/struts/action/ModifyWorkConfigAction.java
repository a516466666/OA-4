/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.icss.hit.struts.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.icss.hit.bean.WorkPlanBean;
import com.icss.hit.bean.interfaces.WorkPlanDao;
import com.icss.hit.hibernate.vo.Schedule;
import com.icss.hit.struts.form.ModifyWorkConfigForm;

/** 
 * 修改日程安排	
 * Creation date: 08-05-2009
 * @author xw-pc
 * XDoclet definition:
 * @struts.action path="/modifyWorkConfig" name="modifyWorkConfigForm" input="/work/modifyWorkConfig.jsp" scope="request" validate="true"
 */
public class ModifyWorkConfigAction extends Action {
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
		ModifyWorkConfigForm modifyWorkConfigForm = (ModifyWorkConfigForm) form;
		
		long userId=1;
		
		if( request.getSession().getAttribute("UserId") != null ){
			userId = Long.parseLong(request.getSession().getAttribute("UserId").toString());
		}else{
			return mapping.findForward("NullLogin");
		}
		WorkPlanDao wp = new WorkPlanBean();
		Schedule sch = wp.getScheduleInfo(Long.parseLong(modifyWorkConfigForm.getSch_id()));
			
			//判断时间是否为空
			boolean flag=true;
			if(modifyWorkConfigForm.getSch_begintime()!=null&&modifyWorkConfigForm.getSch_endtime()!=null&&modifyWorkConfigForm.getSch_begintime().length()>0&&modifyWorkConfigForm.getSch_endtime().length()>0&&modifyWorkConfigForm.getSch_content()!=null&&modifyWorkConfigForm.getSch_content().length()>0&&modifyWorkConfigForm.getSch_title()!=null&&modifyWorkConfigForm.getSch_title().length()>0)
			{
				SimpleDateFormat bartDateFormat =new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
				try {
					Date beginTime = bartDateFormat.parse(modifyWorkConfigForm
							.getSch_begintime());
					Date endTime = bartDateFormat.parse(modifyWorkConfigForm
							.getSch_endtime());
					//判断时间是否冲突
					
						List<Schedule> slist = wp.getSchedule(userId);
						try {
							for (int i = 0; i < slist.size(); i++) 
							{
								
								Schedule schedule = slist.get(i);
								// 如果是自身，则跳过
								if( schedule.getSchId() == Long.parseLong(modifyWorkConfigForm.getSch_id())){
									continue;
								}
								//申请的开始时间要晚于已经安排的日程结束的时间
								long m =beginTime.getTime()-schedule.getSchEndtime().getTime();
								//申请的结束时间要早于已经安排的日程开始的时间
								long n = endTime.getTime()-schedule.getSchBegintime().getTime();
								if ( m >= 0 || n <= 0 )
								{
									continue;
								} 
								else
								{
									flag=false;
									break;
								}
							}
						if (flag)
						{
							sch.setSchBegintime(beginTime);
							sch.setSchEndtime(endTime);
							sch.setSchContent(modifyWorkConfigForm.getSch_content());
							sch.setSchTitle(modifyWorkConfigForm.getSch_title());
							if (wp.saveSchedule(sch))
							{
								return mapping.findForward("ModifyWorkConfig.succeed");
							}
							else 
							{
								return mapping.findForward("ModifyWorkConfig.faild");
					
							}
							
						}
						else
						{
							//输出时间冲突的提示信息
							ActionErrors errors = new ActionErrors();
							errors.add("sch_begintime", new ActionMessage("WorkAdd.Timeconflict"));
			 				saveErrors(request, errors);
							return mapping.findForward("ModifyWorkConfig.faild");
						}
					} 
					catch (RuntimeException e)
					{
						e.printStackTrace();
					}

				}
				catch (ParseException e) {
					e.printStackTrace();
					
				}
				
			}
			
		return mapping.findForward("ModifyWorkConfig.faild");
		
		
	}
}