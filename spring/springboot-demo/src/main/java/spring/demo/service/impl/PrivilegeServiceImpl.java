package spring.demo.service.impl;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.demo.dto.PageQuery;
import spring.demo.dto.PrivilegeDto;
import spring.demo.exception.PrivilegeOperateException;
import spring.demo.persistence.primary.domain.Privilege;
import spring.demo.persistence.primary.domain.User;
import spring.demo.persistence.primary.jpa.IPrivilegeRepository;
import spring.demo.persistence.primary.jpa.IUserRepository;
import spring.demo.service.IPrivilegeService;
import spring.demo.service.common.CachedService;
import spring.demo.util.PageResult;
import spring.demo.util.PrivilegeParser;
import spring.demo.util.StringUtil;

/**
 * Created by facheng on 17-11-15.
 */

@Service
public class PrivilegeServiceImpl implements IPrivilegeService, CachedService<PrivilegeDto, PrivilegeDto> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrivilegeServiceImpl.class);

    @Resource
    private IPrivilegeRepository privilegeRepository;

    @Resource
    private IUserRepository userRepository;

    @Override
    public PageResult<PrivilegeDto> lists(PageQuery pageQuery, PrivilegeDto dto) {

        Page<Privilege> privileges = privilegeRepository.findAll((root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotEmpty(dto.getName())) {
                predicates.add(cb.like(root.get("name"), StringUtil.wildcard(dto.getName())));
            }

            return cb.and(predicates.toArray(new Predicate[0]));

        }, pageQuery.sortPageDefault("id"));

        return PageResult.of(privileges.getTotalElements(), PrivilegeParser.fromDomains(privileges.getContent()));
    }

    @Override
    @Transactional
    public void addPrivilege(PrivilegeDto privilegeDto, Long currentUserId) {
        LOGGER.info("start add privilege {}", privilegeDto);

        saveOrUpdateWithCache(privilegeDto.withCurrentId(currentUserId));
    }

    @Override
    public PrivilegeDto findPrivilegeDetail(Long privilegeId) {
        LOGGER.info("start find privilege {}", privilegeId);
        Privilege privilege;
        if (privilegeId == null || (privilege = privilegeRepository.findOne(privilegeId)) == null) {
            throw new PrivilegeOperateException("cannot find privilege " + privilegeId);
        }
        return PrivilegeParser.fromDomain(privilege);
    }

    @Override
    @Transactional
    public void updatePrivilege(Long id, PrivilegeDto privilegeDto, Long currentUserId) {
        if (privilegeDto == null) {
            throw new PrivilegeOperateException("privilege operate error");
        }

        saveOrUpdateWithCache(privilegeDto.withId(id).withCurrentId(currentUserId));
    }

    private void update(PrivilegeDto privilegeDto) {
        LOGGER.info("start update  privilege {}", privilegeDto);

        User currentUser;
        Privilege privilege;
        if (privilegeDto == null || privilegeDto.getCurrentUserId() == null
                || (currentUser = userRepository.findById(privilegeDto.getCurrentUserId())) == null) {
            throw new PrivilegeOperateException("privilege operate error");
        }

        if (privilegeDto.getId() == null || (privilege = privilegeRepository.findOne(privilegeDto.getId())) == null) {
            throw new PrivilegeOperateException("cannot find privilege " + privilegeDto);
        }

        privilege.setUpdatedBy(currentUser);
        privilege.setUpdatedOn(Date.from(Instant.now()));
        privilege.setUrl(privilegeDto.getUrl());
        privilege.setName(privilegeDto.getName());
        privilegeRepository.save(privilege);
    }

    @Override
    public void deletePrivilege(Long id) {
        LOGGER.info("start delete privilege {}", id);
        deleteWithCache(PrivilegeDto.getInstance().withId(id));
    }

    @Override
    @Transactional
    public PrivilegeDto saveOrUpdateWithCache(PrivilegeDto dto) {

        if (dto == null) {
            throw new PrivilegeOperateException();
        }

        if (dto.getId() == null) {

            privilegeRepository.save(PrivilegeParser.fromDto(dto));
        } else {
            update(dto);
        }

        return dto;
    }

    @Override
    @Transactional
    public void deleteWithCache(PrivilegeDto dto) {
        LOGGER.info("start delete privilege {}", dto);

        Privilege privilege;
        if (dto == null || dto.getId() == null || (privilege = privilegeRepository.findOne(dto.getId())) == null) {
            throw new PrivilegeOperateException("cannot find privilege  " + dto);
        }

        privilegeRepository.delete(privilege);
    }
}
