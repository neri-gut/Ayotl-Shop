package shop.ayotl.backend.service.user.data;

import shop.ayotl.backend.config.exception.DuplicatedDataException;
import shop.ayotl.backend.config.exception.ForbiddenException;
import shop.ayotl.backend.config.exception.InvalidParamsException;
import shop.ayotl.backend.config.exception.NotFoundException;
import shop.ayotl.backend.converter.user.data.UserDataDtoConverter;
import shop.ayotl.backend.dto.user.data.UserDataCreateRequest;
import shop.ayotl.backend.dto.user.data.UserDataDto;
import shop.ayotl.backend.dto.user.data.UserDataUpdateRequest;
import shop.ayotl.backend.repository.user.UserRepository;
import shop.ayotl.backend.repository.user.data.UserDataRepository;
import shop.ayotl.backend.service.authentication.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class UserDataServiceImpl implements UserDataService {
    private final UserDataRepository repository;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final UserDataDtoConverter converter;

    public UserDataServiceImpl(
            UserDataRepository repository,
            UserRepository userRepository,
            AuthenticationService authenticationService,
            UserDataDtoConverter converter
    ) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
        this.converter = converter;
    }

    @Override
    public UserDataDto findByUserId(Long userId) {
        userRepository.findById(userId);

        return repository.findByUserId(userId);
    }

    @Override
    public UserDataDto create(UserDataCreateRequest request) {
        validateUserIdIsNotNullInRequest(request.getUserId());

        try {
            findByUserId(request.getUserId());

            throw new DuplicatedDataException("Ya existen datos para este usuario", "");
        }
        catch (NotFoundException exception) {

        }

        final var authenticatedUser = authenticationService.authenticatedUser();
        validateModificationOrCreationOnlyOfHimself(request.getUserId(), authenticatedUser.getId());

        userRepository.findById(request.getUserId());
        final var toCreate = converter.createRequestToDto(request);

        return repository.save(toCreate);
    }

    private void validateModificationOrCreationOnlyOfHimself(Long requestUserId, Long authenticatedUserId) {
        if (! requestUserId.equals(authenticatedUserId)) {
            throw new ForbiddenException("No puede modificar los datos de usuario que no son suyos", "");
        }
    }

    @Override
    public UserDataDto update(UserDataUpdateRequest request) {
        validateUserIdIsNotNullInRequest(request.getUserId());

        final var authenticatedUser = authenticationService.authenticatedUser();
        validateModificationOrCreationOnlyOfHimself(request.getUserId(), authenticatedUser.getId());

        userRepository.findById(request.getUserId());
        final var toUpdate = converter.updateRequestToDto(request);

        return repository.save(toUpdate);
    }

    private void validateUserIdIsNotNullInRequest(Long id) {
        if (id == null) {
            throw new InvalidParamsException("El id de usuario es requerido", "");
        }
    }
}
