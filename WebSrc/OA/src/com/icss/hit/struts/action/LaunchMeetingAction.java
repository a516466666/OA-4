/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.icss.hit.struts.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.icss.hit.bean.MeetingLaunchBean;
import com.icss.hit.bean.MessageSaveBean;
import com.icss.hit.bean.RoomBean;
import com.icss.hit.bean.interfaces.MessageDao;
import com.icss.hit.bean.interfaces.meetingLaunchDao;
import com.icss.hit.hibernate.vo.Meeting;
import com.icss.hit.hibernate.vo.MeetingAttend;
import com.icss.hit.hibernate.vo.Message;
import com.icss.hit.hibernate.vo.ReceiverInfo;
import com.icss.hit.hibernate.vo.Room;
import com.icss.hit.hibernate.vo.SysUser;
import com.icss.hit.struts.form.LaunchMeetingForm;

/** 
 * ���ڷ�����鲢������߷���վ����
 * Creation date: 08-09-2009
 * @author ��ӱ��
 * XDoclet definition:
 * @struts.action path="/launchMeeting" name="launchMeetingForm" input="/meetingLaunch.do" scope="request" validate="true"
 */
public class LaunchMeetingAction extends Action {
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
		LaunchMeetingForm launchMeetingForm = (LaunchMeetingForm) form;// TODO Auto-generated method stub
		//���������ԱID�ļ���
		String idArray = launchMeetingForm.getIdArray();
		//�������
		String meetingTitle = launchMeetingForm.getMeetingTitle();
		//���鿪ʼʱ��
		String beginTime = launchMeetingForm.getBeginTime();
		//�������ʱ��
		String endTime = launchMeetingForm.getEndTime();
		//�������Ҫ����
		String content = launchMeetingForm.getMeetingContent();
		//�����ҵ�ѡ��,������ID
		long meetingRoom = Long.parseLong(launchMeetingForm.getMeetingRoom());
		//�û�����Session���鿴���˵���Ϣ
		long uid = 1;
		if( request.getSession().getAttribute("UserId") != null ){
			uid = Long.parseLong(request.getSession().getAttribute("UserId").toString());
		}else{
			return mapping.findForward("NullLogin");
		}
		//�������˵�ID,���ڷ������������Ա��Ϣ�����ڻ���MeetingAttendʹ�õ�
		idArray+=(","+uid);
		//�����齫���������ԱID�ļ��Ϸֿ�
		String[] Users;           
		Users = idArray.split(",");                        //�ֳ������û�ID
		//���ڵ�ת��
		Date begin = null;
		Date end = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		try{
			//��ʼʱ��
			if( beginTime != null ){
				begin = format.parse(beginTime);
			}
		}catch( Exception e ){
			begin = null;
		}
		// ����ʱ��
		try{
			if( endTime != null ){
				end = format.parse(endTime);
			}
		}catch( Exception e ){
			end = null;
		}
		//ǰ���еİ�����������������������������ע���� ������������
		Room room = new Room();
		room.setRId(meetingRoom);
		
		
		SysUser user = new SysUser();
		user.setSuId(uid);
		//meeting ID
		long mtID=-1;
		//����������Ƿ�ɹ�
		long resultAttend = -1;
		//�������ڿ�������ݺ�������Ϣ�ı���
		//���ÿ�����Ϣ�Ļ�����Ϣ
		Meeting meeting = new Meeting();
		meeting.setMtTitle(meetingTitle);
		meeting.setMtContent(content);
		meeting.setMtBegintime(begin);
		meeting.setMtEndtime(end);
		meeting.setRoom(room);
		meeting.setSysUser(user);
		meetingLaunchDao meetingSave = new MeetingLaunchBean();
		
		mtID  = meetingSave.saveMeetingMessage(meeting); //���������Ϣ�õģ�ע���˰���������������
		if(mtID == -1)return mapping.findForward("launchMeeting.fail");
		
		//����������μӻ������
		for(int i = 0;i < Users.length; i++)
		{
			MeetingAttend meetingAttend = new MeetingAttend();
			//��������μӻ�������������������Ϣ��ID��
			Meeting myMeeting = new Meeting();
			myMeeting.setMtId(mtID);
			meetingAttend.setMeeting(myMeeting);
			//���ò����˵�ID
			SysUser sysUser = new SysUser();
			Long id;
			try
			{
				id = Long.parseLong(Users[i]);
			}
			catch(Exception ex)
			{
				ex.getStackTrace();
				return mapping.findForward("launchMeeting.fail");
			}
			sysUser.setSuId(id);
			meetingAttend.setSysUser(sysUser);
			resultAttend = meetingSave.saveMeetingUsers(meetingAttend);
			if(resultAttend == -1)return mapping.findForward("launchMeeting.fail");
		}
		
		
		//*********************************************************************
		//���û�����վ����
		long mesID = -2;
		long ReciverID = -2;
		Long result;
		String riBox = "0";
		//���õ�ǰ�û���ID����װ��ΪSysUser���趨Message�ķ����˵�ID
		//��ǰʱ��
		Date date = new Date();
		//��������Ϣ������
		String mesContent = "��μӻ���<br/><br/>������⣺" + meetingTitle +
							"<br/>����ʱ�䣺" + beginTime + "--" + endTime +
							"<br/>����ص㣺" + new RoomBean().getRoom(meetingRoom).getRName() +
							"<br/>������Ҫ���ݣ�" + content;
		//�������ڿ������Ϣ�ķ���
		//������Ϣ�Ļ�����Ϣ
		Message myMessage = new Message();
		myMessage.setMsTitle(meetingTitle);
		myMessage.setMsContent(mesContent);
		myMessage.setMsSendtime(date);
		myMessage.setSysUser(user);
		myMessage.setMsDelete("0");
		MessageDao messageSave = new MessageSaveBean();
		
		//����ط�����ֱ�ӷ���վ���ţ����������ô�ݸ���
		//��ʾ�½��������䣬1Ϊ�����䣬2Ϊ�ݸ���,3Ϊ�ռ���
		//�����ڷ������У����û��뷢���ʼ��ǣ����뷢���䣬ע������ʼ��ķ����˵�����д���Ƿ����䣬���н����˵�����д�����ռ���
		riBox = "3";                                   //�����˵�����д�����ռ���    
		myMessage.setMsBox("1");                       //�����˵�����д���Ƿ�����
			
		
		mesID  = messageSave.saveReceiveMessage(myMessage); //������Ϣ�õģ�ע���˰���������������
		if(mesID == -1)return mapping.findForward("launchMeeting.fail");
		
		//������������Ϣ���ռ��ˣ����ռ��˵���������
		for(int i = 0;i < Users.length; i++)
		{
			ReceiverInfo receiver = new ReceiverInfo();
			//�����ռ�����Ϣ�������������Ϣ��ID��
			Message message = new Message();
			message.setMsId(mesID);
			receiver.setMessage(message);
			//���ý����˵�����
			receiver.setRiBox(riBox);
			//����δ��
			receiver.setRiRead("0");
			//����δɾ��
			receiver.setRiDelete("0");
			//�����û���ID
			SysUser sysUser = new SysUser();
			Long id;
			try
			{
				id = Long.parseLong(Users[i]);
			}
			catch(Exception ex)
			{
				ex.getStackTrace();
				return mapping.findForward("launchMeeting.fail");
			}
			sysUser.setSuId(id);
			receiver.setSysUser(sysUser);
			result = messageSave.saveReceiveUsers(receiver);
			if(result == -1)return mapping.findForward("launchMeeting.fail");
		}
		return mapping.findForward("launchMeeting.successd");
		
	}
}