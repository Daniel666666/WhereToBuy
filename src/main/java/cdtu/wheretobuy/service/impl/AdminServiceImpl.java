package cdtu.wheretobuy.service.impl;
import java.util.List;

import cdtu.wheretobuy.pojo.AdminExample;
import cdtu.wheretobuy.pojo.PageResult;
import cdtu.wheretobuy.mapper.AdminMapper;
import cdtu.wheretobuy.pojo.Admin;
import cdtu.wheretobuy.pojo.AdminExample.Criteria;
import cdtu.wheretobuy.service.AdminService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Admin> findAll() {
		return adminMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<Admin> page=   (Page<Admin>) adminMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Admin admin) {
		adminMapper.insert(admin);
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Admin admin){
		adminMapper.updateByPrimaryKey(admin);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Admin findOne(Integer id){
		return adminMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			adminMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(Admin admin, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		AdminExample example=new AdminExample();
		Criteria criteria = example.createCriteria();
		
		if(admin!=null){			
						if(admin.getName()!=null && admin.getName().length()>0){
				criteria.andNameLike("%"+admin.getName()+"%");
			}
			if(admin.getNickName()!=null && admin.getNickName().length()>0){
				criteria.andNickNameLike("%"+admin.getNickName()+"%");
			}
			if(admin.getPassword()!=null && admin.getPassword().length()>0){
				criteria.andPasswordLike("%"+admin.getPassword()+"%");
			}
	
		}
		
		Page<Admin> page= (Page<Admin>)adminMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public Admin login(String name, String password) {
		AdminExample example=new AdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		criteria.andPasswordEqualTo(password);
		List<Admin> adminList = adminMapper.selectByExample(example);
		if(adminList!=null&&adminList.size()>0){
			return adminList.get(0);
		}
		return null;
	}

}
