/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.icss.hit.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/** 
 * MyEclipse Struts
 * Creation date: 08-03-2009
 * 
 * XDoclet definition:
 * @struts.form name="addWorkConfigForm"
 */
public class AddWorkConfigForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** sch_id property */
	private String sch_id;

	/** sch_begintime property */
	private String sch_begintime;

	/** su_id_to property */
	private String su_id_to;

	/** sch_content property */
	private String sch_content;

	/** sch_endtime property */
	private String sch_endtime;

	/** sch_title property */
	private String sch_title;

	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
	
		ActionErrors errors = new ActionErrors();
		// 将String类型转换成Date类型
		
		
		//判断开始时间是否晚于结束时间
			if(sch_begintime!=null&&sch_endtime!=null&&sch_begintime.length()>0&&sch_endtime.length()>0)
			{
				try
				{
					SimpleDateFormat   bartDateFormat   =new   SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
					Date beginTime = bartDateFormat.parse(sch_begintime);
					Date endTime = bartDateFormat.parse(sch_endtime);
				
					long i = beginTime.getTime()-endTime.getTime();
					if(i>=0)
					{
						errors.add("sch_begintime", new ActionMessage("WorkAdd.TimeWrong"));
					}
				}
				catch (ParseException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//判断日程安排名称是否为空
		if(sch_title==null||sch_title.length()==0)
		{
			errors.add("sch_title",new ActionMessage("WorkAdd.TitleIsNull"));
			
		}
			//判断日程安排名称是否过长
		if(sch_title!=null&&sch_title.length()>200)
		{
	
				errors.add("sch_title",new ActionMessage("WorkAdd.TitleIsTooLong"));
			
		}
			//判断日程安排的开始时间是否为空
		if(sch_begintime==null||sch_begintime.length()==0)
		{
			
				errors.add("sch_begintime",new ActionMessage("WorkAdd.BeginTimeIsNull"));
			
		}
		//判断日程安排的结束时间是否为空
		if(sch_endtime==null||sch_endtime.length()==0)
		{
			errors.add("sch_endtime",new ActionMessage("WorkAdd.EndTimeIsNull"));
		}
		//判断日程安排的安排内容是否为空
		if(sch_content==null||sch_content.length()==0)
		{
			errors.add("sch_content",new ActionMessage("WorkAdd.ContentIsNull"));
		}
		request.setAttribute("form", this);
		return errors;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	/** 
	 * Returns the sch_id.
	 * @return String
	 */
	public String getSch_id() {
		return sch_id;
	}

	/** 
	 * Set the sch_id.
	 * @param sch_id The sch_id to set
	 */
	public void setSch_id(String sch_id) {
		this.sch_id = sch_id;
	}

	/** 
	 * Returns the sch_begintime.
	 * @return String
	 */
	public String getSch_begintime() {
		return sch_begintime;
	}

	/** 
	 * Set the sch_begintime.
	 * @param sch_begintime The sch_begintime to set
	 */
	public void setSch_begintime(String sch_begintime) {
		this.sch_begintime = sch_begintime;
	}

	/** 
	 * Returns the su_id_to.
	 * @return String
	 */
	public String getSu_id_to() {
		return su_id_to;
	}

	/** 
	 * Set the su_id_to.
	 * @param su_id_to The su_id_to to set
	 */
	public void setSu_id_to(String su_id_to) {
		this.su_id_to = su_id_to;
	}

	/** 
	 * Returns the sch_content.
	 * @return String
	 */
	public String getSch_content() {
		return sch_content;
	}

	/** 
	 * Set the sch_content.
	 * @param sch_content The sch_content to set
	 */
	public void setSch_content(String sch_content) {
		this.sch_content = sch_content;
	}

	/** 
	 * Returns the sch_endtime.
	 * @return String
	 */
	public String getSch_endtime() {
		return sch_endtime;
	}

	/** 
	 * Set the sch_endtime.
	 * @param sch_endtime The sch_endtime to set
	 */
	public void setSch_endtime(String sch_endtime) {
		this.sch_endtime = sch_endtime;
	}

	/** 
	 * Returns the sch_title.
	 * @return String
	 */
	public String getSch_title() {
		return sch_title;
	}

	/** 
	 * Set the sch_title.
	 * @param sch_title The sch_title to set
	 */
	public void setSch_title(String sch_title) {
		this.sch_title = sch_title;
	}
}