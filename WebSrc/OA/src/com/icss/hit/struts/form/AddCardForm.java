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

/** 
 * MyEclipse Struts
 * Creation date: 08-06-2009
 * 
 * XDoclet definition:
 * @struts.form name="addCardForm"
 */
public class AddCardForm extends ActionForm {
	
	/*
	 * Generated fields
	 */

	/** cd_cellphone property */
	private String cd_cellphone;

	/** cd_company property */
	private String cd_company;

	/** cd_share property */
	private String cd_share;

	/** cd_fax property */
	private String cd_fax;

	/** cd_email property */
	private String cd_email;

	/** cd_info property */
	private String cd_info;

	/** cd_name property */
	private String cd_name;

	/** ct_name property */
	private String ct_name;

	/** cd_sex property */
	private String cd_sex;

	/** cd_tel property */
	private String cd_tel;

	/** cd_position property */
	private String cd_position;
	/** cd_id*/
	private String cd_id;
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
		//名片电子邮件过长
		if(cd_email!=null&&cd_email.length()>150)
		{
		
				errors.add("cd_email", new ActionMessage("CdAdd.EmailTooLong"));	
		
		}
		
		//名片办公电话过长
		if(cd_tel!=null&&cd_tel.length()>20)
		{
		
				errors.add("cd_tel", new ActionMessage("CdAdd.TelTooLong"));
				

		}
		
		//名片的姓名为空
		if(cd_name==null||cd_name.length()==0)
		{
			errors.add("cd_name", new ActionMessage("CdAdd.NameIsNull"));
		}
		//名片的手机号码位数过长
		if(cd_cellphone!=null&&cd_cellphone.length()>20)
		{
				errors.add("cd_cellphone", new ActionMessage("CdAdd.CellPhoneTooLong"));
				
		}
		
		//名片的电子邮件不符合电子邮件格式
		if(cd_email!=null&&cd_email.length()>0)
		{
			if(!cd_email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"))
			{
				errors.add("cd_email", new ActionMessage("CdAdd.Emailillegal"));
			}
		}
		//名片的办公电话不符合电话的格式
		if(cd_tel!=null&&cd_tel.length()>0)
		{
			if(!cd_tel.matches("1\\d{10}|\\d{3}-\\d{8}|\\d{4}-\\d{7,8}"))
			{
				errors.add("cd_tel", new ActionMessage("CdAdd.Telillegal"));
			}
		}
		//名片的手机号码不符合手机号码格式
		if(cd_cellphone!=null&&cd_cellphone.length()>0)
		{
			if(!cd_cellphone.matches("1\\d{10}|\\d{3}-\\d{8}|\\d{4}-\\d{7}"))
			{
				errors.add("cd_cellphone", new ActionMessage("CdAdd.Cellphoneillegal"));
			}
		}
		//名片的传真不符合传真格式
		if(cd_fax!=null&&cd_fax.length()>0)
		{
			if(!cd_fax.matches("1\\d{10}|\\d{3}-\\d{8}|\\d{4}-\\d{7,8}"))
			{
				errors.add("cd_fax", new ActionMessage("CdAdd.faxillegal"));
			}
			//为选择名片夹类型
		}
		if(ct_name==null||ct_name.length()==0)
		{
			errors.add("ct_name", new ActionMessage("CdAdd.CardTypeIsNull"));
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
	 * Returns the cd_cellphone.
	 * @return String
	 */
	public String getCd_cellphone() {
		return cd_cellphone;
	}

	/** 
	 * Set the cd_cellphone.
	 * @param cd_cellphone The cd_cellphone to set
	 */
	public void setCd_cellphone(String cd_cellphone) {
		this.cd_cellphone = cd_cellphone;
	}

	/** 
	 * Returns the cd_company.
	 * @return String
	 */
	public String getCd_company() {
		return cd_company;
	}

	/** 
	 * Set the cd_company.
	 * @param cd_company The cd_company to set
	 */
	public void setCd_company(String cd_company) {
		this.cd_company = cd_company;
	}

	/** 
	 * Returns the cd_share.
	 * @return String
	 */
	public String getCd_share() {
		return cd_share;
	}

	/** 
	 * Set the cd_share.
	 * @param cd_share The cd_share to set
	 */
	public void setCd_share(String cd_share) {
		this.cd_share = cd_share;
	}

	/** 
	 * Returns the cd_fax.
	 * @return String
	 */
	public String getCd_fax() {
		return cd_fax;
	}

	/** 
	 * Set the cd_fax.
	 * @param cd_fax The cd_fax to set
	 */
	public void setCd_fax(String cd_fax) {
		this.cd_fax = cd_fax;
	}

	/** 
	 * Returns the cd_email.
	 * @return String
	 */
	public String getCd_email() {
		return cd_email;
	}

	/** 
	 * Set the cd_email.
	 * @param cd_email The cd_email to set
	 */
	public void setCd_email(String cd_email) {
		this.cd_email = cd_email;
	}

	/** 
	 * Returns the cd_info.
	 * @return String
	 */
	public String getCd_info() {
		return cd_info;
	}

	/** 
	 * Set the cd_info.
	 * @param cd_info The cd_info to set
	 */
	public void setCd_info(String cd_info) {
		this.cd_info = cd_info;
	}

	/** 
	 * Returns the cd_name.
	 * @return String
	 */
	public String getCd_name() {
		return cd_name;
	}

	/** 
	 * Set the cd_name.
	 * @param cd_name The cd_name to set
	 */
	public void setCd_name(String cd_name) {
		this.cd_name = cd_name;
	}

	/** 
	 * Returns the ct_name.
	 * @return String
	 */
	public String getCt_name() {
		return ct_name;
	}

	/** 
	 * Set the ct_name.
	 * @param ct_name The ct_name to set
	 */
	public void setCt_name(String ct_name) {
		this.ct_name = ct_name;
	}

	/** 
	 * Returns the cd_sex.
	 * @return String
	 */
	public String getCd_sex() {
		return cd_sex;
	}

	/** 
	 * Set the cd_sex.
	 * @param cd_sex The cd_sex to set
	 */
	public void setCd_sex(String cd_sex) {
		this.cd_sex = cd_sex;
	}

	/** 
	 * Returns the cd_tel.
	 * @return String
	 */
	public String getCd_tel() {
		return cd_tel;
	}

	/** 
	 * Set the cd_tel.
	 * @param cd_tel The cd_tel to set
	 */
	public void setCd_tel(String cd_tel) {
		this.cd_tel = cd_tel;
	}

	/** 
	 * Returns the cd_position.
	 * @return String
	 */
	public String getCd_position() {
		return cd_position;
	}

	/** 
	 * Set the cd_position.
	 * @param cd_position The cd_position to set
	 */
	/**
	 * @param cd_position
	 */
	public void setCd_position(String cd_position) {
		this.cd_position = cd_position;
	}
	public String getCd_id() {
		return cd_id;
	}

	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}
}
