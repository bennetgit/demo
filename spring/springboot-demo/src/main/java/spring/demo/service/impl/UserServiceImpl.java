package spring.demo.service.impl;

import static spring.demo.util.UserParser.fromDomain;
import static spring.demo.util.UserParser.fromDto;
import static spring.demo.util.UserParser.toSimpleUserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import spring.demo.dto.PageQuery;
import spring.demo.dto.TreeNode;
import spring.demo.dto.UserDto;
import spring.demo.enums.UserStatus;
import spring.demo.exception.UserOperateException;
import spring.demo.jdbctemplate.IUserDao;
import spring.demo.persistence.primary.domain.Role;
import spring.demo.persistence.primary.domain.User;
import spring.demo.persistence.primary.jpa.IRoleRepository;
import spring.demo.persistence.primary.jpa.IUserRepository;
import spring.demo.service.IUserService;
import spring.demo.util.PageResult;
import spring.demo.util.StringUtil;
import spring.demo.util.helper.PasswordHelper;

/**
 * Created by facheng on 16.03.17.
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Resource
    private IUserDao userDao;

    @Resource
    private IUserRepository userRepository;

    @Resource
    private IRoleRepository roleRepository;

    @Override
    @Transactional
    public void create(UserDto userDto) {
        userRepository.save(fromDto(userDto));
    }

    @Transactional
    @Override
    public void deleteByName(String name) {
        userDao.deleteByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void deleteAllUsers() {
        userDao.deleteAllUsers();
    }

    @Override
    @Transactional
    public UserDto getUserByName(String username) {
        return fromDomain(userRepository.findUser(username));
    }

    @Override
    public PageResult<UserDto> getUserListByPage(PageQuery pageQuery, UserDto userQuery) {
        Page<User> users = userRepository.findAll((root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtil.isNotBlank(userQuery.getUsername())) {
                list.add(cb.equal(root.get(UserDto.P_USERNAME), userQuery.getUsername()));
            }
            if (!Objects.isNull(userQuery.getCreatedOnStart())) {
                list.add(cb.greaterThanOrEqualTo(root.get(UserDto.P_CREATED_ON), userQuery.getCreatedOnStart()));
            }
            if (!Objects.isNull(userQuery.getCreatedOnEnd())) {
                list.add(cb.lessThanOrEqualTo(root.get(UserDto.P_CREATED_ON), userQuery.getCreatedOnEnd()));
            }

            return cb.and(list.toArray(new Predicate[0]));
        }, pageQuery.sortPageDefault(UserDto.P_ID));

        return PageResult.of(users.getTotalElements(), toSimpleUserDto(users.getContent()));
    }

    @Override
    @Transactional
    public UserDto getUserById(Long id) {
        UserDto result = toSimpleUserDto(userRepository.findOne(id));
        if (result == null) {
            throw new UserOperateException("cannot find user " + id);
        }

        return result;
    }

    @Override
    @Transactional
    public void update(UserDto userDto) {

        LOGGER.info("start to update user, userDto {}", userDto);
        User user;
        if (userDto == null || userDto.getId() == null || (user = userRepository.findOne(userDto.getId())) == null) {
            throw new UserOperateException("cannot find user, userDto " + userDto);
        }

        user.setUsername(userDto.getUsername());
        user.setMobile(userDto.getMobile());
        user.setSex(userDto.getSexType());
        user.setAdmin(userDto.getAdmin());
    }

    @Override
    @Transactional
    public Pair<UserDto, List<TreeNode<Long>>> getUserAndRoleTree(Long userId) {

        UserDto userDto;
        User user;
        if (userId == null || (userDto = fromDomain(user = userRepository.findById(userId))) == null) {
            throw new UserOperateException("cannot find user " + userId);
        }

        List<TreeNode<Long>> treeNodes = roleRepository.findAllOrderById().stream()
                .map(r -> convertRoleToTreeNode(r, isCheckedRole(r, user.getRoles()))).collect(Collectors.toList());
        return new ImmutablePair<>(userDto, treeNodes);
    }

    @Override
    @Transactional
    public void updateUserRole(Long userId, List<Long> roleIds) {

        LOGGER.info("start assign role {} to user {}", roleIds, userId);
        User user;
        if (userId == null || (user = userRepository.findById(userId)) == null) {
            throw new UserOperateException("cannot find user " + userId);
        }

        List<Role> roles = roleRepository.findRoleByIds(roleIds);
        user.setRoles(roles);
    }

    @Override
    @Transactional
    public void updateStatus(Boolean enabled, List<Long> userIds) {

        if (CollectionUtils.isEmpty(userIds)) {
            throw new UserOperateException("user id is empty");
        }

        if (enabled == null) {
            return;
        }

        List<User> users = userRepository.findAll(userIds);

        if (CollectionUtils.isEmpty(users)) {
            throw new UserOperateException("user is empty");
        }

        users.stream().forEach(u -> u.setStatus(enabled ? UserStatus.ACTIVE : UserStatus.INACTIVE));

    }

    @Override
    @Transactional
    public void updatePassword(Long currentUserId, String newPassword) {

        User currentUser = userRepository.getOne(currentUserId);
        if (currentUser == null) {
            throw new UserOperateException("cannot find user" + currentUserId);
        }

        currentUser.setPassword(PasswordHelper.password(newPassword));
    }

    private TreeNode<Long> convertRoleToTreeNode(Role role, boolean isChecked) {
        TreeNode treeNode = new TreeNode();
        treeNode.setChecked(isChecked);
        treeNode.setId(role.getId());
        treeNode.setName(role.getName());
        return treeNode;
    }

    private boolean isCheckedRole(Role role, List<Role> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return false;
        }

        return roles.stream().anyMatch(r -> Objects.equals(r.getId(), role.getId()));
    }

}
