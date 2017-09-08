package cn.bf.dao;

import java.util.List;
import java.util.Map;

import cn.bf.po.Student;
import cn.bf.po.Vacation;

public interface VacationMapper {
	int deleteByPrimaryKey(String uuid);

	int insert(Vacation record);

	int insertSelective(Vacation record);

	Vacation selectByPrimaryKey(String uuid);

	int updateByPrimaryKeySelective(Vacation record);

	int updateByPrimaryKey(Vacation record);

	List<Vacation> selectVacationByCriteria(Map<String, Object> param);

	void insertVacation(Vacation vacation);

	int selectCountByCriteria(Map<String, Object> param);

	Vacation selectVacationByUuid(Vacation vacation);

	void updateVacationByUuid(Vacation vacation);

	void deleteVacationByUuid(Vacation vacation);
}