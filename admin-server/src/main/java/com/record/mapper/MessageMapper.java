package com.record.mapper;

import com.record.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author OR
 * @since 2023-05-05
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}
