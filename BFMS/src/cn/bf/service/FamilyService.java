package cn.bf.service;

import java.util.Map;

import cn.bf.po.ActiveUser;
import cn.bf.po.Family;

public interface FamilyService {
	public Map<String, Object> getFamilyList(Family family);

	public Map<String, Object> updateFamily(Family[] familyList, ActiveUser activeUser);

	public Map<String, Object> deleteFamilyByUuid(Family family);
}
