package shop.ayotl.backend.service.user;

import shop.ayotl.backend.config.exception.DuplicatedDataException;
import shop.ayotl.backend.config.exception.ForbiddenException;
import shop.ayotl.backend.config.exception.InvalidParamsException;
import shop.ayotl.backend.config.exception.NotFoundException;
import shop.ayotl.backend.converter.user.UserDtoConverter;
import shop.ayotl.backend.dto.user.UserCreateRequest;
import shop.ayotl.backend.dto.user.UserOutputDto;
import shop.ayotl.backend.dto.user.UserPasswordUpdateRequest;
import shop.ayotl.backend.dto.user.data.UserDataDto;
import shop.ayotl.backend.repository.role.RoleRepository;
import shop.ayotl.backend.repository.user.UserRepository;
import shop.ayotl.backend.repository.user.data.UserDataRepository;
import shop.ayotl.backend.service.authentication.AuthenticationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static shop.ayotl.backend.common.constant.role.RoleConstants.ADMIN;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserDataRepository userDataRepository;
    private final AuthenticationService authenticationService;
    private final RoleRepository roleRepository;
    private final UserDtoConverter converter;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            final UserRepository repository,
            final UserDataRepository userDataRepository,
            AuthenticationService authenticationService,
            final RoleRepository roleRepository,
            final UserDtoConverter converter,
            PasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.userDataRepository = userDataRepository;
        this.authenticationService = authenticationService;
        this.roleRepository = roleRepository;
        this.converter = converter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserOutputDto> findAll() {
        final var users = repository.findAll();

        return users
                .stream()
                .map(converter::dtoToOutputDto)
                .toList();
    }

    @Override
    public UserOutputDto findById(final Long id) {
        final var found = repository.findById(id);

        return converter.dtoToOutputDto(found);
    }

    @Override
    @Transactional
    public UserOutputDto create(final UserCreateRequest request) {
        try {
            repository.findByEmail(request.getEmail());

            throw new DuplicatedDataException("El email ya se encuentra registrado", "");
        }
        catch (NotFoundException e) {}

        // Hash the password
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        final var role = roleRepository.findById(request.getRoleId());
        final var toCreate = converter.createRequestDtoToDto(request, role);
        final var created = repository.save(toCreate);

        final var userDataToCreate = UserDataDto
                .builder()
                .userId(created.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .secondLastName(request.getSecondLastName())
                .build();

        userDataRepository.save(userDataToCreate);

        return converter.dtoToOutputDto(created);
    }

    @Override
    public void updatePassword(UserPasswordUpdateRequest request) {
        final var authenticatedUser = authenticationService.authenticatedUser();

        validateModificationOrCreationOnlyOfHimself(request.getId(), authenticatedUser.getId());

        final var oldPassword = authenticatedUser.getPassword();

        // See: https://stackoverflow.com/a/54597746
        if (! passwordEncoder.matches(request.getOldPassword(), oldPassword)) {
            throw new InvalidParamsException("Contraseña anterior no válida", "");
        }

        final var encryptedNewPassword = passwordEncoder.encode(request.getNewPassword());
        authenticatedUser.setPassword(encryptedNewPassword);

        repository.save(authenticatedUser);
    }

    private void validateModificationOrCreationOnlyOfHimself(Long requestUserId, Long authenticatedUserId) {
        if (! authenticatedUserId.equals(requestUserId)) {
            throw new ForbiddenException("No se puede modificar a otro usuario que no es el suyo", "");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        final var authenticatedUser = authenticationService.authenticatedUser();
        final var found = repository.findById(id);

        /*
         Admins can only delete users that aren't admins. If the user is a normal user
         he can only delete his own user
         */
        if (
                (
                        authenticatedUser.getRoles()
                        .stream()
                        .anyMatch(role -> ADMIN.equals(role.getName()))
                        && found.getRoles()
                        .stream()
                        .anyMatch(role -> ADMIN.equals(role.getName()))
                )
                        || authenticatedUser.getRoles()
                        .stream()
                        .noneMatch(role -> ADMIN.equals(role.getName()))
        ) {
            validateModificationOrCreationOnlyOfHimself(id, authenticatedUser.getId());
        }

        repository.findById(id);

        try {
            userDataRepository.findByUserId(id);
            userDataRepository.deleteByUserId(id);
        }
        catch (NotFoundException exception) {

        }

        repository.deleteById(id);
    }
}
