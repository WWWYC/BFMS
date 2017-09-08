package cn.bf.dao;

import java.util.List;
import java.util.Map;

import cn.bf.po.Student;

public interface StudentMapper {
	int deleteByPrimaryKey(String stuid);

	int insert(Student record);

	int insertSelective(Student record);

	Student selectByPrimaryKey(String stuid);

	int updateByPrimaryKeySelective(Student record);

	int updateByPrimaryKey(Student record);

	Long selectCountByYear(String year);

	Integer insertStudent(Student student);

	void updatePhotoPathByStuId(Student stuParam);

	String selectMaxStuNumByYear(Map<String, String> param);

	List<Student> selectStudentByCriteria(Map<String, Object> param);

	Integer selectStudentCountByCriteria(Map<String, Object> param);

	Student selectStudentByStuId(String stuId);

	void updateStudentByStuId(Student student);

	String selectStuPhotoByStuId(Student student);

	void deleteStudentByStuId(Student student);

	List<Student> selectAllStudent();

	List<Student> selectStuIdByStuName(Map<String, Object> param);

	List<Student> selectStudentByCompanyId(Map<String, Object> param);
}