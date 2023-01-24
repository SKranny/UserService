package PersonService.mappers;

import org.mapstruct.Mapper;
import dto.userDto.AddressDTO;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toAddress(AddressDTO addressDTO);

    AddressDTO toAddressDTO(Address address);
}
