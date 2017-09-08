package cn.bf.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bf.dao.GroupCompanyMapper;
import cn.bf.service.GroupCompanyService;
import cn.bf.util.UUIDUtil;

@Service
public class GroupCompanyServiceImpl implements GroupCompanyService {
	@Autowired
	private GroupCompanyMapper groupCompanyMapper;
	
	@Override
        public String insertGroupCompany(String groupId, String companyId) { 
		// 组装参数
		Map<String, String> param = new HashMap<String, String>();
		param.put("uuid", UUIDUtil.getUuid());
		param.put("groupId", groupId);
		param.put("companyId", companyId);
		System.out.println(param);
		
		groupCompanyMapper.insertGroupCompany(param);

		return null;
	}
}
