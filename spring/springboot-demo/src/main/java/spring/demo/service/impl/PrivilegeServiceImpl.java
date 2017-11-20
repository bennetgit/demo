package spring.demo.service.impl;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.demo.constant.Constants;
import spring.demo.dto.PageQuery;
import spring.demo.dto.PrivilegeDto;
import spring.demo.exception.PrivilegeOperateException;
import spring.demo.persistence.primary.domain.Privilege;
import spring.demo.persistence.primary.domain.User;
import spring.demo.persistence.primary.jpa.IPrivilegeRepository;
import spring.demo.persistence.primary.jpa.IUserRepository;
import spring.demo.service.IPrivilegeService;
import spring.demo.util.PageResult;
import spring.demo.util.PrivilegeParser;
import spring.demo.util.StringUtil;

/**
 * Created by facheng on 17-11-15.
 */
@CacheConfig(cacheNames = Constants.CacheConfig.CACHE_NAME, keyGenerator = Constants.CacheConfig.CACHE_KEY_GENERATOR)
@Service
public class PrivilegeServiceImpl implements IPrivilegeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrivilegeServiceImpl.class);

    @Resource
    private IPrivilegeRepository privilegeRepository;

    @Resource
    private IUserRepository userRepository;

    // private IPrivilegeService getPrivilegeService() {
    // return SpringContextHolder.getBean("privilegeServiceImpl",
    // IPrivilegeService.class);
    // }

    @Override
    public PageResult<PrivilegeDto> lists(PageQuery pageQuery, PrivilegeDto dto) {

        Page<Privilege> privileges = privilegeRepository.findAll((root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (org.apache.commons.lang3.StringUtils.isNotEmpty(dto.getName())) {
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
    @CachePut(condition = "#p0 instanceof T(spring.demo.cache.Cached)")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PrivilegeDto saveOrUpdateWithCache(PrivilegeDto dto) {

        if (dto == null) {
            throw new PrivilegeOperateException();
        }

        if (dto.getId() == null) {

            User currentUser = userRepository.getOne(dto.getCurrentUserId());
            Privilege privilege = PrivilegeParser.fromDto(dto);
            privilege.setCreatedBy(currentUser);
            privilegeRepository.save(privilege);
        } else {
            update(dto);
        }

        return dto;
    }

    @Override
    @CacheEvict(condition = "#p0 instanceof T(spring.demo.cache.Cached)")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteWithCache(PrivilegeDto dto) {
        LOGGER.info("start delete privilege {}", dto);

        Privilege privilege;
        if (dto == null || dto.getId() == null || (privilege = privilegeRepository.findOne(dto.getId())) == null) {
            throw new PrivilegeOperateException("cannot find privilege  " + dto);
        }

        privilegeRepository.delete(privilege);
    }
}
