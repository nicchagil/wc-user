package com.nicchagil.orm.mapper.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nicchagil.module.ec.vo.SeckillDisplayVo;

public interface EcSeckillExtMapper {
    
	@Select("select skd.id as seckillId, gd.id as goodsId, gd.name as goodsName, skd.num, DATE_FORMAT(skd.start_time, '%Y-%m-%d %H:%i:%s') as startTime, DATE_FORMAT(skd.end_time, '%Y-%m-%d %H:%i:%s') as endTime " + 
			"from ec_seckill_detail skd " + 
			"inner join ec_goods gd on skd.goods_id = gd.id " + 
			"order by skd.start_time asc")
	List<SeckillDisplayVo> getList();
    
	@Update("update ec_seckill_detail t set t.`num` = t.`num` - #{num} where t.`goods_id` = #{goodsId}")
	int substract(@Param("goodsId") Long goodsId, @Param("num") Long num);
	
}