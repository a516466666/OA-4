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
 * @struts.form name="newUserMessageForm"
 */
public class NewUserMessageForm extends ActionForm {
	/*
	 * Generated fields
	 */
	private String mesID;
	/** messageContent property */
	private String messagecontent;

	/** sum property */
	private String idArray;

	/** messageTitle property */
	private String messageTitle;
	
	private String type;
	/*
	 * Generated Methods
	 */

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if(messageTitle == null||messageTitle.trim().equals(""))
		{
			errors.add("mesTitle",new ActionMessage("Message.NotNull"));
			if(idArray == null||idArray.trim() == "")
			{
				errors.add("sum",new ActionMessage("Users.NotNull"));
			}
			return errors;
		}
		
		return null;
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
	 * Returns the messageContent.
	 * @return String
	 */
	public String getMessagecontent() {
		return messagecontent;
	}

	/** 
	 * Set the messageContent.
	 * @param messageContent The messageContent to set
	 */
	public void setMessagecontent(String messagecontent) {
		this.messagecontent = messagecontent;
	}

	/** 
	 * Returns the sum.
	 * @return String
	 */
	public String getIdArray() {
		return idArray;
	}

	/** 
	 * Set the sum.
	 * @param sum The sum to set
	 */
	public void setIdArray(String idArray) {
		this.idArray = idArray;
	}

	/** 
	 * Returns the messageTitle.
	 * @return String
	 */
	public String getMessageTitle() {
		return messageTitle;
	}

	/** 
	 * Set the messageTitle.
	 * @param messageTitle The messageTitle to set
	 */
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMesID() {
		return mesID;
	}

	public void setMesID(String mesID) {
		this.mesID = mesID;
	}
}