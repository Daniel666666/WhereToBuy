package cdtu.wheretobuy.service.impl;


import cdtu.wheretobuy.mapper.UserMapper;
import cdtu.wheretobuy.pojo.*;
import cdtu.wheretobuy.service.UserService;
import cdtu.wheretobuy.util.RandomUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<User> findAll() {
		return userMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<User> page=   (Page<User>) userMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	/**
	 * 增加
	 */
	@Override
	public void add(User user) {
		user.setStatus("0");
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userMapper.insert(user);
		String activeCode= RandomUtil.generateStr(32);
		redisTemplate.boundHashOps("userActiveCode").put(user.getId(),activeCode);
		Map map=new HashMap();
		map.put("obj","user");
		map.put("addr",user.getEmail());
		map.put("userId",user.getId());
		map.put("activeCode",activeCode);
		jmsMessagingTemplate.convertAndSend("sendEmail",map);//发送激活邮件
		System.out.println("已发送activeMq----发送邮件消息!");
	}

	/**
	 * 激活
	 * @param Id
	 * @param activeCode
	 * @return
	 */
	public Result activate(Integer Id, String activeCode){
		if(redisTemplate.boundHashOps("userActiveCode").get(Id)==null||redisTemplate.boundHashOps("userActiveCode").get(Id)==""){
			return new Result(false,"你已激活账户，请勿重复激活");
		}
		String redisCode=redisTemplate.boundHashOps("userActiveCode").get(Id).toString();
		if (redisCode.equals(activeCode)){
			User user=userMapper.selectByPrimaryKey(Id);
			user.setStatus("1");
			userMapper.updateByPrimaryKey(user);

			redisTemplate.boundHashOps("userActiveCode").delete(Id);
			System.out.println(Id+" 已激活");
			return new Result(true,"激活成功");
		}else{
			return new Result(false,"非法操作");
		}

	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(User user){
		userMapper.updateByPrimaryKey(user);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public User findOne(Integer id){
		return userMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			userMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(User user, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		UserExample example=new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		
		if(user!=null){			
						if(user.getName()!=null && user.getName().length()>0){
				criteria.andNameLike("%"+user.getName()+"%");
			}
			if(user.getNickName()!=null && user.getNickName().length()>0){
				criteria.andNickNameLike("%"+user.getNickName()+"%");
			}
			if(user.getPassword()!=null && user.getPassword().length()>0){
				criteria.andPasswordLike("%"+user.getPassword()+"%");
			}
			if(user.getPhone()!=null && user.getPhone().length()>0){
				criteria.andPhoneLike("%"+user.getPhone()+"%");
			}
			if(user.getEmail()!=null && user.getEmail().length()>0){
				criteria.andEmailLike("%"+user.getEmail()+"%");
			}
			if(user.getAvatar()!=null && user.getAvatar().length()>0){
				criteria.andAvatarLike("%"+user.getAvatar()+"%");
			}
			if(user.getSex()!=null && user.getSex().length()>0){
				criteria.andSexLike("%"+user.getSex()+"%");
			}
			if(user.getStatus()!=null && user.getStatus().length()>0){
				criteria.andStatusLike("%"+user.getStatus()+"%");
			}
	
		}
		
		Page<User> page= (Page<User>)userMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public User login(String username, String password) {
		UserExample exampel=new UserExample();
		UserExample.Criteria criteria = exampel.createCriteria();
		criteria.andNameEqualTo(username);
		criteria.andStatusEqualTo("1");
		criteria.andPasswordEqualTo(DigestUtils.md5DigestAsHex(password.getBytes()));
		List<User> userList = userMapper.selectByExample(exampel);
		if(userList!=null&userList.size()>0){
			return userList.get(0);
		}else{
			return null;
		}

	}

}
