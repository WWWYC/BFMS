package cn.bf.service;

import java.util.Date;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import cn.bf.po.ActiveUser;
import cn.bf.po.Vacation;

public interface VacationService {
	
	public Map<String, Object> getVacationListByCriteria(String name, Date startDate, Date endDate, Integer page, Integer rows);

	public Map<String, Object> insertVacation(Vacation vacation, ActiveUser activeUser);

	public ModelAndView showVacation(Vacation vacation, String viewName);

	public Map<String, Object> updateVacationByUuid(Vacation vacation, ActiveUser activeUser);

	public Map<String, Object> deleteVacationByUuid(Vacation vacation);

}
