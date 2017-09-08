package cn.bf.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import cn.bf.exception.MapperException;
import cn.bf.po.ActiveUser;
import cn.bf.po.Group;

public interface GroupService {
	public Map<String, Object> getGroupNum();

	public Map<String, Object> insertGroup(Group group, ActiveUser activeUser) throws MapperException;

	public List<Group> selectGroupList();

	public Group selectGroupByGroupId(String groupId);

	public Map<String, Object> findGroupPageList(Integer page, Integer rows);

	public ModelAndView showGroup(String groupId, String string);

	public Map<String, Object> updateGroupByGroupId(Group group, ActiveUser activeUser);

	public Map<String, Object> deleteGroupByGroupId(Group group);
}
