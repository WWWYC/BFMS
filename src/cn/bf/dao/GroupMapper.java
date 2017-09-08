package cn.bf.dao;

import java.util.List;
import java.util.Map;

import cn.bf.po.Group;

public interface GroupMapper {
	Integer deleteByPrimaryKey(String groupid);

	Integer insert(Group record);

	Integer insertSelective(Group record);

	Group selectByPrimaryKey(String groupid);

	Integer updateByPrimaryKeySelective(Group record);

	Integer updateByPrimaryKey(Group record);

	Integer selectCount();

	Integer insertGroup(Group group);

	List<Group> selectGroupList();

	Integer selectMaxGroupNum();

	Group selectGroupByGroupId(String groupId);

	List<Group> findGroupPageList(Map<String, Object> param);

	void updateGroupByGroupId(Group group);

	void deleteGroupByGroupId(Group group);
}