package shop.ayotl.backend.converter.user.data;

import shop.ayotl.backend.dto.user.data.UserDataCreateRequest;
import shop.ayotl.backend.dto.user.data.UserDataDto;
import shop.ayotl.backend.dto.user.data.UserDataUpdateRequest;
import shop.ayotl.backend.model.UserData;

public class UserDataDtoConverter {
    public UserDataDto modelToDto(UserData model) {
        return UserDataDto
                .builder()
                .userId(model.getUserId())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .secondLastName(model.getSecondLastName())
                .build();
    }

    public UserData dtoToModel(UserDataDto dto) {
        return UserData
                .builder()
                .userId(dto.getUserId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .secondLastName(dto.getSecondLastName())
                .build();
    }

    public UserDataDto createRequestToDto(UserDataCreateRequest request) {
        return UserDataDto
                .builder()
                .userId(request.getUserId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .secondLastName(request.getSecondLastName())
                .build();
    }

    public UserDataDto updateRequestToDto(UserDataUpdateRequest request) {
        return UserDataDto
                .builder()
                .userId(request.getUserId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .secondLastName(request.getSecondLastName())
                .build();
    }
}
