package com.record.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.record.entity.Contact;
import com.record.mapper.ContactMapper;
import com.record.service.ContactService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.record.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author OR
 * @since 2023-05-05
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ContactServiceImpl extends ServiceImpl<ContactMapper, Contact> implements ContactService {

    private final ContactMapper contactMapper;


    @Override
    public List<Contact> findAll() {
        return contactMapper.selectList(null);
    }

    @Override
    public boolean addContact(Contact contact) {
        contact.setPublishDate(TimeUtils.generateCurrentTime());
        return contactMapper.insert(contact) != 0;
    }

    @Override
    public List<Contact> findByKeywords(String keywords) {
        return contactMapper.selectList(
                new LambdaQueryWrapper<Contact>()
                        .like(Contact::getId,keywords)
                        .or()
                        .like(Contact::getSex,keywords)
                        .or()
                        .like(Contact::getPhoneNumber,keywords)
                        .or()
                        .like(Contact::getEmail,keywords)
                        .or()
                        .like(Contact::getQq,keywords)
                        .or()
                        .like(Contact::getWechat,keywords)
                        .or()
                        .like(Contact::getAddress,keywords)
                        .or()
                        .like(Contact::getEmployer,keywords)
                        .or()
                        .like(Contact::getPostcode,keywords)
                        .or()
                        .like(Contact::getName,keywords)
        );
    }


}
