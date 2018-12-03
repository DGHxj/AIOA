package cn.tedu.sign.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CheckMapper {
	Integer addCheck(@Param("userId") String userId,@Param("checkTime") String checkTime);
	
	List<String> getChecks();
}
