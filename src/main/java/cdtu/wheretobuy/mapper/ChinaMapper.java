package cdtu.wheretobuy.mapper;

import cdtu.wheretobuy.pojo.China;
import cdtu.wheretobuy.pojo.ChinaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChinaMapper {
    int countByExample(ChinaExample example);

    int deleteByExample(ChinaExample example);

    int insert(China record);

    int insertSelective(China record);

    List<China> selectByExample(ChinaExample example);

    int updateByExampleSelective(@Param("record") China record, @Param("example") ChinaExample example);

    int updateByExample(@Param("record") China record, @Param("example") ChinaExample example);
}