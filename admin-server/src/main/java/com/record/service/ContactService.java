package com.record.service;

import com.record.entity.Contact;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author OR
 * @since 2023-05-05
 */
public interface ContactService extends IService<Contact> {


    /**
     * 查询所有的联络人
     * @return
     */
    List<Contact> findAll();


    /**
     * 添加联络人
     * @param contact
     * @return
     */
    boolean addContact(Contact contact);

    /**
     * 通过关键词查询
     * @param keywords
     * @return
     */
    List<Contact> findByKeywords(String keywords);
}
